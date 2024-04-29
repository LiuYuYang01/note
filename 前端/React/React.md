# React

## 快速入门

### 表达式

```react
const msg = 'this is message'

function getAge(){
  return 18
}

function App(){
  return (
    <div>
      <h1>this is title</h1>
      {/* 字符串识别 */}
      {'this is str'}
      {/* 变量识别 */}
      {msg}
      {/* 变量识别 */}
      {msg}
      {/* 函数调用 渲染为函数的返回值 */}
      {getAge()}
    </div>
  )
}
```



### 列表渲染

```react
const list = [
  {id:1001, name:'Vue'},
  {id:1002, name: 'React'},
  {id:1003, name: 'Angular'}
]

function App(){
  return (
    <ul>
      {list.map(item=><li key={item.id}>{item}</li>)}
    </ul>
  )
}
```



### 条件渲染

```react
const flag = true
const loading = true

function App() {
  return (
    <>
      {flag && <span>Hello</span>}
      {loading ? <span>加载中~</span> : <span>加载成功</span>}
    </>
  );
}

export default App;
```



复杂条件渲染

```react
const type = 2

const getArticleJSX = () => {
  switch (type) {
    case 1:
      return <h1>无图模式</h1>
    case 2:
      return <h1>单图模式</h1>
    case 3:
      return <h1>多图模式</h1>
  }
}

function App() {
  return (
    <>
      {getArticleJSX()}
    </>
  );
}

export default App;
```



### 事件

```react
function App() {
    const clickHandler = () => {
    	console.log("button被点击了");
    }
    
  return (
    <>
      {<button onClick={clickHandler}>click me</button>}
    </>
  );
}

export default App;
```



event

```react
const clickHandler1 = (n) => {
  console.log("button被点击了1", n);
}

const clickHandler2 = (e, n) => {
  console.log("button被点击了2", e, n);
}

function App() {
  return (
    <>
      {<button onClick={() => clickHandler1(100)}>click me</button>}
      {<button onClick={(e) => clickHandler2(e, 200)}>click me</button>}
    </>
  );
}

export default App;
```



## 组件

```react
// 定义一个组件
const Button = () => {
  return <button>按钮</button>
}

function App() {
  return (
    <>
      {/* 使用组件 */}
      {<Button />}
      {<Button></Button>}
    </>
  );
}

export default App;
```



## useState

`useState` 的返回值是一个数组

第一个参数是状态变量，第二个参数是 `set` 函数，用来修改状态变量，而 `useState` 的参数作为初始值

```react
import { useState } from "react";

function App() {
  const [num, setNum] = useState(1)

  const btn = () => {
    // num = num + 1 不能赋值, 只能修改
    setNum(num + 1)
  }

  return (
    <>
      <h1>{num}</h1>
      <button onClick={btn}>按钮</button>
    </>
  );
}

export default App;
```

需要注意的 `useState` 的状态变量是只读的，不能直接给赋值，会丢失响应式。需要通过 `set` 函数修改



**修改复杂数据**

```react
import { useState } from "react";

function App() {
  const [obj, setObj] = useState({ name: "yuyang", age: 21 })

  const btn = () => {
    setObj({
      ...obj,
      age: 22
    })
  }

  return (
    <>
      <h1>{obj.name} {obj.age}</h1>
      <button onClick={btn}>按钮</button>
    </>
  );
}

export default App;
```



## 样式

```css
.sty {
    color: red;
}
```

```react
import "./index.css"

function App() {
  const sty = { color: 'red' }

  return (
    <>
      {/* <h1 style={{ color: 'red' }}>Hello World!</h1> */}
      <h1 style={sty}>Hello World!</h1>

      {/* 推荐 */}
      <h1 className="sty">Hello World!</h1>
      <h1 className={`sty ${1 === 1 && 'axtive'}`}>Hello World!</h1>
    </>
  );
}

export default App;
```



## 表单绑定

```react
import { useState } from "react";

function App() {
  const [value, setValue] = useState('')

  const change = (e) => {
    setValue(e.target.value)
  }

  return (
    <>
      <h1>{ value }</h1>
      <input value={value} onChange={change} />
    </>
  );
}

export default App;
```



## 获取DOM

```react
import { useRef, useState } from "react";

function App() {
  const domRef = useRef()

  // 视图还未渲染成功，无法获取
  console.log(domRef.current) // undefined

  // 暂时可以这么做
  setTimeout(() => console.log(domRef.current))

  return (
    <>
      <h1 ref={domRef}>Hello World!</h1>
    </>
  );
}

export default App;
```



