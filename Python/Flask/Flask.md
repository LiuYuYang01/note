# Flask

## 开始

### 虚拟环境

#### 安装

```
pip3 install virtualenv
```



#### 创建

在当前目录创建虚拟环境 `venv` 为目录名，可以自定义

```
virtualenv venv
```



#### 激活

**Window**

激活虚拟环境

```
venv/Scripts/activate
```

**Linux**

激活虚拟环境

```
source venv/bin/activate
```



#### 启动

```
python3 /root/www/thrive/demo.py
```

**示例**

```
(venv) root@iZuf6dns8s5rhksle4gdq1Z:~/www/thrive/venv/bin# python3 /root/www/thrive/demo.py
```



### 最小应用

```python
from flask import Flask

app = Flask(__name__)


@app.route("/")
def index():
    return "<h1>Hello World!</h1>"


if __name__ == "__main__":
    app.run()

```



**app.run**

```python
app.run(host='0.0.0.0', port=8000, debug=True)
```

- **host：** 指定应用程序要运行的主机名，默认为 `127.0.0.1` 表示只能本地电脑访问该应用程序，如果设置为 `0.0.0.0`  表示所有人都可以访问
- **port：** 指定应用程序要运行的端口号，默认为 `5000`
- **debug：** 设置调试模式，如果将其设置为 `True`，则在代码发生更改时会自动重新加载应用程序，默认为 `False`，一般用于开发过程中调试使用
- **threaded：** 指定是否启用多线程模式，默认为 `False` ，如果设置为 `True`，则可以处理并发请求
- **ssl_context：** 指定 `SSL` 上下文，用于启用 `HTTPS` 安全连接



## 路由

- 路由对应的URL必须以 `/` 开头
- `app.route()` 的 `methods` 参数可以指定路由支持的请求方式，默认为 `GET` 请求

```python
@app.route("/", methods=["GET", "POST", "PATH", "DELETE"])
def index():
    return "<h1>Hello World!</h1>"

```

​	

### 路由规则

通过 `app.url_map` 可以获取所有路由规则，规则主要包含 **URL**、**支持的请求方式**、**视图函数** 三部分内容

```python
@app.route("/")
def index():
    return "<h1>Hello World!</h1>"


@app.route("/swiper", methods=["POST", "PATCH", "DELETE"])
def swiper():
    return "<h1>Swiper!</h1>"


if __name__ == "__main__":
    # 打印所有的路由对象
    print(app.url_map)

    # 遍历路由信息
    for item in app.url_map.iter_rules():
        print(f"URL：{item.rule}  请求方式：{item.methods}  视图函数：{item.endpoint}")

    app.run(debug=True)

```



### 路由变量

路由变量的作用是 **传递URL路径参数**，实现动态URL

```python
# http://127.0.0.1:5000/100

@app.route("/<id>")
def index(id): # 必须定义同名形参接收路由变量的值
    print(id) # 100
    return f"<h1>{id}</h1>"
```



### 路由转换器

默认情况下通过路由变量接收到的数据是字符串类型，如果我们想要数值类型的数据，那么还需要手动转换

```python
@app.route("/<id>")
def index(id):
    print(type(id)) # <class 'str'>
    return f"<h1>{id}</h1>"
```



通过路由转换器可以很方便的处理

```python
@app.route("/<int:id>")
def index(id):
    print(type(id)) # <class 'int'>
    return f"<h1>{id}</h1>"
```



通过 `app.url_map.converters` 属性可以查看所有支持转换的类型

```python
print(app.url_map.converters)
# {'default': <class 'werkzeug.routing.converters.UnicodeConverter'>, 
# 'string': <class 'werkzeug.routing.converters.UnicodeConverter'>, 
# 'any': <class 'werkzeug.routing.converters.AnyConverter'>, 
# 'path': <class 'werkzeug.routing.converters.PathConverter'>, 
# 'int': <class 'werkzeug.routing.converters.IntegerConverter'>, 
# 'float': <class 'werkzeug.routing.converters.FloatConverter'>, 
# 'uuid': <class 'werkzeug.routing.converters.UUIDConverter'>}
```



## 请求

flask的请求数据通过 `request` 对象来获取，下面是他的一些常用属性：

