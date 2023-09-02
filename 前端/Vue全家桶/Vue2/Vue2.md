# Vue

## class

**对象形式**

```vue
<template>
    <div>
        <!-- 如果active为true就添加这个类名，反之则不添加 -->
        <div :class="{ active: true }">Hello World</div>

        <!-- 键值对一样可以简写 -->
        <!-- <div :class="{ active: active }">Hello World</div> -->
        <div :class="{ active }">Hello World</div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            active: true
        }
    }
}
</script>

<style>
.active {
    color: red;
}
</style>
```



**数组形式**

```vue
<template>
  <div>
    <!-- 也可以使用数组形式 -->
    <div :class="['active', select]">Hello World</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      select: 'select'
    }
  }
}
</script>

<style>
.active {
  color: red;
}

.select {
  background-color: #000;
}
</style>
```



**三元表达式**

```vue
<template>
  <div>
    <!-- 三元表达式 -->
    <div :class="n > 5 ? 'active' : ''">Hello World</div> 单个
    <div :class="[n > 5 ? 'active' : '']">Hello World</div> 多个
    <div :class="{ active: n > 5 }">Hello World</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      n: 6
    }
  }
}
</script>

<style>
.active {
  color: red;
}
</style>
```



## style

```vue
<template>
    <div>
        <div :style="{ backgroundColor: 'red' }">Hello World</div>
        <div :style="{ backgroundColor: active }">Hello World</div>

        <!-- 键值对一样可以简写 -->
        <!-- <div :style="{ backgroundColor: backgroundColor }">Hello World</div> -->
        <div :style="{ backgroundColor }">Hello World</div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            active: 'red',
            backgroundColor: 'red'
        }
    }
}
</script>
```



## filters

**注意：**Vue3中废除了 `filters`

**基本使用**

```vue
<template>
  <div>
    <div>{{ msg | revolve }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: "Hello World!"
    }
  },
  // 定义过滤器
  filters: {
    revolve(val) {
      // 反转字符串
      return val.split('').reverse().join('')
    }
  }
}
</script>
```



**反转字符串后再转大写**

```vue
<template>
  <div>
    <!-- 反转字符串后再转大写 -->
    <div>{{ msg | revolve | toUp}}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: "Hello World!"
    }
  },
  filters: {
    // 字符串反转
    revolve(val) {
      return val.split('').reverse().join('')
    },
    // 字符串转大写
    toUp(val){
      return val.toUpperCase()
    }
  }
}
</script>
```



## computed

一个数据依赖另外一些数据计算而来的结果就需要用到 `computed` 计算属性

**语法**

```js
  // 在这里定义计算属性
  computed: {
    // 定义一个计算属性
    sum() {
      // 当x或y其中一个发生变化就会自动触发这个sum()函数
      return this.x + this.y
    }
  }
```

**注意：**计算属性定义的 `sum` 属性在 `data` 中不能再次使用了



**缓存**

计算属性是基于它们依赖项的值结果进行缓存的，只要依赖的结果不变, 就直接从缓存中取值。如果值变了，就重新赋值到缓存中



**扩展**

```js
computed: {
    sum: {
      set(val) {
        // 通过set写入值，可以不加return
      },
      get() {
        // 通过get获取值，必须加return
        return 1
      }
    }
}
```



## watch

监听指定的属性，当属性发生变化后会自动执行侦听器，侦听器的两个参数为新值与旧值。

```js
export default {
  data() {
    return {
      str: ""
    }
  },
  // 在这里定义侦听器
  watch: {
    // 侦听str的变化
    str(newData, oldData) {
      console.log('新值：', newData);
      console.log('旧值：', oldData);
    }
  }
}
```



**深度侦听**

```js
export default {
  data() {
    return {
      str: ""
    }
  },
  watch: {
    str: {
      handler(newData, oldData) {
        console.log('新值：', newData);
        console.log('旧值：', oldData);
      },
      immediate: true, //默认执行一次
      deep: true //深度侦听
    }
  }
}
```



**watch与computed的区别？**

`watch` 没有缓存功能，它指定的属性只要发生变化就会直接赋值到缓存 而 `computed` 计算属性具备缓存功能，只要值没有发生变化就从缓存中取值，如果值发生变化才会重新赋值到缓存，这样对性能很友好



## components

### 局部组件

```vue
<template>
  <div>
    <!-- <HelloWorld></HelloWorld> -->
    <HelloWorld />
    <Hello></Hello>
  </div>
</template>

<script>
// 引入组件
// import Hello from '@/components/HelloWorld'
import HelloWorld from '@/components/HelloWorld'
export default {
  // 在这里定义组件
  components: {
    // HelloWorld 名字相同,可以简写
    // HelloWorld: Hello
    
    HelloWorld: HelloWorld,

    // 组件名可以自定义
    Hello: HelloWorld
  }
}
</script>
```



### 全局组件

在 `main.js` 中通过 `Vue.component()` 定义全局组件，这样组件就可以在所有页面直接使用了

```js
// main.js

import Vue from "vue";
import App from "./App.vue";

// 引入组件
import HelloWorld from "@/components/HelloWorld";
// 将组件注册到全局
Vue.component("HelloWorld", HelloWorld);

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
}).$mount("#app");
```



```vue
<!-- App.vue -->

<template>
  <div>
    <!-- 直接使用，不需要再次引入组件了 -->
    <HelloWorld />
  </div>
</template>

<script>

export default {

}
</script>

<style>

</style>
```



### scoped

默认情况下，`Vue` 最终会把所有文件打包到一个 `html` 文件里，所以在组件中或其他地方修改样式会影响到全部。所以就需要用到 `scoped` 属性解决样式冲突。

只需要在 `style` 中加上 `scoped` 即可实现样式隔离。这样当前页面写的任何样式不会影响到其他页面，其他页面写的样式也不会影响当前页面

```css
<style scoped>
  /* css代码 */
</style>
```



## 组件之间的通信

### 父向子通信

**父组件**

```vue
<!-- App.vue -->
<template>
  <div>
    <MyProduct title="手机" :price="price" />
  </div>
</template>

<script>
import MyProduct from './components/MyProduct.vue';
export default {
  data() {
    return {
      price: 3000
    }
  },
  components: {
    MyProduct: MyProduct
  }
}
</script>
```