## 组件

### 父传子

```react
const Son = (props) => {
  console.log(props); // {msg: 'Hello World!', age: 20, is: true, list: Array(3), obj: {…}, …}

  return <h1>{props.msg}</h1>
}

function App() {
  return (
    <>
      <Son msg={'Hello World!'} age={20} is={true} list={[1, 2, 3]} obj={{ name: "jack" }} cb={() => console.log(123)} child={<span>Hello</span>} />
    </>
  );
}

export default App;
```



当给组件中传递一个html标签，可以在组件中通过children来接收

```react
const Son = (props) => {
  console.log(props); // {children: {…}}

  return props.children
}

function App() {
  return (
    <>
      <Son>
        <h1>Hello World!</h1>
      </Son>
    </>
  );
}

export default App;
```



### 子传父

```react
const Son = ({ onGetMsg }) => {
  return <h1 onClick={() => onGetMsg('Hello World!')}>Hello World!</h1>
}

function App() {
  const getMsg = (msg) => {
    console.log(msg);
  }

  return (
    <>
      <Son onGetMsg={getMsg} />
    </>
  );
}

export default App;
```



### 兄弟组件通信

子组件（A）给父组件传值，父组件再把值传给子组件（B）

```react
import { useState } from "react"

const A = ({ onSetMsg }) => {
  const msg = "Hello World!"

  return <button onClick={() => onSetMsg(msg)}>按钮</button>
}

const B = ({ msg }) => {
  return <h1>{msg}</h1>
}


function App() {
  const [msg, setMsg] = useState("")

  return (
    <>
      <A onSetMsg={(msg) => setMsg(msg)} />
      <B msg={msg} />
    </>
  );
}

export default App;
```



### 跨组件通信

```react
import { createContext, useContext } from "react"

// 1. 创建上下文对象
const MsgContext = createContext()

const A = () => {
  // 3. 获取数据
  const msg = useContext(MsgContext)
  console.log(msg); // Hello World!

  return <h1>{msg}</h1>
}


function App() {
  const msg = "Hello World!"

  return (
    <>
      {/* 2. 传递数据 */}
      <MsgContext.Provider value={msg}>
        <A />
      </MsgContext.Provider>
    </>
  );
}

export default App;
```



## useEffect

等组件渲染成功后触发useEffect里面的代码，通常用于发起网络请求、操作dom等

```react
import axios from "axios"
import { useEffect, useState } from 'react'

function App() {
  const [list, setList] = useState([])

  useEffect(() => {
    const getData = async () => {
      const { data: { data } } = await axios.get("http://geek.itheima.net/v1_0/channels")
      setList(data.channels)
    }

    getData()
  }, [])

  return (
    <>
      <ul>
        {
          list.map(item => (
            <li key={item.id}>{item.name}</li>
          ))
        }
      </ul>
    </>
  );
}

export default App;
```



他的第一个参数是一个回调函数，第二个参数是一个数组

如果不写就表示初始执行一次，并且每当组件的数据发生变化都会触发。如果指定对应的状态变量则表示只有指定的数据发生变化才会触发，如果为空表示只在组件渲染成功后执行一次

```react
import { useEffect, useState } from 'react'

function App() {
  const [num, setNum] = useState(1)

  // 在组件渲染完毕后与组件状态变量发生变化后都会触发
  // useEffect(() => {
  //   console.log("useEffect触发了~");
  // })

  // 在组件渲染完毕后只触发一次
  // useEffect(() => {
  //   console.log("useEffect触发了~");
  // }, [])

  // 在组件渲染完毕后触发一次，当指定的状态变量num发生变化后还会触发
  useEffect(() => {
    console.log("useEffect触发了~");
  }, [num])

  const btn = async () => {
    setNum(num + 1)
  }

  return (
    <>
      <button onClick={btn}>按钮</button>
    </>
  );
}

export default App;
```



清除副作用：当我们在useEffect中书写定时器等代码，那么当组件被销毁后定时器并没有释放资源。所以可以在useEffect里面返回一个函数，当组件被卸载后就会触发返回的这个函数，我们可以在这个函数里面做一些清理逻辑