| 属性    | 说明                           | 类型                        |
| :------ | :----------------------------- | :-------------------------- |
| url     | 请求的 URL 地址                | str                         |
| method  | 请求使用的 HTTP 方法           | str                         |
| headers | 请求头中的信息                 | EnvironHeaders 类字典对象   |
| args    | 请求中的查询参数               | MultiDict                   |
| form    | 记录请求中的表单数据           | MultiDict                   |
| data    | 记录请求的数据，并转换为字符串 | bytes                       |
| json    | 记录请求体中的 json 数据       | Dict                        |
| files   | 记录请求上传的文件             | MultiDict[str: FileStorage] |
| url     | 获取请求的API URL完整路径      | str                         |
| path    | 获取请求的API URL路径          | str                         |



### args

获取URL中的查询参数

```python
# http://127.0.0.1:5000/?name=zs&age=20

@app.route("/")
def index():
    data = request.args
    # ImmutableMultiDict([('name', 'zs'), ('age', '20')])
    
    print(data.get("name")) # zs
    print(data.get("age")) # 20
    
    return data
```



### form

拿到 `form-data` 或 `x-www-form-urencoded` 请求中的数据

```python
@app.route("/", methods=["POST"])
def index():
    data = request.form
    # ImmutableMultiDict([('name', 'zs'), ('age', '20')])

    return data
```



### json

拿到 `json` 请求的数据

```python
@app.route("/", methods=["POST"])
def index():
    data = request.json

    return data
```



### files

获取上传的文件对象

```python
@app.route("/", methods=["POST"])
def index():
    data = request.files
    # ImmutableMultiDict([('file', <FileStorage: 'b553f564f81a80dc338695acb1b475d2.jpg' ('image/jpeg')>)])

    file = data.get("file")
    # < FileStorage: 'avatar.jpg'('image/jpeg') >

    print(file.filename) # 完整文件名：avatar.jpg
    print(file.name) # 文件名：avatar

    return "获取文件成功"
```

**注意：** 上传文件使用 `form-data` 方式才能被接收到



**文件上传**

```python
@app.route("/", methods=["POST"])
def index():
    data = request.files

    file = data.get("file")

    path = app.root_path
    print(path)  # 项目的路径：C:\Users\33111\Desktop\flask
    print(file.filename)  # 文件名称：avatar.jpg

    # 上传到根目录
    # file.save(os.path.join(path, file.filename))

    # 上传到upload目录
    os.makedirs(path + "\\upload", exist_ok=True)  # 如果目录不存在就创建一个upload
    file.save(os.path.join(path + "\\upload", file.filename))

    return "上传文件成功"
```



### url & path

获取请求的 `url` 路径

```python
# http://127.0.0.1:5000/api/article

@app.route("/")
def index():
    print(request.url) # http://127.0.0.1:5000/api/article
    print(request.path) # /api/article
    
    return f"<h1>Hello World!</h1>"

```



## 响应

### 静态资源

默认情况下所有的静态资源都放在 `static` 目录中，假如目录中有个 `avatar.jpg` 图片资源，可以通过 `http://127.0.0.1:5000/static/avatar.jpg` 访问到这个资源



如果想要自定义静态资源存储路径，可以这么做

```python
app = Flask(__name__, static_folder="image")
# http://127.0.0.1:5000/image/avatar.jpg
```



默认情况下访问路径就是资源存储的路径，也可以自定义访问的路径

```python
app = Flask(__name__, static_folder="image", static_url_path="/")
# http://127.0.0.1:5000/avatar.jpg
```

将资源存储在 `image` 目录中，通过 `/` 直接访问资源



### 三个返回值

`Flask` 中 **视图函数的返回值可以设置三个**, 分别对应 **响应体, 响应状态码, 响应头**

```python
@app.route("/")
def index():
    #      返回数据        状态码    请求头
    return "Hello World!", 200, {'A': 1024}

```



### 重定向

`Flask` 中通过 `redirect()` 实现重定向功能

```python
from flask import Flask, redirect

app = Flask(__name__)


@app.route("/home")
def index():
    return "Hello World!"

@app.route("/www")
def www():
    # 重定向到指定网站
    # return redirect('http://liuyuyang.net/')

    # 重定向到指定路由
    return redirect('/home')
```



## 异常处理

