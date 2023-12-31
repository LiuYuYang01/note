# Vue

## Vue 的最大的优势是什么

`Vue` 是一款轻量级前端框架，简单易学、数据双向绑定、支持组件化开发、数据与视图完全分离、虚拟DOM运行速度更快 并且作者尤雨溪是中国人，Vue相应的文档也是中文的，对国内比较友好。所以Vue是前端开发人员首选的入门框架



**Vue的优势：**

1. 支持组件化开发，提高代码复用性以及扩展性
2. 数据双向绑定，数据发生变化既视图发生变化
2. 数据与视图完全分离，提高代码可读性



## SPA 单页面应用的优缺点

**优点**

1. 用户体验好，内容的改变不需要加载整个页面，有效减轻服务器压力
2. 前后端分离开发模式 前端进行界面交互逻辑，后端负责数据处理 有效提高代码可读性
3. 组件化开发提高项目复用性以及扩展性



**缺点**

1. 对 SEO 不太友好，前后端分离模式在前端没有渲染数据相当于没有 SEO
2. 不能使用浏览器自带的前进后退功能，需要自己通过 `router` 定义
3. 首次访问页面需要加载大量的静态资源，加载时间相对较长



## jQuery 与 Vue 的区别

`jQuery` 是直接操作的真实 `DOM` 需要先获取元素然后进行赋值，数据与视图是在一起的

而 `Vue` 操作的是虚拟 `DOM` 并且将数据与视图完全分离了



## v-for 遍历中 key 属性作用

在 Vue 虚拟 DOM Diff 算法中 Vue 会借助元素的 Key 值来判断该元素是否可复用，我们需要保证更新前面相同元素的 key 是一致的，所以推荐使用 id 作为 key 属性的值如果用index下标作为 key 属性的值，不过会导致更新前后相同元素的 key 不相同，从而出现一系列未知的BUG



## Vue 的常用指令

1. `v-bind`：动态绑定数据
2. `v-on`：绑定事件监听器
3. `v-for`：循环指令，可以循环数组或对象
4. `v-model`：实现双向绑定
5. `v-if`：根据表达式的真假值，判断是否渲染元素，会销毁并重建
6. `v-show`：显示隐藏元素，修改元素的 `display` 属性



## Vue 常用的修饰符

**.stop：** 阻止事件冒泡
**.prevent： ** 阻止默认行为
**.capture：** 与事件冒泡的方向相反，事件捕获由外到内
**.self：** 只会有被绑定事件的那个元素才能被触发
**.once：** 使该事件只触发一次

## ref 和 reactive 的区别

1. `ref` 既可以定义基本类型也可以定义引用类型的数据，而 `reactive` 只能用来定义引用类型数据

2. `ref` 定义的数据需要通过 `.value` 来读取或更新 `reactive` 可以直接操作数据
3. `ref ` 定义的数据如果是引用数据类型，它的底层其实是通过 `reactive` 来定义的



## watch 和 watchEffect 的区别

1. `watch` 可以访问新值和旧值(如果监视对象的话不行，因为新旧值相等)，`watchEffect` 只能监听最新的值。

2. `watch` 需要监听指定的属性，而 `watchEffect` 会监听所有属性值的变化，所以属性过多情况下不建议使用，会影响性能

3. `watch` 只有监听的属性值发生变化了才会触发，而 `watchEffect` 会默认触发一次。`watch` 需要在第三个参数中指定 `immediate:true` 才会默认触发一次



## Vue 数据响应式原理是什么

**Vue2** 响应式原理是遍历 `data` 对象中的每一个属性，如果是基本数据类型就直接通过 `Object.defineProperty` 把 `data` 对象上的每个属性转换具有 `getter` 和 `setter` 的拦截器属性。如果遍历的是复杂数据类型，则需要递归变量，`Object.defineProperty` 把 `data` 对象上的每个属性转换具有 `getter` 和 `setter` 的拦截器属性



**总结**