**子组件**

```vue
<template>
    <div>
        <!-- 可以直接在模板中使用 -->
        <h1>商品名称：{{ title }}</h1>
        <h3>商品价格：{{ price }}</h3>
    </div>
</template>

<script>
export default {
    // 通过props接收父组件向子组件传递的数据
    props: ['title', 'price'],
    
    // 也可以直接this.进行使用
    created() {
        console.log(this.title, this.price); // 手机 3000
    }
}
</script>
```



**循环传值给组件**

```vue
<!-- App.vue -->
<template>
  <div>
    <div v-for="item, index in list" :key="index">
      <MyProduct :title="item.title" :price="item.price" />
    </div>
  </div>
</template>

<script>
import MyProduct from './components/MyProduct.vue';
export default {
  data() {
    return {
      list: [
        {
          title: "手机",
          price: 3000
        },
        {
          title: "电脑",
          price: 6000
        }
      ]
    }
  },
  components: {
    MyProduct: MyProduct
  }
}
</script>
```



**注意：**子组件不能直接修改父组件通过 `props` 传递的数据，因为 `props` 是只读的，只能获取不能修改。



### 子向父通信

**子组件**

```vue
<!-- MyProduct.vue -->

<template>
    <div>
        <h1>商品名称：{{ title }}</h1>
        <h3>商品价格：{{ price }}</h3>
        <button @click="btn">砍价</button>
    </div>
</template>

<script>
export default {
    // 注意：props是只读的，不能修改值
    props: ['title', 'price'],
    methods: {
        btn() {
            // 生成一个1~10之间的随机数
            let random = Math.floor(Math.random() * (10 - 1 + 1)) + 1
            // 每次点击就会把随机数的值传递给具有自定义事件：bargain 的父组件
            this.$emit('bargain', random)
        }
    }
}
</script>
```



**父组件**

```vue
<!-- App.vue -->

<template>
  <div>
    <!-- 必须是自定义事件：bargain 绑定的方法才能接收子组件传递的数据 -->
    <MyProduct title="手机" :price="price" @bargain="haggle" />
  </div>
</template>

<script>
import MyProduct from './components/MyProduct.vue';
export default {
  components: {
    MyProduct: MyProduct
  },
  data() {
    return {
      price: 3000
    }
  },
  methods:{
    haggle(n){
      // 然后可以通过第一个参数 n 拿到子组件传递过来的数据
      this.price -= n
    }
  }
}
</script>
```



### EventBus

父子组件通讯很麻烦，这时候可以使用 `EventBus` 跨组件通讯方案，也可以叫**事件总线**



**EventBus的使用步骤：**

1. 创建 `EventBus.js` 文件，并向外共享一个空的 `Vue` 实例对象

2. 在数据发送方，调用 `bus.$emit('事件名称', 要发送的数据)` 方法触发自定义事件

3. 在数据接收方，调用 `bus.$on('事件名称', 事件处理函数)` 方法注册一个自定义事件



**第一步：**创建 `EventBus.js` 文件，并向外共享一个空的 `Vue` 实例对象

```js
// 引入Vue
import Vue from 'vue';

// 默认导出
export default new Vue
```



**第二步：**调用 `bus.$emit('事件名称', 要发送的数据)` 方法触发自定义事件

```vue
<!-- Hello.vue -->

<template>
    <div></div>
</template>

<script>
// 引入EventBus
// import EventBus from '@/EventBus/index';
import bus from '@/EventBus';
export default {
    created() {
        bus.$emit('info', '全栈开发工程师')
    }
}
</script>
```



**第三步：**调用 `bus.$on('事件名称', 事件处理函数)` 方法注册一个自定义事件

```vue
<!-- World.vue -->

<template>
    <div></div>
</template>

<script>
// 引入EventBus
import bus from '@/EventBus';
export default {
    created() {
        bus.$on('info', (val) => {
            console.log('接收Hello组件中的数据', val);
        })
    }
}
</script>
```



## props

```vue
<template>
  <div class="header">{{ title }}</div>
</template>

<script>
export default {
  props: ["title"]
}
</script>
```



### type

类型校验

```vue
<template>
  <div class="header">{{ title }}</div>
</template>

<script>
export default {
  props: {
    // 规定传入的title必须是字符串，否则就会报错
    title: {
      type: String
    }
  }
}
</script>
```



### default

默认值

```vue
<template>
  <div class="header">{{ title }}</div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      // 如果没有向子组件传值，则title的默认值就是：努力再努力
      default: "努力再努力"
    },
  }
}
</script>
```



### required

必选项

```vue
<template>
  <div class="header">{{ title }}</div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      // 规定title必选项，如果父组件不传title就会报错
      required: true
    },
  }
}
</script>
```



### 总结

**props: []** - 只能声明变量 or 接收, 不能类型校验

**props: {}** - 声明变量和校验类型规则 - 外部传入值不对则报错



## 生命周期

| **阶段** | **方法名**    | **方法名** |
| -------- | ------------- | ---------- |
| 初始化   | beforeCreate  | created    |
| 挂载     | beforeMount   | mounted    |
| 更新     | beforeUpdate  | updated    |
| 销毁     | beforeDestroy | destroyed  |



### beforeCreate

此阶段什么都干不了，因为 `props`、`data`、`methods` 都还没有创建，处于不可用状态。



### created

此阶段组件的 `props`、`data`、`methods` 都已经创建好了，可以正常使用，但是组件的 `HTML` 模板结构还没创建成功，所以此阶段不能操作 `DOM`



`created` 常用于调用 `methods` 中的方法来发起API网络请求



### beforeMount

此阶段即将把内存中编译好的 `HTML` 结构渲染到浏览器中，此时浏览器中还没有当前组件的 `DOM` 结构，所以还是没办法操作 `DOM` 但是在他之前的方法都可以正常使用



### mounted

此阶段已经把内存中的 `HTML` 结构渲染到了浏览器中，所以现在可以操作 `DOM` 了



### beforeUpdate

此阶段即将把变化后的最新数据重新渲染到组件的 `HTML` 模板结构

只要 `DOM` 发生变化就会自动执行 `beforeUpdate` 函数，因为还没有重新渲染成功所以在这个阶段 `DOM` 还是旧值

