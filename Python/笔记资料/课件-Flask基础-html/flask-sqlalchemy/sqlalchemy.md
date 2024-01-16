# sqlalchemy单体使用 (拓展)

<extoc></extoc>

&nbsp;

- `flask-sqlalchemy` 在脱离 `flask`框架 后将无法使用, `django-orm`的情况亦然, 不过
`sqlalchemy`包 作为独立的ORM框架可以在任何环境下使用
- 以下内容为 `sqlalchemy`包 的单体用法, 仅作为拓展内容, 目的为加深对于 `sqlalchemy`的理解 及 工作中的进一步应用

## 1.构建模型类

```python
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String


# 创建模型基类
Base = declarative_base()

# 创建数据库引擎
# 细节1  sqlalchemy默认实现了连接池功能, 并且可以自动重连(pool_size 连接池中的连接数, max_overflow 超出连接池的额外连接数)
# 细节2  如果数据库使用utf-8支持中文, 则设置连接地址时需要标明  ?charset=utf8   否则报错
engine = create_engine('mysql://root:mysql@127.0.0.1:3306/test31?charset=utf8', echo=True, pool_size=5, max_overflow=10)


class User(Base):
    __tablename__ = 't_user'
    id = Column(Integer, primary_key=True)
    name = Column(String(20), unique=True)


class Address(Base):
    __tablename__ = 't_address'
    id = Column(Integer, primary_key=True)
    detail = Column(String(20))
    user_id = Column(Integer)


if __name__ == '__main__':
    # 删除所有表
    Base.metadata.drop_all(engine)
    # 创建所有表
    Base.metadata.create_all(engine)


```


## 2.构建session


```python
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import scoped_session
from sqlalchemy.orm import sessionmaker


def create_session(engine):
    """封装Session创建过程   此函数只在应用初始化时调用一次即可"""

    # 创建Session工厂     SessionFactory()就可以创建出session对象, 但是该对象没有实现线程隔离
    SessionFactory = sessionmaker(bind=engine)

    # 生成线程隔离的session对象(每个线程取自己的session)
    session = scoped_session(SessionFactory)
    # ps: 其实返回的session是scoped_session类型对象, 再通过session()才会创建Session对象, 不过scoped_session实现了代理功能, 进行数据操作时, 会自动创建/获取实现了线程隔离的Session对象并执行操作

    return session



# 创建模型基类
Base = declarative_base()

# 创建数据库引擎
engine = create_engine('mysql://root:mysql@127.0.0.1:3306/test31?charset=utf8', echo=False, pool_size=5, max_overflow=10)

# 创建会话
session = create_session(engine)


class User(Base):
    __tablename__ = 't_user'
    id = Column(Integer, primary_key=True)
    name = Column(String(20), unique=True)


class Address(Base):
    __tablename__ = 't_address'
    id = Column(Integer, primary_key=True)
    detail = Column(String(20))
    user_id = Column(Integer)



def index():
    """模拟视图函数"""

    # 添加数据
    user1 = User(name='zs')
    session.add(user1)
    session.commit()

    # 查询数据
    items = session.query(User.id, User.name).filter(User.name == 'zs').all()
    for item in items:
        print(item.id, item.name)


if __name__ == '__main__':
    # 删除所有表
    Base.metadata.drop_all(engine)
    # 创建所有表
    Base.metadata.create_all(engine)

    index()


```


## 3.封装session


```python
from contextlib import contextmanager
from sqlalchemy import create_engine, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import relationship, scoped_session
from sqlalchemy.orm import sessionmaker


def create_session(engine):
    """封装Session创建过程"""

    SessionFactory = sessionmaker(bind=engine)
    session = scoped_session(SessionFactory)

    return session


# 创建模型基类
Base = declarative_base()

# 创建数据库引擎
engine = create_engine('mysql://root:mysql@127.0.0.1:3306/test27?charset=utf8', echo=False, pool_size=5, max_overflow=10)

# 创建会话
# session = create_session(engine)


class User(Base):
    __tablename__ = 't_user'
    id = Column(Integer, primary_key=True)
    name = Column(String(20), unique=True)


class Address(Base):
    __tablename__ = 't_address'
    id = Column(Integer, primary_key=True)
    detail = Column(String(20))
    user_id = Column(Integer)


@contextmanager  # 装饰器形式的上下文管理器
def session_scope():
    """使用上下文管理器对session和事务操作进行封装"""
    try:
        session_obj = create_session(engine)
        yield session_obj
        session_obj.commit()
    except:
        session_obj.rollback()  # 事务失败, 手动回滚, 避免新的事务无法创建
        raise
    finally:
        session_obj.remove()  # session工作完成, 销毁session, 释放内存


def index():
    """模拟视图函数"""

    with session_scope() as session:  #  session = session_obj 获取session对象, 代码块执行完会自动提交 并 销毁session

        # 添加数据
        user1 = User(name='zs')
        session.add(user1)

        # 查询数据
        items = session.query(User.id, User.name).filter(User.name == 'zs').all()
        for item in items:
            print(item.id, item.name)


if __name__ == '__main__':
    # 删除所有表
    Base.metadata.drop_all(engine)
    # 创建所有表
    Base.metadata.create_all(engine)

    index()

```


