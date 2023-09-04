# Flask

## 开始

### 虚拟环境



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



### args

获取URL中的查询参数

```python
# http://127.0.0.1:5000/?name=zs&age=20

@app.route("/")
def index():
    print(request.args)
    # ImmutableMultiDict([('name', 'zs'), ('age', '20')])
    
    print(request.args.get("name")) # zs
    print(request.args.get("age")) # 20
    
    return f"<h1>Hello World!</h1>"

```



### form

拿到 `form-data` 或`x-www-form-urencoded` 请求中的数据

```python
@app.route("/", methods=["POST"])
def index():
    print(request.form)
    # ImmutableMultiDict([('name', 'zs'), ('age', '20')])
    
    print(request.form.get("name"))  # zs
    print(request.form.get("age"))  # 20
    
    return f"<h1>Hello World!</h1>"
```



### json

拿到 `json` 请求的数据

```python
@app.route("/", methods=["POST"])
def index():
    print(request.json)
    # {'name': 'zs', 'age': 20}
    
    print(request.json.get("name"))  # zs
    print(request.json.get("age"))  # 20
    
    return f"<h1>Hello World!</h1>"
```



### files

获取上传的文件对象

```python
@app.route("/", methods=["POST"])
def index():
    print(request.files)
    # ImmutableMultiDict([('file', <FileStorage: 'b553f564f81a80dc338695acb1b475d2.jpg' ('image/jpeg')>)])

    print(request.files.get("file"))  # zs
    # < FileStorage: 'b553f564f81a80dc338695acb1b475d2.jpg'('image/jpeg') >
    
    return f"<h1>Hello World!</h1>"
```

**注意：** 上传文件使用 `form-data` 方式才能被接收到



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
    #          返回数据     状态码    请求头
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
    isDir = os.path.exists(app.config['UPLOAD_FOLDER'])

    # 如果不存在就创建一个
    if not isDir: os.mkdir(app.config['UPLOAD_FOLDER'])

    # 获取上传的文件名
    FileName = secure_filename(file.filename)

    # 将文件上传到指定的目录
    file.save(os.path.join(app.config['UPLOAD_FOLDER'], FileName))

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

