# Node.js

## 起步

加载模块

```javascript
// 使用核心模块之前，首先加载核心模块
let path = require('path');
```



### path

路径操作

#### baename

返回文件名   如：**index.js**

```JavaScript
let path = require('path');

let n = 'C:/Users/code/index.js';
console.log(path.basename(n)) //index.js
```



如果想要返回的文件名不加后缀，可以这么做

```javascript
let path = require('path');

let n = 'C:/Users/code/index.js';
console.log(path.basename(n,'.js')) //index
```



#### dirname

返回目录名   如：**C:/Users/code**

```JavaScript
let path = require('path');

let n = 'C:/Users/code/index.js';
console.log(path.dirname(n)) //C:/Users/code
```



#### extname

返回文件后缀   如：**.js**

```JavaScript
let path = require('path');

let n = 'C:/Users/code/index.js';
console.log(path.extname(n)) //.js
```



#### join

拼接路径   如：**C:/Users/code/ + index.js = C:\Users\code\index.js**

```javascript
let path = require('path');

let n = 'C:/Users/code/';
let m = 'index.js'
console.log(path.join(n,m)) //C:\Users\code\index.js
```



#### format

将对象转换为字符串路径

```javascript
let path = require('path');

let n = path.format({
    dir: 'C:\\Users\\code',
    base: 'index.js'
});
console.log(n) //C:\Users\code\index.js
```



#### parse

将字符串路径转换为对象

```JavaScript
let path = require('path');

let n = 'C:/Users/code/index.js';

console.log(path.parse(n))

// 返回结果如下：
/*
{
  root: 'C:/',
  dir: 'C:/Users/code',
  base: 'index.js',
  ext: '.js',
  name: 'index'
}
*/
```



#### __dirname

返回当前的文件的绝对路径

```JavaScript
console.log(__dirname) //C:\Users\33111\Desktop\Demo
```



#### resolve

合并路径



#### 扩展

#### ../

```JavaScript
let path = require('path');

console.log(path.join('/foo', 'bar', 'quux'))
```

正常拼接：\foo\bar\quux



`.` 跟 `./` 是返回当前路径

```JavaScript
console.log(path.join('/foo', 'bar', 'quux','.'))
// \foo\bar\quux

console.log(path.join('/foo', 'bar', 'quux','./'))
// \foo\bar\quux\
```



而 `..` 跟 `../` 是返回上级路径

```JavaScript
console.log(path.join('/foo', 'bar', 'quux','..'))
// \foo\bar

console.log(path.join('/foo', 'bar', 'quux','../'))
// \foo\bar\
```



### fs

文件操作

#### readFile

读取文件内容   如：读取 `text.txt` 的内容

**注意：**路径文件记得加单位

```JavaScript
let fs = require('fs');

fs.readFile('./text.txt',(err,data)=>{
    console.log(data)
    // 输出结果：<Buffer e5 88 98 e5 ae 87 e9 98 b3>
})
```

他的输出结果是： `<Buffer e5 88 98 e5 ae 87 e9 98 b3>` 二进制代码，并不是我们想要的结果。所以需要先 `设置为utf-8模式` 或者 `转换为字符串`



转换为正常文本

```JavaScript
let fs = require('fs');

// 设置为utf-8模式
fs.readFile('./text.txt','utf-8',(err,data)=>{
    console.log(data) //刘宇阳
})

// 或者用toString转换为字符串
fs.readFile('./text.txt',(err,data)=>{
    console.log(data.toString()) //刘宇阳
})
```



- 它的参数第一个参数 `err` 是失败后执行
- 第二个参数`data` 是成功执行，参数名可以自定义



**读取成功**

```JavaScript
let fs = require('fs');

fs.readFile('./text.txt','utf-8',(err,data) => {
    if(err){
        return console.log('读取失败！')
    }

    console.log('读取成功') //读取成功
    console.log(data) //刘宇阳
})
```



**读取失败**

```JavaScript
let fs = require('fs');

// 把路径改错，使加读取失败
fs.readFile('./text.txt','utf-8',(err,data) => {
    // 读取失败，err就有值 / 读取成功，err值就是null
    // err有值就是true 没有值就是false
    if(err){
        return console.log('读取失败！') //读取失败
    }

    console.log('读取成功')
    console.log(data)
})
```