比如说点击按钮把 `data` 中的 `info` 属性值：`Hello World!` 改成：`Hello`，这个阶段只能获取修改前的值：`Hello World!`



### updated

此阶段已经把变化后的最新数据重新渲染到了组件的 `HTML` 模板结构，所以可以获取到最新的 `DOM`值：`Hello`



### 代码示例

```vue
<template>
    <div class="box"></div>
</template>

<script>
export default {
    data() {
        return {
            info: "Hello World!"
        }
    },
    beforeCreate() {
        // 此阶段什么都干不了，因为 props、data、methods 都还没有创建，处于不可用状态。
        console.log(this.info); // undefined
    },
    created() {
        // 此时 props、data、methods 都已经创建好了，可以正常使用
        console.log(this.info); // Hello World!

        // 但是组件的HTML模板还没有创建成功，所以获取不到DOM元素
        console.log(document.querySelector('.box')); //null
    },
    beforeMount() {
        // 此阶段即将把内存中编译好的 HTML 结构渲染到浏览器中，此时浏览器中还没有当前组件的 DOM 结构，所以还是没办法操作 DOM
        console.log(document.querySelector('.box')); // null

        // 但是在他之前的方法都可以正常使用
        // 比如调用接口、修改数据
    },
    mounted() {
        // 此阶段已经把内存中的 HTML 结构渲染到了浏览器中，所以现在可以操作 DOM 了
        console.log(document.querySelector('.box'));
        // <div class="box"></div>
    },
    beforeUpdate() {
        // 此阶段即将把变化后的最新数据重新渲染到组件的 HTML 模板结构
        // 只要 DOM 发生变化就会自动执行 beforeUpdate 函数，因为还没有重新渲染成功所以在这个阶段 DOM 还是旧值
        // 比如说点击按钮把 data 中的 info 属性值：Hello World! 改成：Hello，这个阶段只能获取修改前的值
        console.log(this.info); // 修改前的DOM值：Hello World!
    },
    updated(){
        // 此阶段已经把变化后的最新数据重新渲染到了组件的 HTML 模板结构，所以可以获取到最新的 DOM
        console.log(this.info); // 修改后的DOM值：Hello
    }
}
</script>
```



## axios

### GET

GET 请求使用 `params` 传参

```js
    async created() {
        // 方式一、
        axios({
            method: "GET", // 默认就是GET方式请求, 可以省略不写
            url: "http://123.57.109.30:3006/api/getbooks",
            // GET请求使用params传参
            params: {
                bookname: "西游记"
            }
        }).then(res => {
            console.log(res);
        })

        // 方式二、
        const res = await axios.get("http://123.57.109.30:3006/api/getbooks", { bookname: "西游记" })
        console.log(res);
    }
```



### POST

POST 请求使用 `data` 传参

```js
    async created() {
        // 方式一、
        axios({
            method: "POST",
            url: "http://123.57.109.30:3006/api/addbook",
            // POST请求使用data传参
            data: {
                bookname: "西游记",
                author: "吴承恩",
                publisher: "吴承恩"
            }
        }).then(res => {
            console.log(res);
        })

        // 方式二、
        const res = await axios.post("http://123.57.109.30:3006/api/addbook", {
            bookname: "西游记",
            author: "吴承恩",
            publisher: "吴承恩"
        })
        console.log(res);
    }
```



### 全局配置

```js
// 配置根域名
axios.defaults.baseURL = "http://123.57.109.30:3006"

// 所有请求的url前置可以去掉, 请求时, axios会自动拼接baseURL的地址在前面
created() {
    axios({
        url: "/api/getbooks",
        method: "GET", // 默认就是GET方式请求, 可以省略不写
    }).then((res) => {
        console.log(res);
    });
},
```



## 组件进阶

### 动态组件

`component` 的属性 `is` 是哪个组件，就显示哪个组件

```vue
<template>
    <div>
        <button @click="com = 'A'">切换到A组件</button>
        <button @click="com = 'B'">切换到B组件</button>
        <component :is="com"></component>
    </div>
</template>

<script>
import A from '@/components/A.vue'
import B from '@/components/B.vue'
export default {
    data() {
        return {
            // com的值是对应组件的名子，他的值是什么就显示哪个组件
            com: "A"
        }
    },
    components: {
        A, B
    }
}
</script>
```



### keep-alive

默认情况下动态组件不具备缓存功能，所以每次切换组件都会把该组件的声明周期都执行一遍。

```vue
<template>
  <div>我是A组件</div>
</template>

<script>
export default {
  created(){
    console.log('A 组件创建了');
  },
  destroyed() {
    console.log('A 组件销毁了');
  },
}
</script>
```



如果我们想让它的生命周期只执行一次，则需要使用缓存功能 `keep-alive`

使用 `Vue` 内置的 `keep-alive` 组件, 可以让包裹的组件保存在内存中不被销毁

```vue
<template>
    <div>
        <button @click="com = 'A'">登录</button>
        <button @click="com = 'B'">注册</button>

        <!-- 组件缓存使用 keep-alive 包裹 -->
        <keep-alive>
            <component :is="com"></component>
        </keep-alive>
    </div>
</template>

<script>
import A from '@/components/A.vue'
import B from '@/components/B.vue'
export default {
    data() {
        return {
            // com的值是对应组件的名子，他的值是什么就显示哪个组件
            com: "A"
        }
    },
    components: {
        A, B
    }
}
</script>
```

**总结:** `keep-alive` 可以提高组件的性能, 内部包裹的标签不会被销毁和重新创建, 触发激活和非激活的生命周期方法



#### include

默认情况下使用 `keep-alive` 会导致所有组件缓存，如果我们只想让某个组件缓存就可以用到 `include` 属性指定需要缓存的组件

```VUE
        <keep-alive include="A,B">
            <component :is="com"></component>
        </keep-alive>
```



#### exclude

如果我们想让某个组件不缓存，其它组件缓存就可以用到 `exclude` 属性

```VUE
        <keep-alive exclude="B">
            <component :is="com"></component>
        </keep-alive>
```



#### 激活和非激活

> 目标: 被缓存的组件不再创建和销毁, 而是激活和非激活

补充2个钩子方法名:

​	activated – 激活时触发