```react
import { useEffect, useState } from "react";

function Son() {
  // 默认情况下组件被卸载后，定时器等代码并没有被销毁，还会继续执行
  // useEffect(() => {
  //   let n = 1;

  //   setInterval(() => {
  //     console.log(n++);
  //   }, 500)
  // }, [])

  useEffect(() => {
    let n = 1;

    const time = setInterval(() => {
      console.log(n++);
    }, 500)

    // 组件被卸载时自动执行
    return () => {
      clearInterval(time)
    }
  }, [])
}

function App() {
  const [is, setIs] = useState(true)
  const btn = () => setIs(false)

  return (
    <>
      {is && <Son />}
      <button onClick={btn}>销毁组件</button>
    </>
  );
}

export default App;
```



## Hooks

自定义 `Hook` 是以 `use` 开头的函数，通过自定义 `Hook` 函数可以用来实现逻辑的封装和复用

**注意：** 自定义 `Hook` 不能在函数组件外部调用并且不能嵌套在 if、for、其他函数中

```react
import { useState } from "react";

// 自定义Hooks
const useToggle = () => {
  const [value, setValue] = useState()

  const toggle = () => {
    setValue(!value)
  }

  return { value, toggle }
}

// const { value, toggle } = useToggle() 不能在这里调用！！

function App() {
  const { value, toggle } = useToggle()

  return (
    <>
      <button onClick={toggle}>按钮</button>
      {value && <h1>Hello World!</h1>}
    </>
  );
}

export default App;
```



## 配置 @ 快捷路径

**tsconfig.json**

```json
"compilerOptions": {
    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"]
    },
    // ...
  }
```



**vite.config.ts**

```typescript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path';

export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    },
  }
})
```



# Router

```
npm i react-router-dom
```



最小应用

```react
import React from 'react';
import ReactDOM from 'react-dom/client';

// 导入路由
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

function Aricle(){
    return <h1>Aricle</h1>
}

function Cate(){
    return <h1>Cate</h1>
}

// 创建路由表
const router = createBrowserRouter([
    {
        path: "/article",
        element: <Aricle />
    },
    {
        path: "/cate",
        element: <Cate />
    }
])

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    // 使用路由
    <RouterProvider router={router}></RouterProvider>
);
```



## 路由跳转

### 声明式导航

```react
import React from 'react';
import ReactDOM from 'react-dom/client';

import { createBrowserRouter, RouterProvider, Link } from 'react-router-dom'

function Aricle() {
    return (
        <>
            <Link to="/cate">跳转到分类页</Link>
            <h1>Aricle</h1>
        </>
    )
}

function Cate() {
    return (
        <>
            <Link to="/article">跳转到文章页</Link>
            <h1>Cate</h1>
        </>
    )
}

const router = createBrowserRouter([
    {
        path: "/article",
        element: <Aricle />
    },
    {
        path: "/cate",
        element: <Cate />
    }
])
// 
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <RouterProvider router={router}></RouterProvider>
);
```



### 编程式导航

```react
import React from 'react';
import ReactDOM from 'react-dom/client';

import { createBrowserRouter, RouterProvider, Link, useNavigate } from 'react-router-dom'

function Aricle() {
    const navigate = useNavigate()

    return (
        <>
            <button onClick={() => navigate('/cate')}>跳转到分类页</button>
            <h1>Aricle</h1>
        </>
    )
}

function Cate() {
    const navigate = useNavigate()
    
    return (
        <>
            <button onClick={() => navigate('/article')}>跳转到文章页</button>
            <h1>Cate</h1>
        </>
    )
}

const router = createBrowserRouter([
    {
        path: "/article",
        element: <Aricle />
    },
    {
        path: "/cate",
        element: <Cate />
    }
])
// 
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <RouterProvider router={router}></RouterProvider>
);
```



### Navigate