#### writeFile

写入文件内容   如：修改 `text.txt` 的内容

```JavaScript
let fs = require('fs');

fs.writeFile('./text.txt','Hello World!', () => {})
```



#### readdir

**获取指定目录下的文件名称**

```javascript
const fs = require("fs");

fs.readdir(__dirname + "/docs", (err, res) => {
  console.log(res);
  // [ '1.md', '2.md', '3.md' ]
});
```



**同步方式**

```javascript
const res = fs.readdirSync(__dirname + "/docs")
```



#### extname

获取指定文件的后缀

```javascript
const path = require("path");

console.log(path.extname("index.html")); //.html
console.log(path.extname("index.css")); //.css
console.log(path.extname("index.js")); //.js
```



当前文件夹下指定后缀的文件

```javascript
const fs = require("fs");
const path = require("path");

fs.readdir(__dirname + "/docs", (err, data) => {
  const res = data.filter((item) => path.extname(item) === ".md");
  console.log(res);
  // [ '1.md', '2.md', '3.md' ]
});
```



#### 整理成绩案例

将 `text.txt` 文件中的：`小明=100 小王=98 小亮=97 小红=97` 每个 等于号 **(=)** 换成 冒号 **(：)** 并且换行显示。

**如下效果：**

小明：100 

小王：98 

小亮：97 

小红：97



**代码实现**

```JavaScript
const fs = require('fs');

// 读取文件数据
fs.readFile('./text.txt','utf-8',function(err,data){
    if(err) return console.log(err);

    // 用于合并数据
    let str = '';

    // 将数据以空格转换为字符串
    let arr = data.split(' ');

    // 循环格式
    arr.forEach(item=>{
        str += item.split('=').join('：')+'\n'
    })

    // 写入文件数据
    fs.writeFile('./text.txt',str,function (){})
})
```



## 创建Web服务

```javascript
// 加载http模块
let http = require('http');

// 创建Web服务对象
let server = http.createServer();

// 注册request事件
server.on('request',function(req,res){
    console.log('收到客户端请求')
    res.end('Hello') //响应数据
})

// 绑定端口号，启动服务
server.listen(8080,function(){
    console.log('服务器启动成功了，可以通过 http://127.0.0.1:8080/ 来进行访问');
})
```

**注意：**`res.end(这里只能写字符串哦！)` 如果数组或对象，可以使用 `JSON.stringify` 转换为字符串



**req**

`req.url` 获取请求的 URL 地址

`req.method` 获取是以什么方式请求的接口

例如使用POST请求：`http://127.0.0.1:80/index.html`

```javascript
serve.on("request", (req, res) => {
  console.log(req.url); //index.html
  console.log(req.method); //POST
  res.end("你好：Nodejs");
});
```



**res**

向服务器发送请求



### 解决页面中文乱码问题

服务器接口 `res.end` 返回给页面中的数据默认会乱码，以下可以解决中文乱码问题

```javascript
const http = require("http");

const serve = http.createServer();

serve.on("request", (req, res) => {
  // 调用 res.setHeader() 方法，设置Content-Type 响应头 解决中文乱码问题
  res.setHeader("Content-Type", "text/html; charset=utf-8");

  res.end("你好：Nodejs");
});

serve.listen(80, () => {
  console.log("服务器已启动：http://127.0.0.1:80");
});

```



### 练习

**根据不同请求页面显示不同内容**

```javascript
serve.on("request", (req, res) => {
  const url = req.url;
  console.log(url);

  // 调用 res.setHeader() 方法，设置Content-Type 响应头 解决中文乱码问题
  res.setHeader("Content-Type", "text/html; charset=utf-8");

  // 找不到就显示404页面
  let content = "<h1>404页面</h1>";

  if (url === "/" || url === "/index.html") {
    content = "<h1>首页</h1>";
  } else if (url === "/cate.html") {
    content = "<h1>分类页面</h1>";
  }

  res.end(content);
});
```



**将项目运行到服务器并且默认打开index.html文件**