​	deactivated – 失去激活状态触发



### 组件插槽

`Vue` 提供组件插槽能力, 允许开发者在封装组件时，把不确定的部分定义为插槽



#### 默认插槽

```vue
<template>
    <div>
        <!-- Hello会在组件中的slot位置显示 -->
        <com>Hello</com>
    </div>
</template>

<script>
import com from '@/components/com.vue'
export default {
    components: { com }
}
</script>
```



#### 具名插槽

如果有多个插槽，则需要使用具名插槽

```vue
<!-- App.vue -->
<template>
    <div>
        <com>
            <!-- 两种方式都可以 -->
            <template v-slot:title>标题标题</template>
            <!-- v-slot可以简化成#使用 -->
            <template #content>内容内容内容</template>
        </com>
    </div>
</template>
```

```vue
<!-- com.vue -->
<template>
  <div>
    <div><slot name="title"></slot></div>
    <div><slot name="content" /></div>
  </div>
</template>
```

`v-slot` 可以简化成 `#` 使用

`v-bind` 可以省略成:  `v-on: `可以省略成`@`   那么 `v-slot:`  可以简化成 `#`

**总结:** `slot` 的 `name` 属性起插槽名, 使用组件时, `template` 配合 `#` 插槽名传入具体标签



#### 插槽作用域

```vue
<!-- App.vue -->
<template>
    <div>
        <com>
            <!-- 三种方式都可以 -->
            <template v-slot:title="obj1">标题标题 {{ obj1.info }}</template>
            <template #content="{ info }">内容内容内容 {{ info }}</template>
        </com>
    </div>
</template>
```

```vue
<!-- com.vue -->
<template>
  <div>
    <div><slot name="title" info="Hello"></slot></div>
    <!-- 内容内容内容 Hello -->
    <div><slot name="content" info="World"/></div>
    <!-- 内容内容内容 World -->
  </div>
</template>
```



## ref

某些特殊情况下我们需要操作 `dom` 的 `api`，则需要使用 `ref` 方法来获取真实 `dom` 元素

```vue
<template>
    <div>
        <!-- 第一步：给元素设置ref属性 -->
        <div ref="dom">Hello World!</div>
        <button @click="btn">按钮</button>
    </div>
</template>

<script>
export default {
    methods:{
        btn(){
            // console.log(document.querySelector('div')); //不推荐
            // console.log(this.$refs); // 推荐

            // 第二步：使用this.$refs来获取对应的dom元素进行操作
            this.$refs.dom.style.color = "red"
        }
    }
}
</script>
```



### 调用组件实例

```vue
<!-- App.vue -->
<template>
    <div>
        <com ref="com" />
    </div>
</template>

<script>
import com from '@/components/com.vue'
export default {
    components: { com },
    mounted() {
        // 调用组件data中的数据
        console.log(this.$refs.com.$data.str); // Hello

        // 调用组件methods中的方法
        this.$refs.com.fn() // World
    }
}
</script>
```

```vue
<!-- com.vue -->
<template>
  <div />
</template>

<script>
export default {
  data() {
    return {
      str: "Hello"
    }
  },
  methods: {
    fn() {
      console.log("World");
    }
  }
}
</script>
```



### $nextTick

等DOM渲染完毕后，在下一次更新循环结束时执行 `nextTick` 中的回调函数

先看以下例子了解为什么要使用 `$nextTick`

```vue
<template>
    <div>
        <div ref="dom" v-if="is">Hello World!</div>
        <button @click="btn">显示并把颜色改成红色</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            is: false
        }
    },
    methods: {
        btn() {
            // 让dom元素显示
            this.is = true
            // 然后改变颜色为红色
            this.$refs.dom.style.color = "red"
        }
    }
}
</script>
```

这个时候点击按钮就会报错：`TypeError: Cannot read properties of undefined (reading 'style')`

这时候 `this.$refs.dom` 的值为 `undefined` 所以不能点出 `style`

报错的原因是 `js` 是单线程的，`this.is = true` 时候还没有更新 `dom` 元素，所以下面通过 `ref` 获取不到那个`dom` 元素。因此就会报错，这时候就需要用到 `$nextTick` 方法了



 `$nextTick` 等 `dom` 重新渲染完毕之后执行

```vue
<template>
    <div>
        <div ref="dom" v-if="is">Hello World!</div>
        <button @click="btn">显示并把颜色改成红色</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            is: false
        }
    },
    methods: {
        btn() {
            // 让dom元素显示
            this.is = true

            // 等DOM渲染完毕后，在下一次更新循环结束时执行nextTick中的回调函数
            this.$nextTick(() => {
                // 然后改变颜色为红色
                this.$refs.dom.style.color = "red"
            })
        }
    }
}
</script>
```

等 `dom` 重新加载完毕之后执行 `$nextTick` 回调里面的代码，相当于 `dom` 显示后再进行操作



**应用场景**

1. 如果想要在修改数据后立刻得到更新后的 `DOM` 结构，可以使用 `Vue.nextTick()`

2. 在 `created` 生命周期中进行 `DOM` 操作



**总结**

在 `$nextTick` 当中的代码不会立即执行，而是等 `DOM` 更新完成之后再执行，这样我们拿到的一定是最新的数据



## directives

`directives` 用于自定义局部、全局事件

### 局部自定义事件

**局部自定义事件：**只能在当前文件或组件使用

```vue
<template>
    <div v-color>Hello World</div>
</template>

<script>
export default {
    // 自定义事件
    directives: {
        // 事件名称
        color: {
            // 当事件被v-color绑定时，会立即触发bind函数
            bind(el) {
                // el = 绑定此事件的原生DOM对象
                el.style.color = "red"
            }
        }
    }
}
</script>
```



### value

获取自定义事件的 `value` 值

```vue
<template>
    <div v-color="'blue'">Hello World</div>
</template>

<script>
export default {
    directives: {
        color: {
            // binding 可以自定义
            bind(el, binding) {
                // 通过binding.value可以拿到自定义事件的值
                console.log(binding.value);
                el.style.color = binding.value
            }
        }
    }
}
</script>
```



### update

`bind` 函数只会执行一次，当数据发生变化时候，`bind` 函数将不再执行。`update` 函数会在每次 `DOM` 更新时执行