`Vue2` 是通过 `Object.defineProperty` 把每一个属性都转换为响应式，如果有 100 个属性就要遍历 100 次，而 `Vue3` 则是把整个对象给 `proxy` 转换为响应式，如果有 100 个属性也只遍历一次。

前者需要遍历全部，而后者只需要遍历一次，所以后者 `Vue3` 性能更佳



## computed 和 watch 属性的区别以及应用场景

**区别**

1、计算属性具有缓存功能，只有当依赖的值发生变化时才会触发，而 侦听属性没有缓存，只要值被修改就会触发

2、计算属性不支持异步，而 侦听属性支持异步

3、计算属性必须要有return 返回值，而 侦听属性不能写return 会导致获取不到数据

 

**应用场景**  

**watch：** 一个数据影响多个数据，需要在数据变化时执行异步操作或者开销较大的操作时使用。

例如搜索数据

**computed：** 一个数据受到多个数据影响，处理复杂逻辑或多个属性影响一个属性变化时候使用。

例如购物车商品结算功能



## Vue 组件的 data 为什么必须是函数

组件中的 `data` 函数返回值形式定义，这样每复用组件时就会返回一个新的 `data`，相当于给每个组件实例创建一个互不影响的私有数据空间。而写成对象形式就会导致所有组件实例共用了一个 `data`，就会造成一个数据发生变化，所有 `data` 数据都会变化这种情况



## Vue组件之间的通信有几种方式

1. props：父组件可以通过 `props` 属性传递数据给子组件。子组件可以通过 `props` 选项接收并使用这些数据。
2. $emit：子组件可以通过 `$emit` 方法触发自定义事件，并向上传递消息给父组件。父组件可以通过监听子组件触发的事件，并处理相应的逻辑。
3. 使用事件总线：可以通过创建一个公共的Vue实例作为事件总线来实现组件之间的通信。在某个组件中通过事件总线实例触发事件，其他组件通过事件总线实例监听该事件并执行相应操作，从而实现组件之间的通信。
4. Vuex：可以在不同的组件之间共享数据，并通过定义的 `mutations` 来修改共享状态。



在 `vue3` 中还可以使用



## 兄弟组件通信

- 事件总线：EventBus  [Vue.prototype.$bus = new Vue()]
- 全局数据共享：Vuex



**事件总线的原理：** 在 `Vue` 原型上设置一个属性，给这个属性添加一个新的 `Vue` 实例，然后使用 `属性.emit` 传递数据，通过 `属性 .on` 接收数据



## 指令 v-el 的作用是什么

提供一个在页面上已存在的 `DOM` 元素作为 `Vue` 实例的挂载目标，可以是 `css` 选择器，也可以是一个 `HTMLElement` 实例



## Vue 第一次页面加载会触发哪些钩子？

`beforeCreate`、`created`、`beforeMount`、`mounted`



## Vue 获取数据在哪个生命周期函数?

一般在 `created`、`beforeMount`、`mounted` 中， 如果要操作 `DOM` ，必须在  `beforeMount` 之后才能操作



## Vue 如何去除 URL 中的 # #

将路由的 `hash` 模式改为 `history` 模式



## Vue $route 和 $router 的区别

`$router` 为 `VueRouter` 实例，`$route` 为当前路由

一般 `$router` 用于跳转路由  **而**  `$route` 用于接收路由的参数



## 数据双向绑定原理

`v-model` 主要用于双向绑定数据，他给元素绑定时，不同元素做法也不同

1、如果是普通文本类型元素 input / textarea 绑定的是 value / input

2、如果是单选或多选元素 checkbox / redio 绑定的是 checked / change

3、如果是下拉元素 select 绑定的是 change

4、其他表单元素会按照 value / input 处理

 

## Vue 中 key 值的作用是什么

需要使用 `key` 来给每个节点做一个唯一标识，`Diff算法`就可以正确的识别此节点，找到正确的位置区插入新的节点



## v-show 和 v-if 指令的共同点与不同点

**相同点**

`v-show` 与 `v-if` 都是控制元素的显示与隐藏的

**不同点**

