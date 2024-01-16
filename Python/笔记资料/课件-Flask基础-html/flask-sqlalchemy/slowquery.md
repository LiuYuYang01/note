# 慢查询统计 (拓展)

- `sqlalchemy` 通过配置项`SQLALCHEMY_RECORD_QUERIES` 可以对执行过的查询语句进行统计处理
- 开发中 ,可以 **使用该配置项 和 钩子函数** 实现 **慢查询统计**, 以便后续针对 执行效率差的语句进行优化处理

```python

from flask import Flask
from flask_sqlalchemy import SQLAlchemy, get_debug_queries

app = Flask(__name__)

# 相关配置
app.config["SQLALCHEMY_DATABASE_URI"] = "mysql://root:mysql@127.0.0.1:3306/test32"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False

app.config['SQLALCHEMY_RECORD_QUERIES'] = True  # 启用慢查询记录功能
app.config['FLASKY_DB_QUERY_TIMEOUT'] = 0.0001  # 设置sql执行超时时间，用于统计执行时间超过 0.0001秒的查询语句

# 组件初始化
db = SQLAlchemy(app)


@app.after_request
def after_request(response):  # 钩子函数

    for query in get_debug_queries():  # 获取本次请求中执行过的查询语句

        # 如果本次请求的查询语句属于慢查询,则打印
        if query.duration >= app.config['FLASKY_DB_QUERY_TIMEOUT']:  

            # 打印超时sql执行信息 (SQL语句, 查询参数, 查询时间, 代码位置), 后续也可以考虑保存到日志文件中
            print('#####Slow query:%s \nParameters:%s \nDuration:%fs\nContext:%s\n #####' %
                  (query.statement, query.parameters, query.duration, query.context))

    return response


# 模型类
class User(db.Model):
    __tablename__ = "users"  
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(64))
    email = db.Column(db.String(64))
    age = db.Column(db.Integer)


@app.route('/')
def index():
   
    users = User.query.all()  # 执行查询
    print(users)

    return 'index'


if __name__ == '__main__':
    # 删除所有表
    db.drop_all()
    # 创建所有表
    db.create_all()

    # 添加测试数据
    user1 = User(name='wang', email='wang@163.com', age=20)
    user2 = User(name='zhang', email='zhang@189.com', age=33)
    user3 = User(name='chen', email='chen@126.com', age=23)
    user4 = User(name='zhou', email='zhou@163.com', age=29)
    user5 = User(name='tang', email='tang@itheima.com', age=25)
    user6 = User(name='wu', email='wu@gmail.com', age=25)
    user7 = User(name='qian', email='qian@gmail.com', age=23)
    user8 = User(name='liu', email='liu@itheima.com', age=30)
    user9 = User(name='li', email='li@163.com', age=28)
    user10 = User(name='sun', email='sun@163.com', age=26)
    db.session.add_all([user1, user2, user3, user4, user5, user6, user7, user8, user9, user10])
    db.session.commit()

    app.run(debug=True)

```