```vue
<template>
    <div>
        <div v-color="color">Hello World</div>

        <!-- 1. bind函数只会执行一次，当数据更新后就不会再执行了 -->
        <button @click="color = 'red'">按钮</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            color: "blue"
        }
    },
    directives: {
        color: {
            // 只执行一次
            bind(el, binding) {
                el.style.color = binding.value
            },
            // 2. 所以这时候需要用到update函数，当数据更新后执行
            update(el, binding) {
                el.style.color = binding.value
            }
        }
    }
}
</script>
```



**简写：**第一次会自动执行，数据更新后也会执行

```vue
<template>
    <div>
        <div v-color="color">Hello World</div>

        <button @click="color = 'red'">按钮</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            color: "blue"
        }
    },
    directives: {
        // 简写形式：第一次会自动执行，数据更新后也会执行
        color: (el, binding) => {
            // 这里的代码相当于在：bind、update中都写了一遍
            el.style.color = binding.value
        }
    }
}
</script>
```



### 全局自定义事件

**全局自定义事件：**在所有文件、组件中都可以直接使用

```js
// 定义全局事件
Vue.directive("color", {
  bind(el, binding) {
    el.style.color = binding.value;
  },
  update(el, binding) {
    el.style.color = binding.value;
  },
});
```

```js
// 简写
Vue.directive("color", (el, binding) => {
  el.style.color = binding.value;
});
```



## Vue-router

### 路由原理

```vue
<template>
  <div class="app-container">
    <h1>App 根组件</h1>

    <a href="#/Home">首页</a>
    <a href="#/Movie">电影</a>
    <!-- #后面也可以不加/，最好加上 -->
    <a href="#About">关于</a>
    <hr />

    <component :is="com"></component>
  </div>
</template>

<script>
// 导入组件
import Home from '@/components/Home.vue'
import Movie from '@/components/Movie.vue'
import About from '@/components/About.vue'

export default {
  name: 'App',
  data() {
    return {
      com: "Home"
    }
  },
  // 注册组件
  components: {
    Home,
    Movie,
    About
  },
  created() {
    // 监听哈希路由是否发生变化
    window.onhashchange = () => {
      // 只要发生变化就通过location.hash拿到最新的哈希值 如：#Home
      // console.log(location.hash);

      // 根据不同哈希路由显示不同组件
      switch (location.hash){
        case "#/Home":
          this.com = "Home"
          break
        case "#/Movie":
          this.com = "Movie"
          break
        case "#About":
          this.com = "About"
          break
      }
    }
  }
}
</script>

<style lang="less" scoped>
.app-container {
  background-color: #efefef;
  overflow: hidden;
  margin: 10px;
  padding: 15px;

  >a {
    margin-right: 10px;
  }
}
</style>
```



### 安装路由

```
yarn add vue-router@3.5.2
```

**Vue2** 使用 `vue-router3`

**Vue3** 使用 `vue-router4`



### 配置路由

`src -> router -> index.js`

```javascript
import Vue from 'vue'
// 引入路由
import VueRouter from 'vue-router'

// 安装路由
Vue.use(VueRouter)

// 配置路由
const router = new VueRouter()

// 导出路由
export default router
```



`src -> main.js`

```javascript
import Vue from 'vue'
import App from './App.vue'

// 引入路由
import router from '@/router'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  // 路由实例对象
  router
}).$mount('#app')
```



### 基本使用

`src -> App.vue`

```vue
<template>
  <div class="app-container">
    <h1>App 根组件</h1>

    <a href="#/Home">首页</a>
    <a href="#/Movie">电影</a>
    <a href="#/About">关于</a>
    <hr />

    <router-view></router-view>
  </div>
</template>
```



`src -> router -> index.js`

```javascript
import Vue from "vue";
// 引入路由
import VueRouter from "vue-router";

// 引入组件
import Home from '@/components/Home.vue'
import About from '@/components/About.vue'
import Movie from '@/components/Movie.vue'

// 安装路由
Vue.use(VueRouter);

// 配置路由
const router = new VueRouter({
  routes: [
    // { path: "哈希路径，不带#", component: 对应的组件 }
    { path: "/Home", component: Home },
    { path: "/About", component: About },
    { path: "/Movie", component: Movie },
  ],
});

// 导出路由
export default router;
```



### router-link

 `router-link` 相当于 `a` 标签

它的属性 `to` 相当于 `a` 标签中的 `href`

用 `router-link` 配合 `to`, 实现点击切换路由

```html
<router-link to="需要跳转的路由">
<!-- <router-link to="/Home">首页</router-link> -->
```

 

`router-link` 内置了一个 `css` 类名：`.router-link-active` 他可以很方便的实现点击哪个就让哪个高亮的效果

```css
/* 被点击的导航样式 */
.router-link-active {
  color: red;
}
```



### 配置路由

**第一步**

`src/router/index.js`

```javascript
import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import Home from '@/view/Home'
import Cate from '@/view/Cate'
import Find from '@/view/Find'
import My from '@/view/My'

const router = new VueRouter({
    routes: [
        {
            //点击哪个路由会根据path的值进行匹配，如果匹配上就显示对应的component组件
            path: '/home',
            component: Home
        },
        {
            path: '/cate',
            component: Cate
        },
        {
            path: '/find',
            component: Find
        },
        {
            path: '/my',
            component: My
        }
    ]
})

export default router
```



**第二步**

```javascript
import Vue from 'vue'
import App from './App.vue'
//导入路由
import router from '@/router/index'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router // 挂载到Vue实例
}).$mount('#app')
```



**第三步**

```vue
<template>
  <div>
    <router-link to="/home">首页</router-link>
    <router-link to="/cate">分类</router-link>
    <router-link to="/find">发现</router-link>
    <router-link to="/my">我的</router-link>
    
    <router-view></router-view>
  </div>
</template>

<script>
export default {

}
</script>

<style>
/* 被点击的导航样式 */
.router-link-active {
  color: red;
}
</style>
```



### 重定向

用户在访问地址A的时候，强制用户跳转到地址C，从而展示特定的组件页面。

通过路由规则的 `redirect` 属性，指定一个新的路由地址，可以很方便的设置路由的重定向

```js
{ path: "/", redirect: "/Home" },
```