## 4.关系属性 和 连接查询

```python
from contextlib import contextmanager
from sqlalchemy import create_engine, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import relationship, scoped_session
from sqlalchemy.orm import sessionmaker


def create_session(engine):
    """封装Session创建过程"""

    SessionFactory = sessionmaker(bind=engine)
    session = scoped_session(SessionFactory)

    return session


# 创建模型基类
Base = declarative_base()

# 创建数据库引擎
engine = create_engine('mysql://root:mysql@127.0.0.1:3306/test27?charset=utf8', echo=False, pool_size=5, max_overflow=10)

# 创建会话
# session = create_session(engine)


class User(Base):
    __tablename__ = 't_user'
    id = Column(Integer, primary_key=True)
    name = Column(String(20), unique=True)
    addresses = relationship('Address')


class Address(Base):
    __tablename__ = 't_address'
    id = Column(Integer, primary_key=True)
    detail = Column(String(20))
    user_id = Column(Integer, ForeignKey('t_user.id'))


@contextmanager  # 装饰器形式的上下文管理器
def session_scope():
    """使用上下文管理器对session和事务操作进行封装"""
    try:
        session = create_session(engine)
        yield session
        session.commit()
    except:
        session.rollback()  # 事务失败, 手动回滚, 避免新的事务无法创建
        raise
    finally:
        session.remove()  # session工作完成, 销毁session, 释放内存


def index():
    """模拟视图函数"""

    with session_scope() as session:  # 获取session对象, 代码块执行完会自动提交 并 销毁session

        # 添加数据
        user1 = User(name='zs')
        session.add(user1)
        session.flush()

        adr1 = Address(detail='中关村1号', user_id=user1.id)
        session.add(adr1)

        session.commit()


        # 关系属性
        print(user1.addresses[0].detail)

        # 连接查询
        ret = session.query(User.name, Address.detail).join(Address, User.id == Address.user_id).filter(User.name == 'zs').all()
        for username, adr_detail in ret:
            print(username, adr_detail)


if __name__ == '__main__':
    # 删除所有表
    Base.metadata.drop_all(engine)
    # 创建所有表
    Base.metadata.create_all(engine)

    index()

```

## 5.执行原生SQL

```python
from contextlib import contextmanager
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import scoped_session
from sqlalchemy.orm import sessionmaker


def create_session(engine):
    """封装Session创建过程"""

    SessionFactory = sessionmaker(bind=engine)
    session = scoped_session(SessionFactory)

    return session


# 创建模型基类
Base = declarative_base()

# 创建数据库引擎
engine = create_engine('mysql://root:mysql@127.0.0.1:3306/test31?charset=utf8', echo=False, pool_size=5, max_overflow=10)

# 创建会话
# session = create_session(engine)


class User(Base):
    __tablename__ = 't_user'
    id = Column(Integer, primary_key=True)
    name = Column(String(20), unique=True)


class Address(Base):
    __tablename__ = 't_address'
    id = Column(Integer, primary_key=True)
    detail = Column(String(20))
    user_id = Column(Integer)


@contextmanager  # 装饰器形式的上下文管理器
def session_scope():
    """使用上下文管理器对session和事务操作进行封装"""
    try:
        session = create_session(engine)
        yield session
        session.commit()
    except:
        session.rollback()  # 事务失败, 手动回滚, 避免新的事务无法创建
        raise
    finally:
        session.remove()  # session工作完成, 销毁session, 释放内存


def index():
    """模拟视图函数"""

    with session_scope() as session:  # 获取session对象, 代码块执行完会自动提交 并 销毁session
        # 添加数据
        user1 = User(name='zs')
        session.add(user1)
        session.commit()

        # 原生SQL查询数据
        data = session.execute('select * from t_user where id=:param', {'param': 1})

        print(data.rowcount)  # 取条数 count

        # row = data.fetchone()  # 取第一条  first
        # print(row.id)  # 取主键
        # print(row.name)  # 取字段

        rows = data.fetchall()  # 取所有数据  all  已经fetch出的数据将无法再取出
        for row in rows:
            print("fetchall:", row.id, row.name)


if __name__ == '__main__':
    # 删除所有表
    Base.metadata.drop_all(engine)
    # 创建所有表
    Base.metadata.create_all(engine)

    index()


```