```python
from flask import Flask, abort

app = Flask(__name__)


# 捕获http异常：如果访问不存在的路由就会触发该函数
@app.errorhandler(404)
def error_404(error):
    print(error)
    # 404 Not Found: The requested URL was not found on the server. If you entered the URL manually please check your spelling and try again.
    return "<h1>你访问的页面不存在</h1>"


# 捕获全局异常: 所有的异常都可以被捕获到
@app.errorhandler(Exception)
def error_overall(error):
    print(error)
    # name 'func' is not defined
    return f"捕获到了异常：{error}"


@app.route("/")
def index():
    # 故意定义一个没有的函数引发异常 
    func(123)

    return "Hello World!"


if __name__ == "__main__":
    app.run(debug=True)

```



## 请求钩子

请求钩子可以对请求的各阶段进行监听, 方便开发者 **针对请求完成一些统一的处理**， **以便减少重复代码**

![img](image/请求钩子.png)

```python
# 每次执行视图函数之前调用, 对请求进行一些准备处理, 如参数解析, 黑名单过滤, 数据统计等
@app.before_request
def prepare():
    print("before_request")


# 每次执行视图函数之后(已经包装为响应对象)调用, 对响应进行一些加工处理, 如设置统一响应头, 设置数据的外层包装
@app.after_request
def process(response: Response):
    print("after_request")

    print(response.headers)  # 请求头
    print(response.data)  # 响应的数据
    print(response.status_code)  # 状态码

    return response

```



## 配置

将类作为配置加载到 `app`

```python
from flask import Flask

class BaseConfig(object):
    # 请求路径前缀
    URLPREFIX = "/api"


app = Flask(__name__)

app.config.from_object(BaseConfig)
print(app.config.get("URLPREFIX")) # /api


@app.route('/')
def Home():
    return "Hello World!"


if (__name__ == "__main__"):
    app.run(debug=True, port=7777)
```



应用场景

```python
# 配置基类
class BaseConfig(object):
    # 请求路径前缀
    URLPREFIX = "/api"


# 开发环境
class DevelopConfig(BaseConfig):
    SQLALCHEMY_DATABASE_URI = 'mysql://root:123123@127.0.0.1:3306/student_py'


# 生产环境
class ProduceConfig(BaseConfig):
    SQLALCHEMY_DATABASE_URI = 'mysql://root:123123@127.0.0.1:3306/student_py'


# 选择环境
switch = {
    "dev": DevelopConfig,
    "pro": ProduceConfig
}
```



**注意：** 只有大写的属性才会被加载到 `app.config` 中

```python
class BaseConfig(object):
    aaa = 100
    BBB = 200

app.config.from_object(BaseConfig)
print(app.config.get("aaa")) # None
print(app.config.get("BBB")) # 200
```



## SQLalchemy

**安装**

```python
pip3 install flask-sqlalchemy
```

- 在安装/使用过程中，如果出现 `ModuleNotFoundError: No module named 'MySQLdb'` 错误，则表示缺少`MySQL` 依赖包，可依次尝试下列两个方案后重试：
- 方案1：安装 `mysqlclient`依赖包 (如果失败再尝试方案2)

```python
pip3 install mysqlclient
```

- 方案2：安装`pymysql`依赖包

```python
pip3 install pymysql
```

`mysqlclient` 和 `pymysql` 都是用于mysql访问的依赖包, 前者由C语言实现的, 而后者由python实现, 前者的执行效率比后者更高, 但前者在windows系统中兼容性较差, 工作中建议优先前者



### 组件初始化

`flask-sqlalchemy` 的相关配置也封装到了 `flask` 的配置项中, 可以通过 `app.config` 属性 或 配置加载方案 (如 `config.from_object` ) 进行设置

| 配置项                         | 说明                                                       |
| :----------------------------- | :--------------------------------------------------------- |
| SQLALCHEMY_DATABASE_URI        | 设置数据库的连接地址                                       |
| SQLALCHEMY_BINDS               | 访问多个数据库时, 用于设置数据库的连接地址                 |
| SQLALCHEMY_ECHO                | 是否打印底层执行的SQL语句                                  |
| SQLALCHEMY_RECORD_QUERIES      | 是否记录执行的查询语句, 用于慢查询分析, 调试模式下自动启动 |
| SQLALCHEMY_TRACK_MODIFICATIONS | 是否追踪数据库变化(触发钩子函数), 会消耗额外的内存         |
| SQLALCHEMY_ENGINE_OPTIONS      | 设置针对 `sqlalchemy`本体的配置项                          |

 