```javascript
serve.on("request", (req, res) => {
  const url = req.url;

  let fpath = '';
  // 如果请求的路径是 / 则手动指定文件的存放路径
  if(url === '/'){
    fpath = path.join(__dirname,'./view/index.html');
  }else{
    // 如果请求的路径不为 / 则动态拼接文件的存放路径
    fpath = path.join(__dirname,'./view',url);
  }

  console.log(fpath);

  // 请求哪个页面，就显示对应页面的内容
  fs.readFile(fpath,(err,data)=>{
    if(err) return res.end('<h1>404页面</h1>')

    res.end(data)
  })
});
```



## 模块化

模块化有两种规范，分别是：`ES6` 与 `commonJS`，而默认情况下是：commonJS



### ES6模块化规范

如果需要使用ES6模块化需要打开 `package.json` 添加一行代码：`"type": "module"` 即可使用 `ES6` 模块化规范

**导出**

```JavaScript
// 默认导出
export default {
    name:'刘宇阳',
    fun:function(){}
}

// 按需导出
export let age = 19;
```



**导入**

```JavaScript
import obj from './txt.js'; //默认导出不需要加花括号
import {age} from './txt.js'; //按需导出需要加花括号

console.log('我叫：' + obj.name + ' 今年：' + age + '岁') //我叫：刘宇阳 今年：19岁
```

ES6规范模块化的路径文件必须要加后缀 `.js`    比如：`'./txt.js'`

`commonJS` 可加可不加



### CommonJS

**按需导出**

```javascript
module.exports.name = "刘宇阳"
```

**默认导出**

```javascript
module.exports = {
    name:"刘宇阳"
}
```



**exports**

由于 `module.exports`  写起来比较复杂，为了简化向外共享成员的代码，Node提供了 `exports` 对象。默认情况下 exports 和 module.exports 指向的同一个对象。最终共享的结果还是以 module.exports 指向的对象为准。

```javascript
console.log(module.exports === exports); //true
```



**导入**

```javascript
const obj = require('./module');
console.log(obj);
```



**注意：**

1. 导入内置或者第三方模块不需要加路径，而导入自定义模块需要加上路径
2. 导入的自定义模块文件可以加后缀也可以不加，默认就是 `module.js`



## npm

`package.json` 记录当前项目所依赖模块的版本信息，更新模块时锁定模块的大版本号（版本号的第一位）。

`package-lock.json` 记录了node_modules目录下所有模块的具体来源和版本号以及其他的信息。



### 初始化

安装本地模块，需要使用 `npm` 工具初始化。

`npm init` 或 `npm init -y` 

初始化之后，会在项目目录中生成 `package.json` 的文件。



### 安装与卸载

#### 安装

```bash
# 正常的下载安装
npm install 模块名

# 简写install为i
npm i 模块名

# 一次性安装多个模块
npm i 模块名 模块名 模块名
```



#### 卸载

```bash
npm uninstall 模块名
npm un 模块名
npm un 模块名 模块名 模块名
```



#### 更新

假如说你安装了某个包，当前版本是 `2.0`。过了几天包更新到了 `3.0`，如果你想要更新，不需要删除旧的包，直接：`npm i 包名` 即可覆盖之前旧的包，保留最新的。



### 切换镜像源

由于 `npm` 默认镜像是国外的，在安装 `npm` 包时候会下载很慢，所以我们需要切换到国内的 `淘宝镜像源` ，淘宝镜像将 npm 包全部拷贝了一份，所以我们可以通过淘宝镜像来下载 npm 包，这样速度上会有明显提高



**查看镜像源**

```bash
npm config get registry
```



**切换到淘宝镜像源**

```bash
npm config set registry=https://registry.npm.taobao.org
https://registry.npm.taobao.org/

```



#### nrm

为了更方便的切换镜像源，我们可以安装 `nrm` 这个小工具，利用 `nrm` 提供的终端命令，可以快速的查看和切换镜像源

**安装**

```bash
npm i nrm -g
```



**查看可用的镜像源**

```bash
nrm ls
```



**将镜像源切换为 淘宝 镜像**

```bash
nrm use taobao
```



## Express

创建一个基本Web服务器

```javascript
// 导入express
const express = require('express')

// 创建Web服务器
const app = express()

// 调用app.listen(端口号，启动成功后的回调函数)，启动服务器
app.listen(80,()=>{
  console.log('express serve running at http://127.0.0.1/');
})
```



