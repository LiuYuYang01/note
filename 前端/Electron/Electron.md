# Electron

## 快速入门

创建 `Nextjs` 项目

```
npx create-next-app@latest
```



**1、在主进程中创建一个界面：`src/main/main.ts`**

```javascript
const { app, BrowserWindow } = require('electron')

// 创建一个窗口
const createWindow = () => {
  const win = new BrowserWindow({
    width: 800,
    height: 600
  })

  // 指定界面加载的URL
  win.loadURL('http://localhost:3000/')
}

// 在一切就绪时触发
app.on("ready", () => {
  // 一切就绪后才能创建窗口
  createWindow()
})

// 当全部窗口关闭时触发
app.on('window-all-closed', () => {
  app.quit()
})
```

**注意：** 在 `Electron` 中，只有在 `app` 模块的 `ready` 事件被激活后才能创建浏览器窗口



**2、配置主进程文件路径以及启动应用命令：`package.json`**

```json
{
  "name": "electron_app",
  ...
  "main": "src/main/main.js", // 主进程路径
  "scripts": {
    "dev": "concurrently \"next dev\" \"wait-on http://localhost:3000 && electron .\"",
	...
  },
  "dependencies": {
    ...
  },
  "devDependencies": {
	...
  }
}
```

正常情况下我们需要先运行命令： `next dev` 启动 `web` 服务，然后运行命令： `electron .` 启动应用，需要执行 `2` 个命令。但这里我们借助了 `concurrently` 实现多命令运行

然后我们可以更严谨一些，借助 `wait-on` 实现页面正常访问了才能执行 `electron .` 启动应用

```
npm i concurrently wait-on
```

