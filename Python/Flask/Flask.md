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