### 发起请求

```javascript
// 导入express
const express = require("express");

// 创建Web服务器
const app = express();

// 监听客户端的 GET 和 POST请求，并向客户端响应具体的内容
app.get("/user", (req, res) => {
  // 响应一个对象
  res.send({ name: "刘宇阳", age: 20, sex: "男" });
});

// post请求
app.post("/user", (req, res) => {
  // 响应一个字符串
  res.send("Hello");
});

// 调用app.listen(端口号，启动成功后的回调函数)，启动服务器
app.listen(80, () => {
  console.log("express serve running at http://127.0.0.1/");
});

```



### 处理参数

#### req.query

GET请求接口：`http://127.0.0.1/user?name=刘宇阳&age=20`

```javascript
app.get("/user", (req, res) => {
  console.log(req.query); //{ name: '刘宇阳', age: '20' }
});
```



#### req.params

GET请求接口：`http://127.0.0.1/user/1/刘宇阳`

```javascript
app.get("/user/:id/:name", (req, res) => {
  console.log(req.params); //{ id: '1', name: '刘宇阳' }
});
```



### 托管静态资源

`express` 提供了非常好用的函数：`express.static()` 通过它我们可以非常方便的创建一个静态资源服务器，例如通过如下代码就可以将 `public` 目录下的 `图片`、`HTML`、`CSS`、`JavaScript` 文件对外开放访问了

```javascript
app.use(express.static('public'))
```

`express` 在指定的静态目录中查找文件，并对外提供资源的访问路径，因此存放静态文件的目录名不会出现在 `URL` 中。



如果想要托管多个静态资源目录，请多次调用 `exports.static()` 函数

```javascript
app.use(express.static('public'))
app.use(express.static('view'))
```

访问静态资源文件时，`exports.static()` 函数会根据目录的添加顺序查找所需的文件

例如：`public` 与 `view` 都有 `index.html` 文件，如果访问了 `index.html` 就会触发 `public` 中的`index.html`，因为他在前，`view` 在后



**挂载路径前缀**

如果希望在托管的静态资源访问路径之前挂载路径前缀，这可以使用如下方式：

```javascript
app.use('/public',express.static('public'))
```

这样你可以通过带有 `/public` 前缀地址来访问 `public` 目录中的文件了



### 中间件

#### 内置中间件

快速托管静态资源的内置中间件

```javascript
app.use(express.static())
```

配置解析 `application/json` 格式数据

```javascript
app.use(express.json())
```

配置解析 `application/x-www-form-urlencoded` 格式数据

```javascript
app.use(express.urlencoded({extended:false}))
```



#### 错误处理中间件

如果不使用错误中间件，项目中某个地方发生错误，就会导致整个项目崩溃。所以就需要用到错误处理的中间件

```javascript
// 定义路由
app.get("/", (req, res) => {
  throw new Error("服务器内部发生了错误！");
  res.send("这里不会被触发");
});

// 定义错误中间件，捕获整个项目的异常错误，从而防止程序的崩溃
app.use((err, req, res, next) => {
  console.log("发生了错误：" + err.message);
  res.send("Error：" + err.message);
});
```



### 跨域

1. 安装 `cors`

```bash
npm install cors
# or
yarn add cors
```

2. 导入 `cors` 

```javascript
const cors = require('cors');
```

3. 安装 `cors` 到中间件

```javascript
app.use(cors());
```



### JWT

安装JWT相关包

```bash
yarn add jsonwebtoken express-jwt
```

**jsonwebtoken**：用于生成 `JWT` 字符串

**express-jwt**：用于将 `JWT` 字符串解析还原成 `JSON` 对象



#### 生成Token

使用 `JWT` 生成 `Token`  当登录成功后返回给客户端

```javascript
var express = require("express");
var router = express.Router();
const jwt = require("jsonwebtoken");
// token秘钥
const secretKey = "LiuYuYang ^_^";

router.post("/login", function (req, res, next) {
  const { username, password } = req.body;

  if (username != "admin" || password != "123123") {
    return res.send({ status: 0, msg: "登录失败!" });
  } else {
    // 生成Token
    // const token = jwt.sign({需要加密的信息}, 秘钥(secretKey), { expiresIn: "到期时间" });
    const token = jwt.sign({ username }, secretKey, { expiresIn: "30s" });

    res.send({
      status: 1,
      msg: "登录成功!",
      user: req.body,
      token
    });
  }
});

module.exports = router;
```