**示例**

```javascript
const router = new VueRouter({
    routes: [
        {
            //用户默认跳转到home首页
            path:'/',
            redirect:'/home'
        },
        {
            //访问指定path的路由，显示component指定的组件
            path: '/home', //对应的路由
            component: Home //对应的组件
        },
        {
            path: '/cate',
            compoent: Cate
        },
        {
            path: '/my',
            component: My
        }
    ]
})
```



### 404配置

所有页面都匹配不到，就显示404对应的页面

`src -> component -> No.vue`

```vue
<template>
  <img src="../assets/404.png" alt="">
</template>

<script>
export default {

}
</script>

<style scoped>
    img{
        width: 100%;
    }
</style>
```



`src/router/index.js`

```javascript
const router = new VueRouter({
    routes: [
        // 404配置
        {
            path: '*',
            component: No
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/cate',
            compoent: Cate
        },
        {
            path: '/my',
            component: My
        }
    ]
})
```



### 路由嵌套

`src -> App.vue`

```vue
    <router-link to="/Home">首页</router-link>
    <router-link to="/Movie">电影</router-link>
    <router-link to="/About">关于</router-link>
```



`src -> components -> About.vue`

```vue
<template>
  <div class="about-container">
    <h3>About 组件</h3>
    
    <!-- 二级路由 -->
    <router-link to="/About/Tab1">Tab1</router-link>
    <router-link to="/About/Tab2">Tab2</router-link>
  
    <router-view></router-view>
  </div>
</template>
```



`src -> router -> index.js `

```javascript
const router = new VueRouter({
  routes: [
    {
      path: "/About",
      component: About,
      children: [
        // 子路由中的path最好不要加上/
        { path: "Tab1", component: Tab1 },
        { path: "Tab2", component: Tab2 },
      ],
    },
  ],
});
```



### 动态路由

#### 基本使用

`src -> App.vue`

```vue
    <router-link to="/Home">首页</router-link>
    <router-link to="/Movie/1">满江红</router-link>
    <router-link to="/Movie/2">流浪地球</router-link>
    <router-link to="/Movie/3">深海</router-link>
    <router-link to="/About">关于</router-link>

    <router-view></router-view>
```



`src -> router -> index.js `

```javascript
routes: [
    { path: "/Movie/:id", component: Movie },
],
```



**拿到动态路由传递的值**

`src -> components -> Movie.vue`

```vue
<template>
  <div class="movie-container">
    <h3>Movie 组件</h3>

    <!-- 在template模板中this可加可不加 -->
    <!-- <div>{{ this.$route.params.id }}</div> -->
    <div>{{ $route.params.id }}</div>
  </div>
</template>
```



#### props传参

`src -> App.vue`

```vue
    <router-link to="/Home">首页</router-link>
    <router-link to="/Movie/1">满江红</router-link>
    <router-link to="/Movie/2">流浪地球</router-link>
    <router-link to="/Movie/3">深海</router-link>
    <router-link to="/About">关于</router-link>

    <router-view></router-view>
```



`src -> router -> index.js `

```javascript
const router = new VueRouter({
  routes: [
    // props:true即可使用props很方便的接收参数
    { path: "/Movie/:id", component: Movie, props: true },
  ],
});
```



**拿到动态路由传递的值**

`src -> components -> Movie.vue`

```vue
<template>
  <div class="movie-container">
    <h3>Movie 组件</h3>

    <div>{{ id }}</div>
  </div>
</template>

<script>
export default {
  name: 'Movie',
  props: ["id"]
}
</script>
```



#### query and fullPath

`src -> App.vue`

```vue
    <router-link to="/Home">首页</router-link>
    <router-link to="/Movie/1">满江红</router-link>
    <router-link to="/Movie/2?name=zs&age=20">流浪地球</router-link>
    <router-link to="/Movie/3">深海</router-link>
    <router-link to="/About">关于</router-link>

    <router-view></router-view>
```



**拿到动态路由传递的值**

`src -> components -> Movie.vue`

```javascript
export default {
  name: 'Movie',
  created(){
    console.log(this.$route.fullPath);
    // 获取完整路径信息：/Movie/2?name=zs&age=20

    console.log(this.$route.path);
    // 获取部分路径信息：/Movie/2

    console.log(this.$route.query);
    // 获取参数信息：{name: 'zs', age: '20'}
  }
}
```



### 声明式导航

```vue
<template>
  <div>
    <router-link to="/home">首页</router-link>
    <router-link to="/cate/1000?name=lyy&&age=19">分类</router-link>
    <router-link to="/find/">发现</router-link>
    <router-link to="/my">我的</router-link>
    
    <router-view></router-view>
  </div>
</template>

<script>
export default {

}
</script>

<style>
/* 被点击的导航样式 */
.router-link-active {
  color: red;
}
</style>
```



`src/router/index.js`

```javascript
import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import Home from '@/view/Home'
import Cate from '@/view/Cate'
import Find from '@/view/Find'
import My from '@/view/My'

const router = new VueRouter({
    routes: [
        {
            path:'/',
            redirect: '/home'
        },
        {
            path:'*',
            component: Home
        },
        {
            path: '/home',
            component: Home,
        },
        {
            path: '/cate/:cid', // 有:的路径代表要接收具体的值
            component: Cate,
        },
        {
            path: '/find',
            component: Find,
        },
        {
            path: '/my',
            component: My,
        }
    ]
})

export default router
```



**接收参数**

```vue
    接收query的参数
	<div>姓名：{{$route.query.name}}</div> lyy
    <div>年龄：{{$route.query.age}}</div> 19

	接收params的参数
    <div>$route.params.cid：{{$route.params.cid}}</div> 1000
```



### 编程式导航

#### path

**语法**

`$router.push(匹配在路由中定义的path)`

```javascript
{ path: "/Home", component: Home }
```

```html
<button @click="$router.push('/Home')">跳转</button>
```



**示例**

使用 `path` 方式跳转并传参

```html
<button @click="go('/Home')">跳转</button>
```

```js
export default {
  name: 'App',
  methods:{
    go(path){
      // this.$router.push(path)

      this.$router.push({
        path,
        // 使用path方法无法获取params中的值
        params: {
          x: 100,
          y: 200
        },
        query: {
          x: 300,
          y: 400
        }
      })
    }
  }
}
```