**官方文档：** [https://www.electronjs.org/zh/docs/latest/tutorial/quick-start](https://www.electronjs.org/zh/docs/latest/tutorial/quick-start)



## BrowserWindow

创建并控制浏览器窗口

```javascript
// 在主进程中.
const { BrowserWindow } = require('electron')

// 创建一个窗口
const win = new BrowserWindow({ width: 800, height: 600 })

// 加载远程URL
win.loadURL('https://github.com')

// 加载本地文件
win.loadFile('index.html')
```

**官方文档：** https://www.electronjs.org/zh/docs/latest/tutorial/window-customization



## 进程

### 主进程

每个 `Electron` 应用都有一个单一的主进程作为应用程序的入口点。主进程在 `Nodejs` 环境中运行，它具有 `Require` 模块和使用所有`Nodejs API` 的能力，主进程核心就是：使用 `BrowserWindow` 来创建窗口



### 渲染进程

每个 `BrowserWindow` 实例都对应一个单独的渲染器进程，运行在渲染器进程中的代码必须遵循网页标准，简单来说就是渲染进程无权直接访问 `Require` 或使用 `Nodejs` 的 `API`



### 预加载脚本

预加载是运行在渲染进程中的，他可以访问部分 `Nodejs` 的 `API`，同时又可以与网页内容进行安全的交互

主进程运行在 `Nodejs` 环境，而渲染进程运行在浏览器

因此渲染进程只能使用浏览器相关的API，不能使用 `Nodejs` 的。但我们可以通过预加载给他们两者之间做一个桥梁，使他们互相调用。

**main.js**

```javascript
const win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      // 这里不能使用相对路径
      // preload: './server/preload.js'
        
      // 可以这么做
      preload: path.join(__dirname, './server/preload.js')
    }
})
```



**preload.js**

```
console.log("Hello World!");
```

当界面被打开时就会自动触发 `preload.js` 文件中的代码



**需求：** 点击按钮后在页面呈现当前的 `nodejs` 版本

暴露数据给渲染进程

```javascript
const { contextBridge } = require("electron")

// myAPI最终会挂载到渲染进程的window中，可以直接使用
contextBridge.exposeInMainWorld("myAPI", {
  value: "Hello World!",
  version: process.version
})
```

```react
"use client"

export default function Home() {
  return (
    <>
      <h1>{myAPI.value}</h1>
      {/* <button onClick={()=>alert(window.myAPI.version)}>按钮</button> */}
      <button onClick={() => alert(myAPI.version)}>按钮</button>
    </>
  );
}
```



## IPC 进程通信

需要注意的是预加载脚本只能使用部分 `Nodejs` 的 `API`，但主进程是可以的，所以就需要用到进程通信了

简单来说就是让预加载脚本通知 `main.js` 去执行 `Nodejs` 相关的操作



关于 `Electron` 进程通信，我们要知道：

- `IPC` 全称为： `InterProcess Communication` ，即：进程通信
- `IPC` 是 `Electron` 中最核心的内容，它是从 `UI` 调用原生 `API` 的唯一方法
- `Electron` 注意使用 `ipcMain` 和 `ipcRenderer` 来定义通道，进行进程通信



### 单向通信

在渲染进程中通过 `ipcRenderer.send` 来发送消息，在主进程中使用 `ipcMain.on` 接收消息

**常用于：** 在渲染进程中调用主进程的 `API`，如下需求

**需求：** 点击按钮创建一个 `Hello.txt` 文件，文件的内容来自于用户

1、预加载脚本中使用 `ipcRenderer.send('信道',参数)` 发送消息，与主进程通信。

```javascript
const { contextBridge, ipcRenderer } = require("electron")

contextBridge.exposeInMainWorld("myAPI", {
  create: (content) => ipcRenderer.send("create", content)
})
```

2、主进程中，在加载页面之前，使⽤ `ipcMain.on('信道',回调)` 配置对应回调函数，接收

```javascript
// 创建文件并写入指定数据
const createFile = (event, data) => {
  fs.writeFileSync("Hello.txt", data, "utf-8")
}

app.on("ready", () => {
  createWindow()

  // 主进程注册对应回调
  ipcMain.on("create", createFile)
})
```

3、在渲染进程中使用

```react
"use client"

export default function Home() {
  return (
    <>
      <button onClick={() => myAPI.create("Hello World!")}>按钮</button>
    </>
  );
}
```



### 双向通信

渲染进程通过 `ipcRenderer.invoke` 发送消息，主进程使用 `ipcMain.handle` 接收并处理消息

**注意：** `ipcRender.invoke` 的返回值是 `Promise` 实例

**常用于：** 从渲染进程调用主进程方法并等待结果，如下需求 

**需求：** 点击读取 `Hello.txt` 中的内容，并将结果呈现在页面上。

```javascript
// 读取指定的文件数据
const readFile = (event, path) => {
  const data = fs.readFileSync(path, "utf-8")
  return data
}

app.on("ready", () => {
  createWindow()
  
  // 注册对应回调
  ipcMain.handle("read", readFile)
})
```

```javascript
const { contextBridge, ipcRenderer } = require("electron")

contextBridge.exposeInMainWorld("myAPI", {
  // 返回的结果是Promise实例
  read: (path) => ipcRenderer.invoke("read", path)
})
```

```react
import { useState } from "react";

export default function Home() {
  const [value, setValue] = useState("")

  const btn = () => setValue(myAPI.read("Hello.txt"))

  return (
    <>
      <h1>{value}</h1>
      <button onClick={btn}>按钮</button>
    </>
  );
}
```



### 主 -> 渲染进程

主进程使用 `win.webContents.send` 发送消息，渲染进程通过 `ipcRenderer.on` 处理消息

**常用于：** 从主进程主动发送消息给渲染进程，如下需求

**需求：** 应用加载 `6` 秒钟后，主动给渲染进程发送⼀个消息，内容是：你好啊！

```javascript
const createWindow = () => {
  const win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, './server/preload.js')
    }
  })

  win.loadURL('http://localhost:3000/')

  // 3秒钟之后向渲染进程发送消息
  setTimeout(() => {
    win.webContents.send("msg", "你好啊!")
  }, 2000)
}
```

```javascript
const { contextBridge, ipcRenderer } = require("electron")

contextBridge.exposeInMainWorld("myAPI", {
  getMessage: (callback) => ipcRenderer.on("msg", callback)
})
```

```react
"use client"

import { useEffect } from "react";

export default function Home() {
  const logMessage = (event, data) => alert(data)

  useEffect(() => {
    myAPI.getMessage(logMessage)
  }, [])

  return (
    <>
      <h1>Hello World!</h1>
    </>
  );
}
```



## 沙盒化

`Electron` 沙盒化是一种安全机制，限制了渲染进程的权限，约束只能在浏览器环境中运行，防止直接访问 `Node.js` 的系统级 `API`，如文件系统或进程管理

简单来说就是约束渲染进程，不让它直接访问 `Nodejs` 的 `API`，如果想要访问，可以通过进程通信实现。

当然我们也可以给指定渲染进程关闭沙盒化，让他不受约束，但不建议这么做，对安全有很大隐患

**官方文档：** https://www.electronjs.org/zh/docs/latest/tutorial/sandbox



## 适配

### 完善窗口行为

1、Windows 和 Linux 平台窗⼝特点是：关闭所有窗⼝时退出应⽤

```javascript
// 当所有窗⼝都关闭时
app.on('window-all-closed', () => {
    // 如果所处平台不是mac(darwin)，则退出应⽤。
    if (process.platform !== 'darwin') app.quit()
})
```



2、mac 应⽤即使在没有打开任何窗⼝的情况下也继续运⾏，并且在没有窗⼝可⽤的情况下激活

```javascript
// 当app准备好后，执⾏createWindow创建窗⼝
app.on('ready',()=>{
	createWindow()
	
	// 当应⽤被激活时
	app.on('activate', () => {
	//如果当前应⽤没有窗⼝，则创建⼀个新的窗⼝
	})
})
```



## 打包

使用 `electron-builder` 打包应用

1、安装

```
npm install electron-builder -D
```

2、在 `package.json` 中进行相关配置，具体如下：

```json
{
  "name": "video-tools", // 应⽤程序的名称
  "version": "1.0.0", // 应⽤程序的版本
  "main": "main.js", // 应⽤程序的⼊⼝⽂件
  "scripts": {
    "start": "electron .", // 使⽤ `electron .` 命令启动应⽤程序
    "build": "electron-builder" // 使⽤ `electron-builder` 打包应⽤程序，⽣成安装包
  },
  "build": {
    "appId": "com.atguigu.video", // 应⽤程序的唯⼀标识符
      // 打包windows平台安装包的具体配置
      "win": {
      "icon": "./logo.ico", //应⽤图标
        "target": [
          {
            "target": "nsis", // 指定使⽤ NSIS 作为安装程序格式
            "arch": ["x64"] // ⽣成 64 位安装包
          }
        ]
    },
    "nsis": {
      "oneClick": false, // 设置为 `false` 使安装程序显示安装向导界⾯，⽽不是⼀键安装
      "perMachine": true, // 允许每台机器安装⼀次，⽽不是每个⽤户都安装
      "allowToChangeInstallationDirectory": true // 允许⽤户在安装过程中选择安装⽬录
    }
  },
  "devDependencies": {
    "electron": "^30.0.0", // 开发依赖中的 Electron 版本
    "electron-builder": "^24.13.3" // 开发依赖中的 `electron-builder` 版本
  },
  "author": "tianyu", // 作者信息
  "license": "ISC", // 许可证信息
  "description": "A video processing program based on Electron" // 应⽤程序的描述
}
```

3、执行打包命令

```
npm run build
```