#### 校验Token

导入 `express-jwt` 

```javascript
const { expressjwt } = require("express-jwt");
```

设置到中间件

```javascript
app.use(
  expressjwt({ secret: secretKey, algorithms: ["HS256"] }).unless({
    path: [/^\/login],
  })
);
```

只有 `/login` 接口有权限访问，其他接口无权访问，除非在请求头 `Headers` 中的 `Authorization` 加上 `Token` 字符串发送到服务器进行身份认证



## 操作数据库

### 操作SQL

例子：查询数据表中所有数据

```javascript
const mysql = require("mysql");

const pool = mysql.createPool({
  connectionLimit: 150,
  host: "localhost",
  user: "root",
  password: "123123",
  database: "data",
});

pool.getConnection(function (err, db) {
  if (err) return;

  // 所有sql语句写在query的第一个参数
  db.query("select * from stu", function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



### 新增数据

```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  db.query("insert into stu values(0, '尤雨溪', 30, 175.6, '男')", function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



#### 快捷操作

```js
pool.getConnection(function (err, db) {
  if (err) return;

  let obj = { name: "尤雨溪", age: 30, height: 175.6, gender: "男" };
  
  // insert into stu values(0, '尤雨溪', 30, 175.6, '男');
  db.query("insert into stu set ?", obj, function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



```js
pool.getConnection(function (err, db) {
  if (err) return;

  // insert into stu values(0, '尤雨溪', 30, 175.6, '男');
  db.query("insert into stu values(?, ?, ?, ?, ?)", [0, '尤雨溪', 30, 175.6, '男'], function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



### 更新数据

```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  db.query("update stu set name='比尔盖茨',age=20,height=185,gender='男' where id = 2", function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



#### 快捷操作

```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  // update stu set name='比尔盖茨', age=20, height=185, gender='男' where id = 2;
  db.query("update stu set name=?, age=?, height=?, gender=? where id=?", ['比尔盖茨', 30, 165.50, '男', 2], function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  let obj = {id:3, name: "尤雨溪", age: 30, height: 175.6, gender: "男" };

  // update stu set name='比尔盖茨', age=20, height=185, gender='男' where id = 2;
  db.query("update stu set ? where id = ?", [obj, obj.id], function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



### 删除数据

```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  db.query("delete from stu where id = 5", function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



#### 快捷操作

```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  // delete from stu where id = 5;
  db.query("delete from stu where id = ?", 5, function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



#### 标记删除

- 使用 `Delete` 语句会把真正的数据从表中删除掉，为了保险起见，推荐使用标记删除的形式来模拟删除操作。
- 所谓标记删除就是在表中设置类似于 `status` 这样的状态字段来标记当前这条数据是否被删除。
- 当用户执行了删除的动作时，我们并没有执行 `Delete` 语句把数据删除掉，而是执行了 `Update` 语句将这条数据对应的 `status` 字段标记为已删除即可  



字段值可以用：`1` 代表已删除    `0` 代表未删除

```javascript
pool.getConnection(function (err, db) {
  if (err) return;

  // 将id为6的那条数据status字段设置为1，代表已删除
  db.query("update stu set status = 1 where id = ?", 6, function (err, data) {
    if (err) throw error;

    console.log(data);

    db.release();
  });
});
```



### 封装数据库操作SQL

```js
// 导入MySQL
const mysql = require('mysql');

// 创建数据库连接对象
const pool = mysql.createPool({
  connectionLimit: 150,
  host: 'localhost', //主机地址
  user: 'root', //用户名
  password: '123123', //密码
  database: 'data' //数据库名称
});


function query(sql) {
  return new Promise(function (resolve, reject) {
    // 连接数据库
    pool.getConnection(function (err, db) {
      if (err) return;

      // 执行SQL语句
      db.query(sql, function (err, data) {
        if (err) throw error;

        resolve(data);

        // 释放数据库
        db.release()
      });
    })
  })
}

// 导出
module.exports = query
```