`<Navigate>` 元素在渲染时会改变当前位置。它是 [`useNavigate`](https://baimingxuan.github.io/react-router6-doc/hooks/use-navigate) 的组件包装器，并接受与 props 相同的参数。

```react
import { persistence } from '@/utils'
import { Navigate } from 'react-router-dom'

const AuthRoute = ({ children }: { children: React.ReactNode }) => {
  const token = persistence.getToken()

  if (token) {
    return <>{children}</>
  } else {
    return <Navigate to="/login" replace />
  }
}

export default AuthRoute
```



### 跳转传参

```react
import React from 'react';
import ReactDOM from 'react-dom/client';

import { createBrowserRouter, RouterProvider, Link, useNavigate, useSearchParams, useParams } from 'react-router-dom'

function Aricle() {
    const navigate = useNavigate()

    const [params] = useSearchParams()
    console.log(params); // URLSearchParams {size: 1}

    const params1 = useParams() // {id: '100'} 111

    return (
        <>
            <button onClick={() => navigate('/cate?name=大前端')}>跳转到分类页</button>
            <h1>Aricle query参数：{params1.id} {params.get('title')}</h1>
        </>
    )
}

function Cate() {
    const [params] = useSearchParams()

    return (
        <>
            <Link to="/article/100?title=大前端新趋势">跳转到文章页</Link>
            <h1>Cate params参数：{params.get('name')}</h1>
        </>
    )
}

const router = createBrowserRouter([
    {
        path: "/article/:id",
        element: <Aricle />
    },
    {
        path: "/cate",
        element: <Cate />
    }
])
// 
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <RouterProvider router={router}></RouterProvider>
);
```



## 嵌套路由

### 二级路由

```react
import React from 'react';
import ReactDOM from 'react-dom/client';

// 导入路由
import { createBrowserRouter, RouterProvider, Link, Outlet } from 'react-router-dom'

// 头部组件
function Header() {
    return (
        <>
            <h1>Header</h1>
        </>
    )
}

// 页脚组件
function Footer() {
    return (
        <>
            <h1>Footer</h1>
        </>
    )
}

// 布局
function Layout() {
    return (
        <>
            <Header />

            <Link to="/">首页</Link>
            <Link to="/article">文章页</Link>
            <Link to="/cate">分类页</Link>

            {/* 类似于router-view */}
            <Outlet />

            <Footer />
        </>
    )
}

// 首页
function Index() {
    return (
        <>
            <h1>Index</h1>
        </>
    )
}

// 文章页
function Article() {
    return (
        <>
            <h1>Article</h1>
        </>
    )
}

// 分类页
function Cate() {
    return (
        <>
            <h1>Cate</h1>
        </>
    )
}

// 登录页
function Login() {
    return (
        <>
            <h1>Login</h1>
        </>
    )
}

// 路由表
const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        // 二级路由
        children: [
            {
                // 设置为主页，当路由访问父路径相当于访问了这个
                index: true,
                element: <Index />
            },
            {
                path: "article",
                element: <Article />
            },
            {
                path: "cate",
                element: <Cate />
            }
        ]
    },
    {
        path: "/login",
        element: <Login />
    }
])

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    // 注入路由
    <RouterProvider router={router}></RouterProvider>
);
```



### 404配置

```react
import React from 'react';
import ReactDOM from 'react-dom/client';

// 导入路由
import { createBrowserRouter, RouterProvider, Link } from 'react-router-dom'

// 首页
function Index() {
    return (
        <>
            <Link to="/">首页</Link>
            <Link to="/article">文章页</Link>
            <Link to="/cate">分类页</Link>

            <h1>Index</h1>
        </>
    )
}

// 文章页
function Article() {
    return (
        <>
            <h1>Article</h1>
        </>
    )
}

// 404页
function NotFound() {
    return (
        <>
            <h1>404：页面未找到</h1>
        </>
    )
}

// 路由表
const router = createBrowserRouter([
    {
        path: "/",
        element: <Index />
    },
    {
        path: "/article",
        element: <Article />
    },
    {
        path: "*",
        element: <NotFound />
    }
])

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    // 注入路由
    <RouterProvider router={router}></RouterProvider>
);
```



## 路由模式

各个主流框架的路由常用的路由模式有俩种，history模式和hash模式, ReactRouter分别由 createBrowerRouter 和 createHashRouter 函数负责创建

| 路由模式 | url表现     | 底层原理                    | 是否需要后端支持 |
| -------- | ----------- | --------------------------- | ---------------- |
| history  | url/login   | history对象 + pushState事件 | 需要             |
| hash     | url/#/login | 监听hashChange事件          | 不需要           |



## 路由懒加载

```

```



# Redux

**安装**

```
npm i @reduxjs/toolkit  react-redux
```



## 快速上手

1、定义一个最简单的 `Store` 模块


```react
// src/store/modules/CountStore.js

import { createSlice } from '@reduxjs/toolkit'

const countStore = createSlice({
    // 自定义一个独一无二模块名称
    name: "count",
    // 在这里定义数据
    initialState: {
        n: 1
    },
    // 在这里定义方法，用于修改状态
    reducers: {
        add(state) {
            state.n++
        },
        minus(state) {
            state.n--
        }
    },
})

// 解构出reducers中的方法
const { add, minus } = countStore.actions

// 获取reducer函数
const countReducer = countStore.reducer

// 导出
export { add, minus }
export default countReducer
```



2、注册 `Store` 模块

```react
// src/store/index.js

import { configureStore } from '@reduxjs/toolkit'

// 导入此状态模块
import countReducer from './modules/CountStore'

export default configureStore({
    reducer: {
        // 注册模块
        count: countReducer
    }
})
```



3、最后一步

```react
// src/index.js

import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App'

// 导入store
import store from './store'
// 导入store提供的组件Provider
import { Provider } from 'react-redux'

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    // 提供store数据
    <Provider store={store}>
        <App />
    </Provider>
);
```



在页面中使用

```react
// src/App.js

import { useDispatch, useSelector } from "react-redux";
// 导入Store中的方法
import { add, minus } from './store/modules/CountStore'

function App() {
  // 导入Store中的数据
  const { n } = useSelector(state => state.count)
  // 方法必须当做参数传给dispatch进行使用
  const dispatch = useDispatch()

  return (
    <>
      <button onClick={() => dispatch(add())}>+</button>
      <span>{n}</span>
      <button onClick={() => dispatch(minus())}>-</button>
    </>
  );
}

export default App;
```



## 传递参数

可以通过 `reducers` 中的方法的第二个参数来接收传递的数据，如果需要传递多个数据，那么可以传递一个对象

```react
const countStore = createSlice({
    name: "count",
    // 在这里定义数据
    initialState: {
        n: 1
    },
    reducers: {
        add(state, action) {
            // 有值就根据指定的值相加，没有值就加1
            action.payload ? state.n += action.payload : state.n++

        },
        minus(state, action) {
            action.payload ? state.n -= action.payload : state.n--
        }
    },
})
```

```react
function App() {
  // ...

  return (
    <>
      <button onClick={() => dispatch(add(10))}>+</button>
      <span>{n}</span>
      <button onClick={() => dispatch(minus())}>-</button>
    </>
  );
}
```



## 异步操作

```react
import { useDispatch, useSelector } from "react-redux";
import { getDataList } from './store/modules/CountStore'
import { useEffect } from "react";

function App() {
  const { list } = useSelector(state => state.count)

  const dispatch = useDispatch()

  useEffect(() => {
    dispatch(getDataList())
  }, [dispatch])

  return (
    <>
      <ul>
        {list.map(item => <li key={item.id}>{item.name}</li>)}
      </ul>
    </>
  );
}

export default App;
```



```react
import { createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const countStore = createSlice({
    // 自定义一个独一无二模块名称
    name: "count",
    // 在这里定义数据
    initialState: {
        list: []
    },
    // 在这里定义方法，用于修改状态
    reducers: {
        setList(state, actions) {
            // 只能只能同步赋值，不能异步赋值
            state.list = actions.payload
        }
    },
})

// 解构出reducers中的方法
const { setList } = countStore.actions

// 获取reducer函数
const countReducer = countStore.reducer

// 获取接口中的数据
const getDataList = () => {
    // 必须返回一个函数，因为不能在外层使用async，否则外层函数就变成了promise，不符合dispatch参数类型
    return async (dispatch) => {
        const { data: { data } } = await axios.get("http://geek.itheima.net/v1_0/channels")
        dispatch(setList(data.channels))
    }
}

export { getDataList }
export default countReducer
```



# 极客园项目

## 路由守卫

1. 定义一个组件，将需要登录后才能访问的页面插入到组件中，比如 `Layout`

```react
import { createBrowserRouter } from 'react-router-dom'
import Layout from '@/pages/Layout'
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import AuthRoute from '@/components/AuthRoute'

const router = createBrowserRouter([
  {
    path: '/',
    element: <AuthRoute><Layout /></AuthRoute>,
    children: [
      {
        index: true,
        element: <Home />
      }
    ]
  },
  {
    path: '/login',
    element: <Login />
  }
])

export default router
```



2. 通过 `children` 拿到插入的组件 `Layout`

3. 如果有 `token` 就渲染这个组件，没有 `token` 就跳转到登录页

```react
const AuthRoute = ({ children }: { children: React.ReactNode }) => {
  const token = persistence.getToken()

  if (token) {
    return <>{children}</>
  } else {
    return <Navigate to="/login" replace />
  }
}

export default AuthRoute
```