在页面中通过 `$route.query.name` 拿到 `query` 的参数

**注意：**`path` 方式无法获取 `params` 中的值，只能通过下面的 `name` 方式获取 `params` 的值



#### name

`$router.push(匹配在路由中定义的name)`

```javascript
{ path: "/Home", component: Home, name:"nameHome" }
```

```vue
<button @click="$router.push('nameHome')">跳转</button>
```



**示例**

使用 `name` 方式跳转并传参

`name` 路由名在页面中看不到，可以随便定义 而 `path` 可以在 `url` 的 `hash` 值看到，所以尽量符合规范

```html
<button @click="go('nameHome')">跳转</button>
```

```js
export default {
  name: 'App',
  methods:{
    go(name){
      // this.$router.push(path)

      this.$router.push({
        name,
        params: {
          x: 100,
          y: 200
        },
        query: {
          x: 300,
          y: 400
        }
      })
    }
  }
}
```

在页面中通过 `$route.query.name` 拿到 `query` 的参数

在页面中通过 `$route.params.sex` 拿到 `params` 的参数



**path 与 name的区别**

`params` 不会在路由中显示参数，并且只能通过 `path` 方式才能获取值

`query` 会在路由中显示参数，并且可以使用 `path、name` 方式获取值



**扩展**

跳转：`$router.push()`

前进：`$router.forward()`

后退：`$router.back()`

前进 or 后退：`$router.go()`



**$route 和 $router**

`$route` 用于接收参数而 `$router` 用于跳转路由



### 常用的 RouterLink 属性

#### replace

设置 `replace` 属性的话，当点击时，会调用 `roter.replace()` 而不是 `router.push()`，所以导航后不会留下 `history` 记录，也就是不能回退到上一个页面

```vue
<router-link :to="{path: ‘/abc‘}" replace>ABC</router-link>
```



#### append

设置 `append` 属性后，则在当前路径前添加基路径，例如，我们从 `/a` 导航到一个相对路径 `b` ，如果没有配置 `append`，则路径为 `/b`，如果配了，则为 `/a/b`

```vue
<router-link to="b" append>Home</router-link>
```



#### tag

有时候想要 `<router-link>` 渲染成某种标签，例如 `<li>` 。于是我们使用 `tag prop` 类指定何种标签，同样它还是会监听点击，触发导航。

```vue
<router-link to="/foo" tag="li">FOO</router-link>
```

渲染的结果：`<li>FOO</li>`

注意：Vue3不起效果，貌似移除了tag



#### active-class

设置链接激活时使用的 `css` 类名。默认值可以通过路由的构造选项 `linkActiveClass` 来全局配置,  默认值为 `router-link-active`

```javascript
export default New Router({   linkActiveClass: ‘active‘ })
```



#### exact

"是否激活"，默认是false 。举个粟子，如果当前的路径是/a 开头的，那么<router-link to="/a"> 也会被设置css类名。

按照这个规则，<router-link to="/"> 将会点亮各个路由！想要链接使用"exact匹配模式"，则使用exact属性：

```vue
// 这个链接只会在地址为 / 的时候被激活 
<router-link to="/" exact>Home</router-link>
<router-link to="/user">USER</router-link>
<router-link to="/user/userinfo">USER-info</router-link>
// 如果不设置exact，则当路由到了/user/userinfo 页面时，USER也是被设置了router-link-active样式的！
```



#### events

声明可以用来触发导航的事件（默认是‘click‘）。可以是一个字符串或者是一个包含字符串的数组。



### 模式设置

路由模式有两种：

`hash` 方式：http://localhost:8080/#/home （默认）

`history` 方式：http://localhost:8080/home （上线后需要服务器端支持）



在 `router/index.js` 文件中设置模式

```javascript
const router = new VueRouter({
    routes,
    mode:"history"
})
```



### 路由守卫

路由守卫一般用于判断用户是否登录，登录后就会有 `Token`，如果没有 `Token` 则未登录。我们可以通过路由守卫判断有没有 `Token`，如果有就 `next()` 放行，反之跳转到登录页 `next("/login")`



#### 前置路由守卫

每次路由跳转时候都会执行回调里面的代码

```javascript
router.beforeEach((to, from, next) => {})
```



它的三个参数分别是：`to`、`from`、`next`

**to：**跳转到的路由信息

**from：**前路由跳转的信息

**next：**是否放行



#### next

放行，放行之后路由才能够正常跳转切换

```javascript
next()
```



强制跳转到指定的 `path` 路由

```javascript
next("/login")
```



在原地保持不动

```javascript
next(false)
```



#### 后置路由守卫

**应用场景：**对跳转后的页面进行滚动条回调0 0 位置、更新页面title、懒加载结束等等

```javascript
router.afterEach((to, from) => {
    setTitle(to, router.app)
    iView.LoadingBar.finish()
    window.scrollTo(0, 0)
})
```



## Vant组件库

Vant组件库官网：https://vant-contrib.gitee.io/vant/v2/#/zh-CN/home



在 `main.js` 中根据需求导入



**按需导入**

```javascript
import { Button,Uploader } from 'vant';
import 'vant/lib/button/style'
import 'vant/lib/uploader/style'
Vue.use(Button);
Vue.use(Uploader);
```



**导入全部**

```javascript
import vant from 'vant';
import 'vant/lib/index.css'
Vue.use(vant)
```



**使用**

```vue
<template>
  <div>
    <van-button type="primary">主要按钮</van-button>
    <van-button type="info">信息按钮</van-button>
    <van-button type="default">默认按钮</van-button>
    <van-button type="warning">警告按钮</van-button>
    <van-button type="danger">危险按钮</van-button>
  </div>
</template>

<script>
export default {

};
</script>

<style>
</style>
```



## Vuex

> Vuex是一个专为Vue.js应用程序开发的状态管理模式，他采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化



**基本使用**

安装 `Vuex` 

```bash
yarn add vuex@3
```

`Vue2` 对应的 `vuex@3` 版本

`Vue3` 对应的 `vuex@4` 版本



`main.js`