数据库URI(连接地址)格式：`协议名://用户名:密码@数据库IP:端口号/数据库名` 如：

```python
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:mysql@127.0.0.1:3306/test31'
```

- 如果数据库驱动使用的是 **pymysql**, 则协议名需要修改为 `mysql+pymysql://xxxxxxx`
- `sqlalchemy` 支持多种关系型数据库, 其他数据库的URI可以查阅 [官方文档](https://flask-sqlalchemy.palletsprojects.com/en/2.x/config/#connection-uri-format)

```python
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# 设置数据库连接地址
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:mysql@127.0.0.1:3306/test31'
# 是否追踪数据库修改(开启后会触发一些钩子函数)  一般不开启, 会影响性能
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
# 是否显示底层执行的SQL语句
app.config['SQLALCHEMY_ECHO'] = True

# 初始化组件对象, 直接关联Flask应用
db = SQLAlchemy(app)
```



### 构建模型类

![img](image/ORM映射.png)

```python
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# 相关配置
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:mysql@127.0.0.1:3306/test31'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SQLALCHEMY_ECHO'] = True

# 创建组件对象
db = SQLAlchemy(app)


# 构建模型类  类->表  类属性->字段  实例对象->记录
class User(db.Model):
    __tablename__ = 'user'  # 设置数据表名, 表名默认为类名小写
    
    id = db.Column(db.Integer, primary_key=True)  # 设置主键, 默认自增
    name = db.Column('username', db.String(20), unique=True)  # 设置字段名 和 唯一约束
    age = db.Column(db.Integer, default=10, index=True)  # 设置默认值约束 和 索引


if __name__ == '__main__':
    with app.app_context():
        # 删除所有继承自db.Model的表
    	db.drop_all()
        # 创建所有继承自db.Model的表
        db.create_all()
    
    app.run(debug=True)
```

**注意：** 如上所述我们将第一个字段定义为 `username` 那么数据库中的字段名就是 `username` ，如果第一个字段不填那么字段名就是变量名 `name` 



**总结**

- 模型类必须继承 **db.Model** 其中 **db** 指对应的组件对象
- 表名默认为类名小写，可以通过 `__tablename__` 类属性 进行修改
- 类属性对应字段，必须是通过 **db.Column()** 创建的对象
- 可以通过 `create_all()` 和 `drop_all()`方法 来创建和删除所有模型类对应的表



### 字段类型

| 类型名   | Python 接收的类型 | MySQL 生成的类型 | 说明                            |
| :------- | :---------------- | :--------------- | :------------------------------ |
| Integer  | int               | int              | 整型                            |
| Float    | float             | float            | 浮点型                          |
| Boolean  | bool              | tinyint          | 布尔型，只占1个字节             |
| Text     | str               | text             | 文本类型，最大64KB              |
| LongText | str               | longtext         | 文本类型，最大4GB               |
| String   | str               | varchar          | 变长字符串，**必须限定长度**    |
| Time     | datetime.time     | time             | 时间：12:30:45                  |
| Date     | datetime.date     | date             | 日期：2023-09-09                |
| DateTime | datetime.datetime | datetime         | 日期和时间：2023-09-09 12:30:45 |



### 字段选项

| 选项名        | 说明                                                   |
| :------------ | :----------------------------------------------------- |
| autoincrement | 默认为True，表示该字段自增，如果为False表示不自增      |
| primary_key   | 如果为 True，表示该字段为表的主键，默认自增            |
| unique        | 如果为 True，代表这列设置唯一约束，数据不能重复        |
| nullable      | 如果为 False，代表这列字段可以为空（默认True不能为空） |
| default       | 为这列设置默认值，优先级大于 nullable                  |
| index         | 如果为 True，为这列创建索引，提高查询效率              |

**注意：** 如果没有给对应字段的类属性设置 `default` 参数, 且添加数据时也没有给该字段赋值，则 `sqlalchemy` 会给该字段设置默认值 `None`



### 不记录到数据库

如果有些属性我们不想让他记录到数据库中，仅仅为了给接口使用，那么就不需要给他定义 `db.Column()`

类似于 `Spring Boot` 框架中的 `Mybatis-plus` 的 `@TableField` 功能

```python
class User(db.Model):  
    id = db.Column(db.Integer, primary_key=True)  
    username = db.Column(db.String(80), unique=True, nullable=False)  
    email = db.Column(db.String(120), unique=True, nullable=False)  
    info = 1  # 注意这里只是定义了属性，并没有将其作为数据库列
```



### 增删改查

#### 新增数据

```python
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# 相关配置
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:123123@127.0.0.1:3306/ceshi'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SQLALCHEMY_ECHO'] = True

# 创建组件对象
db = SQLAlchemy(app)


# 构建模型类
class User(db.Model):
    __tablename__ = 'user'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column('username', db.String(20), unique=True)
    age = db.Column(db.Integer, index=True)


@app.route('/')
def index():
    # 创建模型对象并给name与age字段添加数据
    user = User(name='zs', age=20)
    # or
    # user.name = 'zs'
    # user.age = 20

    # 将模型对象添加到会话中
    db.session.add(user)

    # 也可以添加多条数据
    # db.session.add_all([user1, user2, user3])

    # 提交会话 (会提交事务)
    db.session.commit()

    return "index"


if __name__ == '__main__':
    with app.app_context():
        db.drop_all()
        # 创建数据库表
        db.create_all()
        
    app.run(debug=True)

```



#### 查询数据

##### 查询执行器

| 方法                     | 说明                                               |
| :----------------------- | :------------------------------------------------- |
| one()                    | 返回符合查询的单个模型对象，如果没有或多个都会报错 |
| all()                    | 返回列表, 元素为所有符合查询的模型对象             |
| count()                  | 返回查询结果的数量                                 |
| first()                  | 返回符合查询的第一个模型对象，如果未查到，返回None |
| first_or_404()           | 返回符合查询的第一个模型对象，如果未查到，返回404  |
| get(主键)                | 返回主键对应的模型对象，如不存在，返回None         |
| get_or_404(主键)         | 返回指定主键对应的模型对象，如不存在，返回404      |
| paginate(页码, 每页条数) | 返回一个Paginate对象，它包含分页查询的结果         |



##### 查询过滤器

| 过滤器                    | 说明                                                  |
| :------------------------ | :---------------------------------------------------- |
| filter_by(字段名=值)      | 把等值过滤器添加到原查询上，返回BaseQuery对象         |
| filter(函数引用/比较运算) | 把过滤器添加到原查询上，返回BaseQuery对象             |
| limit(限定条数)           | 使用指定的值限定原查询返回的结果，返回BaseQuery对象   |
| offset(偏移条数)          | 根据指定的值按照原查询进行偏移查询，返回BaseQuery对象 |
| order_by(排序字段)        | 根据指定条件对原查询结果进行排序，返回BaseQuery对象   |
| group_by(分组字段)        | 根据指定条件对原查询结果进行分组，返回BaseQuery对象   |
| options()                 | 针对原查询限定查询的字段，返回BaseQuery对象           |



##### 基本查询

```python
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# 相关配置
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:123123@127.0.0.1:3306/ceshi'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SQLALCHEMY_ECHO'] = True

# 创建组件对象
db = SQLAlchemy(app)


# 构建模型类
class User(db.Model):
    __tablename__ = "users"  # 表名 默认使用类名的小写
    # 定义类属性 记录字段
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(64))
    email = db.Column(db.String(64))
    age = db.Column(db.Integer)


@app.route('/')
def index():
    # 查询所有用户数据
    User.query.all()

    # 查询有多少个用户
    User.query.count()

    # 查询第一个用户
    User.query.first()

    # 查询id为4的用户[2种方式]
    User.query.filter_by(id=4).all()
    User.query.filter(User.id == 4).first()

    # 查询名字结尾字符为g的所有数据[开始/包含/结尾]
    User.query.filter(User.name.startswith('w')).all()
    User.query.filter(User.name.contains('n')).all()
    User.query.filter(User.name.endswith('g')).all()
    # 查询w开头 | g结尾 | 并且包含n的所有数据
    User.query.filter(User.name.like('w%n%g')).all()

    # 查询名称与邮箱都以 li 开头的所有数据
    User.query.filter(User.name.startswith('li'), User.email.startswith('li')).all()

    return "index"


if __name__ == '__main__':
    with app.app_context():
        db.drop_all()
        # 创建数据库表
        db.create_all()

        # 创建测试数据
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

        # 一次性添加多条数据
        db.session.add_all([user1, user2, user3, user4, user5, user6, user7, user8, user9, user10])

        # 将数据提交到数据库
        db.session.commit()

    app.run(debug=True)

```



##### 逻辑查询

```python
	# 查询用户名称不等于wang的所有数据
    from sqlalchemy import not_
    User.query.filter(not_(User.name == "wang")).all()
    User.query.filter(User.name != "wang").all()

    # 查询名称为wang或者年龄为23的所有数据
    from sqlalchemy import or_
    User.query.filter(or_(User.name == "wang", User.age == 23)).all()

    # 查询名称为wang 并且年龄为23的所有数据
    from sqlalchemy import and_
    User.query.filter(and_(User.name == "wang", User.age == 23)).all()
    User.query.filter(User.name == "wang", User.age == 23).all()

    # 查询id为1 3 5 7 9的数据
    User.query.filter(User.id.in_([1, 3, 5, 7, 9])).all()
```



##### 顺序查询

```python
	# 年龄从小到大排序
    User.query.order_by(User.age.desc()).all()
    User.query.order_by(User.age.desc()).limit(5).all()  # 取前5个
    # 年龄从大到小排序
    User.query.order_by(User.age).all()  # 默认从小到大
    User.query.order_by(User.age.asc()).all()

    # 年龄从小到大排序 再按照id从大到小排序
    User.query.order_by(User.age, User.id.desc()).all()
    # 年龄从小到大排序 再按照id从大到小排序 取前5个
    User.query.order_by(User.age, User.id.desc()).limit(5).all()

    # 从第二条数据开始 取4条数据：2 3 4 5
    User.query.offset(1).limit(4).all()
    # 查询年龄从小到大排序后的第2-5个数据：2 3 4 5
    User.query.order_by(User.age).offset(1).limit(4).all()
```



##### 分页查询

```python
# 默认不传page或size, 则从第一页开始显示5条
page = request.args.get("page", 1, type=int)
size = request.args.get("size", 5, type=int)

data = UserModel.query.paginate(page=page, per_page=size, error_out=False)
```

`error_out` 默认值为 `True`，表示当前请求的页数超过了可用的页数时抛出一个错误，而设置为 `False` 表示超过了可用的页数时返回一个空数组，而不是抛出报错。



**分页中常用的属性**

| 属性名   | 描述               |
| -------- | ------------------ |
| page     | 当前页码           |
| pages    | 总页数             |
| total    | 数据总数           |
| has_prev | 是否有上一页       |
| has_next | 是否有下一页       |
| prev_num | 上一页的页码       |
| next_num | 下一页的页码       |
| per_page | 每页显示的数据条数 |



##### 分组查询



#### 修改数据

**方法一：** 查询和更新分两条语句，效率低。并且如果并发更新，可能出现更新丢失问题

```python
@app.route('/')
def index():
    # 查询数据
    data = User.query.filter(id = 2).first()

    if not data:
        return "修改失败：数据不存在"

    # 修改数据
    data.age = data.age + 10
    # 提交会话
    db.session.commit()

    return "修改成功"
```



**方法二：** 效率更高、批量更新、单条SQL具有原子性, 不会出现更新丢失的问题 **【推荐写法】**

```python
@app.route('/')
def index():
    # 查询数据
    # update user set age = age + 10 where id = 2; 效率高
    data = User.query.filter(User.id == 2).update({'age': User.age + 10})
    
    if not data:
        return "修改失败：数据不存在"

    # 提交会话
    db.session.commit()

    return "修改成功"
```



#### 删除数据

**方法一**

```python
@app.route('/')
def index():
    # 查询数据
    data = User.query.filter(User.id == 2).first()

    if not data:
        return "删除失败：数据不存在"

    db.session.delete(data)

    # 提交会话
    db.session.commit()

    return "删除成功"
```



**方法二**

```python
@app.route('/')
def index():
    # 查询数据
    data = User.query.filter_by(id = 2).delete()

    if not data:
        return "删除失败：数据不存在"

    # 提交会话
    db.session.commit()

    return "删除成功"
```



## 文件上传

### 本地上传

```python
@app.route("/upload",methods=["POST"])
def update():
    try:
        # 获取上传的文件对象
        file = request.files['file']
    except:
        return response(400, "请先上传文件")
        
    # 先判断该目录是否存在
    isDir = os.path.exists("upload")

    # 如果不存在就创建一个
    if not isDir: os.mkdir("upload")

    # 获取上传的文件名
    FileName = secure_filename(file.filename)

    # 将文件上传到指定的目录
    file.save(os.path.join("upload", FileName))

    return response(200, "上传文件成功", {"url": request.host_url + '123.jpg'})
```



**os.path.join**

`os.path.join` 的作用是将上传的文件保存到指定的目录中，如果目录中已经存在了同名文件，则不会重新上传一个新文件而是覆盖原有文件。如果目录中不存在同名文件，才会创建一个新文件并保存上传的文件内容



**secure_filename**

`secure_filename` 是 `Flask` 框架提供的一个安全文件名生成函数，用于将文件名转换为安全的文件名，以避免一些潜在的安全问题。**他不是必须的**

具体来说，它的作用有以下几个方面：

1. 删除文件名中的非法字符：在不同操作系统中，文件名可能包含一些非法字符，如斜杠、反斜杠、冒号等，这些字符可能会导致文件操作失败或产生安全问题。`secure_filename`函数会删除文件名中的这些非法字符，以确保文件名的合法性。

2. 防止跨站脚本攻击：如果文件名中包含一些特殊字符，如尖括号、单引号、双引号等，可能会导致跨站脚本攻击。`secure_filename`函数会将这些特殊字符转换为相应的HTML实体，以防止跨站脚本攻击。

3. 避免文件名冲突：在文件上传过程中，可能会出现多个用户上传同名文件的情况，这会导致文件名冲突。`secure_filename`函数会为每个文件生成一个唯一的文件名，以避免文件名冲突。

   **假如上传两个同名文件：**

   `secure_filename` 函数会为每个文件生成一个唯一的文件名，以避免文件名冲突。例如，如果上传两个名为 `example.jpg `的文件，`secure_filename` 函数会将它们分别重命名为类似于 `example_1.jpg `和 `example_2.jpg` 的唯一文件名，以避免文件名冲突。

   具体来说，`secure_filename` 函数会在文件名后面添加一个随机字符串，以确保每个文件名的唯一性。例如，如果上传的文件名为 `example.jpg`，则 `secure_filename` 函数可能会将其重命名为`example_5f5c9d8a.jpg`。这样，即使上传多个同名文件，它们的文件名也会是唯一的，不会产生冲突。



### 远程上传

将文件上传到远程 **对象存储** ，就拿七牛云为例：

```python
from flask import Flask

from qiniu import Auth, put_file, etag

AK = "xpquCtc-v1t-M3wY3b8WVCVACS1viMpUNY6aZPzg"
SK = "1h-V_vCrOt2UIoHf4x_Rj4GxfjsW_IINRz0VyzFQ"

# 鉴权
q = Auth(AK, SK)

# 存储桶名称
BucketName = "liuyuyang1024"

app = Flask(__name__)


@app.route("/upload", methods=["POST"])
def index():
    try:
        # 上传后保存的文件名
        key = 'avatar.jpg'

        # 生成上传 Token，用于将文件上传到七牛云的凭证，可以指定过期时间等
        token = q.upload_token(BucketName, key, 3600)
        # 3600为token过期时间，秒为单位。3600等于一小时

        # 要上传的文件 本地路径
        localfile = './image/avatar1.jpg'
        ret, info = put_file(token, key, localfile, version='v2')
        print(info)

        assert ret['key'] == key
        assert ret['hash'] == etag(localfile)
    except Exception as e:
        return str(e)

    return "上传成功!"


if __name__ == "__main__":
    app.run(debug=True)

```



## JWT

**安装**

```python
pip3 install pyjwt
```



### 生成一个Token

```python
import jwt

# 加密信息
SecretKey = "liuyuyang"

# 配置请求头信息
headers = {
	"typ": "jwt",
    "alg": "HS256"
}

    # 自定义的信息
payload = {
	"id": 1,
	"username": "liuyuyang1024",
	# 设置token 5天后到期
	"exp": datetime.utcnow() + timedelta(days=5)
}

# 生成Token
result = jwt.encode(
	headers=headers,
	payload=payload,
	key=SecretKey,
	algorithm="HS256",
)
    
# 生成一个Token
print("Token：", result)
```



### 配置过期时间

通过 `timedelta` 设置 `token` 的到期时间

| 参数         | 时间 |
| ------------ | ---- |
| days         | 天   |
| hours        | 时   |
| minutes      | 分   |
| seconds      | 秒   |
| microseconds | 微秒 |

**示例**

```python
from datetime import timedelta

# 设置时间间隔为 2 天、3 小时、30 分钟、15 秒
timedelta(days=2, hours=3, minutes=30, seconds=15)
```



### 身份认证

```python
from datetime import datetime, timedelta

from flask import Flask, request
import jwt

SecretKey = "liuyuyang"

app = Flask(__name__)


# 生成token
def CreateToken():
    # 配置请求头
    headers = {
        "typ": "jwt",
        "alg": "HS256"
    }

    # 自定义信息
    payload = {
        "id": 1,
        "username": "liuyuyang1024",
        # 设置Token为10秒后到期
        "exp": datetime.utcnow() + timedelta(seconds=10)
    }

    # 生成Token
    result = jwt.encode(
        payload=payload,
        key=SecretKey,  # 秘钥
        algorithm="HS256",  # 加密方式
        headers=headers
    )

    return result


# 调用此接口获取一个token用于测试
@app.route("/token")
def Home():
    token = CreateToken()
    return token


# 假设这个接口需要Token身份认证才能访问
@app.route("/admin", methods=["GET", "POST"])
def Login():
    # 接收json格式传递的数据
    data = request.get_json()
    token = data["token"]

    try:
        # 解析Token
        info = jwt.decode(token, key=SecretKey, algorithms=["HS256"], verify=False)
        return {"message": "登录成功", "Token": token}
    # Token过期处理
    except jwt.exceptions.ExpiredSignatureError:
        return {"message": "Token 已过期"}
    # Token认证失败处理
    except jwt.exceptions.InvalidSignatureError:
        return {"message": "Token 认证失败"}


if __name__ == "__main__":
    app.run(debug=True)
```



## scoket.io

**客户端 与 服务器交互**

**前端代码**

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<script src="https://cdn.socket.io/4.6.0/socket.io.min.js"
        integrity="sha384-c79GN5VsunZvi+Q/WObgk2in0CbZsHnjEqvFxC5DxHn9lTfNce2WW6h2pH6u/kF+"
        crossorigin="anonymous"></script>


<script type="text/javascript" charset="utf-8">
    // 如果前端与scoket.io 域名不一样，则需要指定域名：
    const socket = io("http://127.0.0.1:5000");

    // 像服务端发送消息
    socket.on('connect', function () {
        socket.emit('msg', {info: "哈哈哈哈哈哈哈哈哈"})
    });

    // 接收服务器发送的消息
    socket.on('result', function (data) {
        console.log('这是服务端传递的消息：', data);
    });
</script>
</body>

</html>
```



**Flask代码**

```python
from flask import Flask
from flask_socketio import SocketIO, emit

app = Flask(__name__)

# 设置前端所有网站可以跨域
socketio = SocketIO(app, cors_allowed_origins="*")

# 设置指定需要跨域的域名
# socketio = SocketIO(app, cors_allowed_origins='http://localhost:63342')


# 这里接收到消息后会自动触发result函数
@socketio.on('msg')
def result(data):
    # 接收客户端发送的消息
    print('这是客户端传递的消息：' + data['info'])

    # 向客户端发送一段消息
    emit("result", "呵呵呵呵呵呵呵呵呵呵")


if __name__ == '__main__':
    # 通过：allow_unsafe_werkzeug = True 来禁用werkzeug服务器的安全机制，否则会报错
    socketio.run(app, allow_unsafe_werkzeug=True, port=5000, debug=True)

```



## requirements

**创建依赖**

```
pip3 freeze > requirements.txt
```



**安装依赖**

```
pip3 install -r requirements.txt
```



**卸载依赖**

```
pip3 uninstall -y -r requirements.txt
```