实现本质不同：`v-show` 本质是通过设置 `css` 的 `display：none` 来控制显示隐藏，其实就是在控制 `css`。

而 `v-if` 是动态向 `DOM` 树中添加和删除来控制显示与隐藏的

**总结**

`v-show` 只编译一次，后面其实就是在控制 `css`，而 `v-if` 不停的创建、销毁对性能不好。所以当频繁显示隐藏切换时使用 `v-show`，否则就使用 `v-if`



## Vue的生命周期

**beforeCreate：** 在实例创建之间执行，数据是未加载状态。
**created：** 在实例创建、数据加载后，能初始化数据，DOM渲染之前执行。
**beforeMount： **虚拟DOM已创建完成，在数据渲染前最后一次更改数据。el未挂载。
**mounted：** 页面、数据渲染完成。el挂载完毕。可以访问DOM节点。
**beforeUpdate：** 重新渲染之前触发。不会造成重渲染。
**Updated： **数据已经更新完成，DOM也重新render完成，更改数据会陷入死循环。
**beforeDestroy： **实例销毁前执行，实例仍然完全可用。
**destroyed： **实例销毁后执行，这时候只剩下DOM空壳。



## Vuex 的出现解决了什么问题

主要解决了以下两个问题 

1、当多个组件依赖于同一状态时，对于层层嵌套的组件之间传值会非常麻烦，并且没办法对兄弟组件之间的传值

2、使用路由传递参数过多时就会导致 `400` 等问题 



## nextTick的作用以及应用场景

等 `DOM` 元素渲染完毕之后，在下一次更新循环结束时自动执行回调函数中的代码

**为什么要在下一次？** 因为本次的 `DOM` 数据还没有更新，在下一次更新结束后 是最新的数据



**应用场景**

1. 如果想要在修改数据后立刻得到更新后的 `DOM` 结构，可以使用 `Vue.nextTick()`

2. 在 `created` 生命周期中操作 `DOM` 



## keep-alive的作用以及应用场景

可以实现组件缓存，当组件切换时只会触发一次组件的生命周期
它具有 `include`、`exclude` 这两个属性，可以有条件的进行组件缓存
两个钩子函数 `activated` / `deactivated` 用来得知当前组件是否处于激活状态



**应用场景**

`keep-alive` 一般用于在组件页面跳转后保留当前位置

比如从首页 到 分类页第一次会加载分类页的生命周期，而在第二次跳转到分类页时候，因为有了组件缓存，所以分类页的生命周期就不会再次触发了，而且能够保留当前分类页的位置



## 一般在哪个生命周期请求异步数据

我们可以在钩子函数 `created`、`beforeMount`、`mounted` 中进行请求异步数据，因为在这三个钩子函数中，`data` 已经创建，可以将服务端端返回的数据进行赋值。

一般推荐在 `created` 钩子函数中请求异步数据，因为在 `created` 钩子函数中调用异步请求有以下优点：

能更快获取到服务端数据，减少页面加载时间，用户体验更好；

并且 `SSR` 不支持 `beforeMount` 、`mounted` 钩子函数，放在 `created` 中有助于一致性。



## 怎么使 watch 立即被调用

 在参数中指定 `immediate: true` 将会在页面加载完毕后立即执行一次 `watch` 中的回调



## 怎么使 watch 深度监听

在参数中指定 `deep: true` 将会开启 `watch` 深度监听



## computed 中的属性名和 data 中的属性名可以相同吗 

不能同名，因为 `computed、data、props` 都会被挂载到 `vm vue` 的实例对象中，所以他们不能同名



## 谈谈Pinia是什么

一个集中式状态管理方案，通常用于管理多个组件共享的状态数据

相比 Vuex 来说 Pinia 的优点：

没有 mutations 流程更简洁

TypeScript支持更加友好

Pinia 模块定义即可使用，不用汇总



## Vue2/3采用的数据响应式原理分别是？

vue2 采用的是 `Proxy `

vue3 采用的是 `Object.defineProperty`



## params 和 query的区别

## 简述 Vue 的 MVVM模式

## 路由守卫

## 监听组件原生事件