```javascript
// 导包
import Vue from "vue";
import Vuex from "vuex";

// 安装Vuex
Vue.use(Vuex);

// 创建store对象
const store = new Vuex.Store({
  // 在这里写全局共享数据
  state:{
    str: "Hello World!",
  },
});

new Vue({
  render: h => h(App),
  // 挂载到Vue实例
  store
}).$mount('#app')

```



### State

`state` 用于存放页面中经常用到的数据，进行集中管理

```javascript
const store = new Vuex.Store({
  // 在这里写全局共享数据
  state:{
    str: "Hello World!",
  },
});
```



#### 基本使用

通过固定格式 `$store.state` 进行访问 `Vuex` 中的数据

```vue
<template>
  <div>{{ $store.state.str }}</div>
</template>

<script>
export default {
  created(){
    console.log(this.$store.state.str);
    // Hello World!
  }
}
</script>
```



#### mapState

`mapState` 是辅助函数，帮助我们把 `store` 中的数据映射到 组件的计算属性中, 它属于一种方便用法

**用法**

1.导入 `mapState` 

```javascript
import { mapState } from "vuex"
```



2.采用数组形式引入 `state` 属性

```javascript
mapState(["age"])
```



3.利用展开运算符将导出的状态通过计算属性映射到页面

```javascript
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["age"]),
  },
};
```



**示例**

```vue
<template>
  <div>
    {{ str }}
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState(['str'])
  },
  created() {
    console.log(this.str);
    // Hello World!
  }
}
</script>
```



### mutations

`state` 数据的修改只能通过 `mutations`，并且 `mutations` 必须是同步更新，目的是形成 **数据快照**

**数据快照：**一次 `mutation` 的执行，立刻得到一种视图状态，因为是立刻，所以必须是同步

#### 基本使用

`mutations` 是一个对象，对象中存放修改 `state` 的方法

```javascript
const store = new Vuex.Store({
  state: {
    str: "Hello World!",
  },
  mutations: {
    addMutations(state, n) {
      state.str = n;
      // str被修改为：Hello YuYang!
    },
  },
});
```

```vue
<template>
  <div>{{ $store.state.str }}</div>
  <!-- Hello YuYang! -->
</template>

<script>
export default {
  created() {
    // this.$store.commit(方法名, 自定义参数)
    this.$store.commit("addMutations", "Hello YuYang!")
  }
}
</script>
```



#### mapMutations

`mapMutations` 是 `Vuex` 的 `mutation` 的**辅助函数**，用于中映射 `mutation` 中的方法，以便在该页面中很方便的直接使用 `mutation` 里的方法 (**语法糖**)

**导入**

```javascript
import { mapMutations } from 'vuex'
```



**映射**

```javascript
methods: {
    ...mapMutations(["addMutations"])
},
```



**示例**

```javascript
const store = new Vuex.Store({
  state: {
    str: "Hello World!",
  },
  mutations: {
    addMutations(state, n) {
      state.str = n;
    },
  }
});
```

```vue
<template>
  <div>
    <div>{{ $store.state.str }}</div>
    <button @click="btn">按钮</button>
    
    <button @click="addMutations('Hello YuYang!')">按钮</button>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  // 在 methods 中映射 mutations 的方法
  methods: {
    ...mapMutations(["addMutations"]),
    
    btn(){
      this.addMutations('Hello YuYang!')
    }
  },
}
</script>
```



### actions

`state` 是存放数据的，`mutations` 是同步更新数据，`actions` 是负责操作异步数据的

#### 基本使用

```javascript
const store = new Vuex.Store({
  state: {
    str: "Hello World!",
  },
  mutations: {
    addMutations(state, val) {
      state.str = val;
    },
  },
  actions: {
    addActions(context) {
      // 1秒钟之后改变数据
      setTimeout(() => {
      // mutations 是修改数据的唯一途径，actions 只是为了处理异步代码，最终修改数据还是要通过 commit 给 mutations
        context.commit("addMutations", "Hello YuYang!");
      }, 1000);
    },
  },
});
```

```vue
<template>
  <div>
    <div>{{ $store.state.str }}</div>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.dispatch("addActions")
  }
}
</script>
```

**注意：**`mutations` 是修改数据的唯一途径，`actions` 只是为了处理异步代码，最终修改数据还是要通过 `commit` 给 `mutations`



#### mapActions

```javascript
const store = new Vuex.Store({
  state: {
    str: "Hello World!",
  },
  mutations: {
    addMutations(state, val) {
      state.str = val;
    },
  },
  actions: {
    addActions(context, n) {
      // 1秒钟之后改变数据
      setTimeout(() => {
        // mutations 是修改数据的唯一途径，actions 只是为了处理异步代码，最终修改数据还是要用 commit 提交给 mutations
        context.commit("addMutations", n);
      }, 1000);
    },
  },
});
```

```vue
<template>
  <div>
    <div>{{ $store.state.str }}</div>

    <button @click="addActions('Hello YuYang!')">按钮</button>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
  methods:{
    ...mapActions(["addActions"])
  }
}
</script>
```



### getters

`getters` 用于对 `store` 中的数据进行加工，返回一个新的数据

`store` 中的数据发生变化，`getters` 的数据也会跟着发生变化



#### 基本使用

```javascript
const store = new Vuex.Store({
  state: {
    str: "Hello ",
  },
  getters: {
    strGetters(state) {
      return state.str + "YuYang!";
      // Hello YuYang!
    },
  },
});
```

```vue
<template>
  <div>
    <div>{{ $store.getters.strGetters }}</div>
    <!-- Hello YuYang! -->
  </div>
</template>
```



#### mapGetters

```javascript
const store = new Vuex.Store({
  state: {
    str: "Hello ",
  },
  getters: {
    strGetters(state) {
      return state.str + "YuYang!";
      // Hello YuYang!
    },
  },
});
```

```vue
<template>
  <div>
    <div>{{ strGetters }}</div>
    <!-- Hello YuYang! -->
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
  computed:{
    ...mapGetters(["strGetters"])
  }
}
</script>
```



### Vuex大总结

`mutations` 是修改数据的唯一途径



`mutations` 只能处理同步代码 **而** `actions` 主要复制处理异步代码

如果 `mutations` 操作异步数据虽然页面中效果能实现，但是会跟 `Vue` 调试器的 `state` 数据不同，所以操作异步数据要通过 `actions` 
