# Vue3

## Vite

- 它是一个更加轻量（热更新速度快，打包构建速度快）的 `vue` 项目脚手架工具。
- 相对于 `vue-cli` 它默认安装的插件非常少，随着开发过程依赖增多，需要自己额外配置。
- **所以：** 在单纯学习 `vue3` 语法会使用它，后面做项目的时候我们还是使用 `vue-cli`



## 创建项目

- 创建项目 `npm init vite 项目名称` 或 `yarn create vite 项目名称`
- 安装依赖 `npm i` 或者 `yarn`
- 启动项目 `npm run dev` 或者 `yarn dev`

- 我们可以使用 `vite` 创建项目学习 `vue3` 语法，使用 `vue-cli` 创建项目正式开发。



## 组合式 API

### setup

```vue
<script>
export default {
  setup(){
    console.log(this,'setup 执行了'); // setup自身没有this
  },
  beforeCreate(){
    console.log('beforeCreate 执行了');
  }
  //undefined setup 执行了
  //beforeCreate 执行了
}
</script>
```

1. `setup` 自动执行, 并且在**所有**生命周期执行之前
2. 没有 `this` 指向, 不能绑定组件实例



**渲染数据**

```vue
<template>
  <div>{{ msg }}</div>
</template>

<script>
export default {
  setup() {
    const msg = "Hello World!"

    // 这里msg必须由return返回出来，页面中才能使用
    return { msg }
  }
}
</script>
```



**绑定方法**

由于数据不是响应式的。所以点击按钮修改 `msg` 的数据，在页面中其实并没有发生变化。但 `msg` 的值确实已经被修改为了：`"Hello Vue3!"`

```vue
<template>
  <!-- Hello World! -->
  <!-- 页面中的值不是响应式的，所以点击按钮修改值并不会发生变化 -->
  <div @click="btn">{{ msg }}</div>
</template>

<script>
export default {
  setup() {
    let msg = "Hello World!"

    const btn = () => {
      msg = "Hello Vue3!"
      console.log(msg);
      // "Hello Vue3!"
    }

    // 这里msg必须由return返回出来，页面中才能使用
    return { msg, btn }
  }
}
</script>
```



## 生命周期

### Vue2的生命周期

```
- beforeCreate
- created

- beforeMount
- mounted

- beforeUpdate
- updated

- beforeDestroy
- destroyed
```



### Vue3的生命周期

- `setup` 创建实例前
- `onBeforeMount` 挂载DOM前
- `onMounted` 挂载DOM后
- `onBeforeUpdate` 更新组件前
- `onUpdated` 更新组件后
- `onBeforeUnmount` 卸载销毁前
- `onUnmounted` 卸载销毁后



### 示例

```vue
<template>
  <div class="content">Hello World!</div>
</template>

<script>
// 导入生命周期函数
import { onBeforeMount, onMounted } from 'vue'
export default {
  setup() {
    // DOM渲染前
    onBeforeMount(() => {
      // 此时还获取不到DOM元素
      console.log('DOM渲染前：', document.querySelector('.content'));
    })

    // DOM渲染后
    onMounted(() => {
      console.log('DOM渲染后1：', document.querySelector('.content'));
    })

    // 相同的声明周期函数可以写多个，执行顺序由上到下
    onMounted(() => {
      console.log('DOM渲染后2：', document.querySelector('.content'));
    })
  }
}
</script>
```



## 响应式

### ref

默认 `Vue3` 不支持响应式，所以修改数据页面中不会发生变化。

我们可以通过 `ref` 函数将简单或复杂类型的数据转换响应式

**定义简单的数据类型为响应式**

```vue
<template>
  <div @click="btn">{{ msg }}</div>
</template>

<script>
import { ref } from 'vue'
export default {
  setup() {
    // 通过ref包裹的数据就会变成响应式数据
    const msg = ref("Hello World!")

    function btn() {
      // 只能通过.value属性改变响应式的值
      msg.value = "Hello Vue3!"
    }

    return { msg, btn }
  }
}
</script>
```



**定义复杂的数据类型为响应式**

```vue
<template>
  <div @click="btn">{{ obj.name }} {{ obj.age }}</div>
</template>

<script>
import { ref } from 'vue'
export default {
  setup() {
    const obj = ref({
      name: 'zs',
      age: 20
    })

    function btn() {
      // 注意：想要修改响应式数据，必须通过.value
      obj.value.name = "ls"
      obj.value.age = 30

      // 或者也可以使用这种写法：obj.value = { name: "ls", age: 30 }
    }

    return { obj, btn }
  }
}
</script>
```



### reactive

**文章推荐：** [vue3.0中reactive的正确使用姿势 - 南风晚来晚相识 - 博客园 (cnblogs.com)](https://www.cnblogs.com/IwishIcould/p/15096750.html)



`reactive` 它跟 `ref` 函数相同，不同的是 `reactive` 只能定义复杂类型为响应式数据，比如对象、数组等等。不能定义简单数据类型为响应式数据，比如：数字、字符串等等。而 `ref` 函数无论简单还是复杂类型都可以定义

所以一般 `reactive` 用于定义复杂数据类型，而 `ref` 用于定义简单数据类型为响应式数据



使用 `reactive` 将复杂类型数据定义为响应式，使数据与视图绑定为一起，这样只要数据发生变化既视图就会发生变化

```vue
<template>
  <div @click="btn">{{ obj.name }} {{ obj.age }}</div>
</template>

<script>
import { reactive } from 'vue'
export default {
  setup() {
    // 用reactive(包裹起来的数据就会转换为响应式数据)
    const obj = reactive({
      name: "yy",
      age: 20
    })

    function btn() {
      obj.age = 30
    }

    return { obj, btn }
  }
}
</script>
```

**注意：** `rective` 修改数据无需 `value`，而 `ref` 需要



**使用场景：**

- 当你明确知道需要的是一个响应式数据 对象 那么就使用 `reactive` 即可
- 其他情况使用 `ref`



### toRef

默认情况下我们想使用某响应式对象的某个属性，我们可以解构出来，但是解构出来的值就不是响应式了。

所以我们可以通过 `toRef` 函数可以将某个响应式对象的某个属性单独抽离出来，并且是响应式的

```vue
<template>
  <div @click="btn">{{ obj.name }} {{ obj.age }}</div>
  <div>{{ age }}</div>
</template>

<script>
import { reactive, toRef } from 'vue'
export default {
  setup() {
    const obj = reactive({
      name: "yy",
      age: 20
    })

    // 如果想要使用obj响应式对象中的某个数据不能直接解构出来, 因为解构出来后他的值就不是响应式的了
    // const { age } = obj

    // 此时我们可以通过toRef方法使obj响应式对象中单独的属性age变成响应式的
    const age = toRef(obj, 'age')

    function btn() {
      // 此时修改obj.age的值时，toRef的age也会发生变化，因为他们是响应式的
      // obj.age = 30

      // 如果我们想修改toRef的age可以这么写，不能直接age = 40
      age.value = 40
    }

    return { obj, age, btn }
  }
}
</script>
```



```vue
<template>
  <div>{{ name }}</div>
  <div>{{ msg }}</div>
  <button @click="btn">按钮</button>
</template>

<script>
import { ref, toRef, watch } from 'vue'
export default {
  setup() {
    const msg = ref({
      name: "鱿鱼须",
      age: 20,
      hobby: "敲代码"
    })

    const btn = () => {
      msg.value.name = "尤雨溪"
    }
    
    // 默认情况下响应式对象被解构出来后就失去了响应式特性，可以通过toRef重新使数据响应式
    // return { msg, name: mas.name, btn }
    return { msg, name: toRef(msg.value, "name"), btn }
  }
}
</script>
```



```javascript
  setup() {
    const msg = ref({
      name: "鱿鱼须",
      age: 20,
      hobby: "敲代码",
      obj: {
        abc: 100
      }
    })

    const btn = () => {
      // msg.value.name = "尤雨溪"
      msg.value.obj = { abc: 200 }
    }

    // msg对象中的obj改为响应式数据
    return { msg, name: toRef(msg.value, "obj"), btn }
  }
```



### toRefs

`toRefs` 函数可以把某个响应式对象中所有的属性变成响应式的，即使解构也不影响响应式

```vue
<template>
  <div @click="btn">{{ name }} {{ age }}</div>
  <div>{{ age }}</div>
</template>

<script>
import { reactive, toRefs } from 'vue'
export default {
  setup() {
    const obj = reactive({
      name: "yy",
      age: 20
    })

    // 将obj响应式数据中的所有属性都转换为响应式的，而且解构出来也不影响响应式
    const newObj = toRefs(obj)
    const { name, age } = newObj

    function btn() {
      // obj.name = 'ls'
      // obj.age = 30

      // 解构出来的值也是响应式的，并且需要加value
      name.value = 'ww'

      newObj.age.value = 50
    }

    return { ...newObj, btn }
  }
}
</script>
```



### 知识运用案例

```vue
<template>
  <div>
    坐标轴：{{ x }} {{ y }}
  </div>
</template>

<script>
import { onMounted, onUnmounted, reactive, toRefs } from 'vue'
export default {
  setup() {
    const obj = reactive({ x: 0, y: 0 })

    const move = (e) => {
      obj.x = e.pageX
      obj.y = e.pageY
    }

    // 绑定事件
    onMounted(() => {
      document.addEventListener('mousemove', move)
    })

    // 销毁事件
    onUnmounted(() => {
      document.removeEventListener('mousemove', move)
    })

    return { ...toRefs(obj) }
  }
}
</script>
```



**将代码单独抽离出来**

```vue
<template>
  <div>
    坐标轴：{{ x }} {{ y }}
  </div>
</template>

<script>
import { onMounted, onUnmounted, reactive, toRefs } from 'vue'

// 将代码单独抽离出来
const useMouse = () => {
  const obj = reactive({ x: 0, y: 0 })

  const move = (e) => {
    obj.x = e.pageX
    obj.y = e.pageY
  }

  // 绑定事件
  onMounted(() => {
    document.addEventListener('mousemove', move)
  })

  // 销毁事件
  onUnmounted(() => {
    document.removeEventListener('mousemove', move)
  })

  return obj
}

export default {
  setup() {
    const move = useMouse()

    return { ...toRefs(move) }
  }
}
</script>
```



## computed

**Vue2写法**

```javascript
  // 在这里定义计算属性
  computed: {
    // 定义一个计算属性
    sum() {
      // 当x或y其中一个发生变化就会自动触发这个sum()函数
      return this.x + this.y
    }
  }
```



**Vue3写法**

```javascript
    const sum = computed(() => {
      return this.x + this.y
    })
```



如果参数为一个**对象** 可以在这里写 `get`、`set`

```javascript
    const sum = computed({
      // get 方法
      get() {
        return 
      },
      // set 方法
      set() {
        
      },
    })
```



**示例**

**两数相加**

```vue
<template>
  <div>{{ sum }}</div>
  <button @click="addX"> x + 1 </button>
  <button @click="addY"> y + 1 </button>
</template>

<script>
import { ref, computed } from 'vue'
export default {
  setup() {
    const x = ref(0)
    const y = ref(0)

    const addX = () => {
      x.value++
    }

    const addY = () => {
      y.value++
    }

    // 当依赖的值x或y发生变化时就会自动触发计算属性
    const sum = computed(() => "计算后的结果：" + (x.value + y.value))

    return { addX, addY, sum }
  }
}
</script>
```



**搜索案例**

```vue
<template>
  <input type="text" v-model="search" />
  <ul>
    <li v-for="item, index in list" :key="index">{{ item }}</li>
  </ul>
</template>

<script>
import { ref, computed } from 'vue'
export default {
  setup() {
    const names = ref([
      "林俊杰",
      "孙燕姿",
      "周杰伦",
      "张惠妹",
      "刘若英",
      "林宥嘉",
      "刘德华",
      "张韶涵",
      "周笔畅",
      "孙楠",
    ])

    const search = ref("")

    // const list = computed(() => names.value.filter(item => item.includes(search.value)))
    const list = computed(() => {
      return names.value.filter(item => item.includes(search.value))
    })

    return { search, list, names }
  }
}
</script>
```



**缓存**

计算属性是基于它们依赖项的值结果进行缓存的，只要结果不变, 就直接从缓存中取值。如果值变了，就重新赋值到缓存中



## watch

**文章推荐：** https://zhuanlan.zhihu.com/p/465651353



**Vue2写法**

```javascript
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



**Vue3写法**

```javascript
export default {
  setup() {
    const str = ref("")

    watch(str, (newData, oldData) => {
      console.log("新值：", newData);
      console.log("旧值：", oldData);
    })

    return { str }
  }
}
```



监听基本响应式数据时，参数一需要加 `value`   ,    监听复杂响应式数据时，参数一不需要加 `value`

监听响应式对象中的单个属性数据，如果使用的 `ref` 则响应式对象需要加上 `value` ，使用 `reactive` 不需要加 `value`

```vue
<script setup lang="ts">
import { watch, ref } from 'vue';

const info = ref<string>("Hello")

const update = () => {
    info.value = "Hello Vue3"
}

// 如果需要监听的值为基本数据类型，则需要通过箭头函数返回并且加上.value
// watch(()=>info.value, data => {

// 如果需要监听的值为引用类型，则不需要
watch(info, data => {
    console.log(data);
})
</script>

<template>
    <div>{{ info }}</div>
    <button @click="update">更新数据</button>
</template>

<style scoped lang="scss"></style>

```

**注意：**

1. 想要监听响应式对象中的某个基本类型的属性必须这样写：`() => obj.hobby` **而** 引用类型可以直接写 `对象.属性名`

2. 重新赋值不会触发 `watch`，只有值被修改时才会触发



**deep / immediate** 

```javascript
    watch(obj.hobby, (newData, oldData) => {
      console.log("新值：", newData);
      console.log("旧值：", oldData);
    }, { deep: true, immediate: true })
```



**组合监听**

```vue
<template>
  <div>
    <input type="text" v-model="msg.str1">
    <input type="text" v-model="msg.str2">
  </div>
</template>

<script>
import { ref, watch } from 'vue'
export default {
  setup() {
    const msg = ref({
      str1: "",
      str2: ""
    })

    watch([() => msg.value.str1, () => msg.value.str2], (newStr, oldStr) => {
      // console.log("str1：", [newStr, oldStr]);
      // console.log("str2：", [newStr, oldStr]);

      console.log(newStr, oldStr);
      // 新值：newStr[0]
      // 旧值：oldStr[1]
    })

    return { msg }
  }
}
</script>
```



## watchEffect

`watchEffect` 监听所有的数据，并且只关心数据的最新值，不关心旧值是什么。默认会执行一次

```vue
<template>
  <div>
    <input type="text" v-model="msg.str">
  </div>
</template>

<script>
import { watchEffect, ref, watch } from 'vue'
export default {
  setup() {
    const msg = ref({
      str: "",
    })

    watchEffect(() => {
      console.log(msg.value.str);
    })

    return { msg }
  }
}
</script>
```



`watchEffect` 只能监听基本数据类型，无法监听引用类型

```javascript
    const str = ref("")

    watchEffect(() => {
      // 只能监听基本数据类型
      console.log(str.value);
      
      // console.log(obj.str.value);
    })
```



**停止监听**

```javascript
    const stop = watchEffect(() => {
      console.log(msg.value.str);
    })

    // 调用一下stop即可停止watchEffect监听
    stop()
```



## 组件通讯

### 父向子通讯

`App.vue`

```vue
<template>
  <HelloWorld msg="Hello World" />
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
export default {
  components: { HelloWorld },
}
</script>
```



`components/HelloWorld.vue`

```vue
<template>
  <div>{{ msg }}</div>
</template>

<script>
export default {
  // 不加props也可以，不过在js中获取不到子组件传递的数据，但页面中可以正常使用
  props: ['msg'],

  setup(props, emit) {
    console.log(props); // Proxy {msg: 'Hello World'}
    return { msg: props.msg }
  }

}
</script>
```



值的更新

`App.vue`

```vue
<template>
  <Hello :n="n" />
  <button @click="btn">按钮</button>
</template>

<script>
import { ref } from 'vue'
import Hello from './components/HelloWorld.vue'
export default {
  components: { Hello },

  setup() {
    let n = ref(0)

    const btn = () => {
      n.value++
    }

    return { n, btn }
  }
}
</script>
```



`components/HelloWorld.vue`

```vue
<template>
  <div>{{ n }}</div>
</template>

<script>
import { watch } from 'vue'
export default {
  props: ["n"],
    
  // 当父组件更新 props 时 setup 函数是不会重新执行的
  // 所以在 setup 函数中使用 props 时需要用到 computed 或者 watch 来响应 props 的变化
  // 注意: 直接在模板中使用 props 数据是没有这个问题的 可以直接在模板中使用 n 的值并且是响应式的

  setup(props, { emit }) {
    watch(() => props.n, (newData) => {
      console.log(newData);
    })
  }
}
</script>
```



### 子向父通讯

`App.vue`

```vue
<template>
  <Hello @abc="func" />
</template>

<script>
import Hello from './components/HelloWorld.vue'
export default {
  components: { Hello },

  setup() {
    const func = (data) => {
      console.log("触发了父组件的func方法");
      console.log("子组件传递的数据：", data);
    }

    return { func }
  }
}
</script>

<style></style>
```



`components/HelloWorld.vue`

```vue
<template>
  <div @click="btn">Hello World</div>
</template>

<script>
export default {
  setup(props, context) {
    const btn = () => {
      context.emit("abc", "Hello World")
    }

    return { btn }
  }
}
</script>

<style></style>
```



### 代码片段

在 `Vue2` 模板中不支持写多个标签，所有标签需要写在根标签中，在 `Vue3` 中支持多跟标签写法

```vue
<template>
  <div>{{ childMsg }}</div>
  <button @click="onClickHandler">change msg</button>
</template>
```



如果在组件模板中, 自定义事件需要在 `emits` 选项中声明.

```javascript
emits: ["onMsgChanged"] // 事件名：onMsgChanged
```



### 生命周期

`App.vue`

```vue
<template>
  <Hello v-if="show" />
  <button @click="btn">按钮</button>
</template>

<script>
import { ref } from 'vue'
import Hello from './components/HelloWorld.vue'
export default {
  components: { Hello },

  setup() {
    const show = ref(true)

    const btn = () => {
      show.value = !show.value
    }

    return { show, btn }
  }
}
</script>
```



`components/HelloWorld.vue`

```vue
<template>
  <div>Hello World</div>
</template>

<script>
import { onMounted, onUnmounted, onUpdated } from 'vue';
export default {
  setup(props, context) {
    console.log("组件中的setup执行");

    // 组件DOM渲染完毕后执行
    onMounted(() => {
      console.log("组件中的onMounted执行");
    })

    // 组件数据更新后执行
    onUpdated(() => {
      console.log("组件中的onUpdated执行");
    })

    // 组件卸载后执行
    onUnmounted(() => {
      console.log("组件中的onUnmounted执行");
    })
  }
}
</script>
```



`components/HelloWorld.vue`

```vue
<template>
  <div>Hello World</div>
</template>

<script>
import { onMounted, onUnmounted, onUpdated } from 'vue';
export default {
  setup(props, context) {
    console.log("组件中的setup执行");

    let time = null

    // 组件DOM渲染完毕后执行
    onMounted(() => {
      console.log("定时器开始");

      let i = 0

      time = setInterval(() => {
        console.log(++i);
      }, 1000)
    })

    // 组件数据更新后执行
    onUpdated(() => {
      console.log("组件中的onUpdated执行");
    })

    // 组件卸载后执行
    onUnmounted(() => {
      // 组件卸载后一般做一些清除相关的代码，例如清除定时器
      clearInterval(time)

      console.log("定时器已卸载");
    })
  }
}
</script>
```



## 与服务端通信

```vue
<template>
  <div v-if="data.loading">loading...</div>
  <div v-else-if="data.error">{{ data.error }}</div>
  <div v-else-if="data.list && data.list.length > 0">
    <ul>
      <li v-for="item in data.list" :key="item.id">{{ item.title }}</li>
    </ul>
  </div>
  <div v-else>暂无数据</div>
</template>

<script>
import axios from 'axios'
import { reactive } from 'vue'
export default {
  setup() {
    const data = reactive({
      loading: false,
      list: [],
      error: ""
    })

    const getPosts = async () => {
      try {
        data.loading = true

        const res = await axios.get("https://jsonplaceholder.typicode.com/posts1")
        data.list = res.data

        data.loading = false
      } catch (error) {
        data.loading = false
        data.error = error.message
      }
    }

    getPosts()

    return { data }
  }
}
</script>
```



## 抽离组合式API

将获取 `Posts` 数据的逻辑抽取单独文件中，使其可以在多个组件中被重复使用。并且提高程序的可读性



`src/App.vue`

```vue
<template>
  <div v-if="data.loading">loading...</div>
  <div v-else-if="data.error">{{ data.error }}</div>
  <div v-else-if="data.list && data.list.length > 0">
    <ul>
      <li v-for="item in data.list" :key="item.id">{{ item.title }}</li>
    </ul>
  </div>
  <div v-else>暂无数据</div>
</template>

<script>
import { userPosts } from './hooks/getPost'
export default {
  setup() {
    const data = userPosts()
    
    return { data }
  }
}
</script>
```



`src/hooks/getPost.js`

```javascript
import axios from "axios";
import { reactive } from "vue";

export function userPosts() {
  const data = reactive({
    loading: false,
    list: [],
    error: "",
  });

  const getPosts = async () => {
    try {
      data.loading = true;

      const res = await axios.get(
        "https://jsonplaceholder.typicode.com/posts"
      );
      data.list = res.data;

      data.loading = false;
    } catch (error) {
      data.loading = false;
      data.error = error.message;
    }
  };

  getPosts();

  return data
}
```



**抽离组合式API子向父传值**

`src/App.vue`

```vue
<template>
  <Hello @abc="fn" />
</template>

<script>
import Home from './hooks/Home'
import Hello from './components/HelloWorld.vue'
export default {
  components: { Hello },
  setup() {
    // const fn = () => {
    //   console.log("Hello World!");
    // }

    const fn = Home().fn

    return { fn }
  }
}
</script>
```



`src/hooks/Home.js`

```javascript
export default function Home() {
  const fn = () => {
    console.log("Hello World!");
  };

  return { fn };
}
```



`components/HelloWorld.vue`

```vue
<template>
  <button @click="btn">按钮</button>
</template>

<script>
export default {
  setup(props, { emit }) {
    const btn = () => {
      emit('abc', "Hello World")
    }

    return { btn }
  }
}
</script>
```



## 获取DOM元素

**获取DOM元素**

```vue
<template>
  <div ref="divRef">Hello World!</div>
</template>

<script>
import { ref, onMounted } from 'vue'
export default {
  setup() {
    const divRef = ref(null)

    onMounted(() => {
      console.log(divRef.value);
      // divRef.value.style.color = "red"
      // <div>Hello World!</div>
    })

    return { divRef }
  }
}
</script>
```



**获取组件的实例**

```vue
<template>
  <Hello ref="H" />
</template>

<script>
import Hello from './components/HelloWorld.vue'
import { ref, onMounted } from 'vue'
export default {
  components: { Hello },
  setup() {
    const H = ref(null)

    onMounted(() => {
      console.log(H);
      console.log(H.value); //获取Hello组件的实例，可以使用组件的属性和方法
    })

    return { H }
  }
}
</script>
```



**批量获取真实DOM**

```vue
<template>
  <ul>
    <!-- 真实DOM：el -->
    <!-- 给每一项赋值真实DOM：elms[index] = el -->
    <li v-for="(item, index) in list" :key="index" :ref="(el) => (elms[index] = el)">
      {{ item }}
    </li>
  </ul>
</template>

<script>
import { ref, onMounted } from "vue"
export default {
  setup() {
    const list = ref(["a", "b", "c"])
    const elms = ref([])

    onMounted(() => console.log("真实dom列表：", elms.value))

    return { list, elms }
  },
}
</script>
```



## provide、inject

通过 `provide`、`inject` 函数的配合使用，可以实现跨组件传递数据

**注意：** 该方式传递数据只能父传子，不支持子传父



**基本使用**

`src/App.vue`

```vue
<template>
  <A />
</template>

<script>
import { provide, ref } from 'vue'
import A from './components/A.vue'
export default {
  components: { A },

  setup() {
    const msg = ref("Hello World")

    provide("data", msg)
  }
}
</script>
```



`components/A.vue`

```vue
<template>
    <B />
</template>

<script>
import B from './B.vue'
export default {
    components: { B }
}
</script>
```



`components/B.vue`

```vue
<template>
    <div>{{ data }}</div>
</template>

<script>
import { inject } from 'vue'
export default {
    setup() {
        const data = inject("data")

        return { data }
    }
}
</script>
```



**进阶用法**

`src/App.vue`

```vue
<template>
  <A />
</template>

<script>
import { provide, reactive } from 'vue'
import A from './components/A.vue'
export default {
  components: { A },

  setup() {
    const data = reactive({
      name: "刘宇阳",
      hobby: "敲代码"
    })

    const update = (val) => {
      // data.hobby = "写代码"
      data.hobby = val
    }

    provide("data", data)
    provide("update", update)
  }
}
</script>
```



`components/A.vue`

```vue
<template>
    <B />
</template>

<script>
import B from './B.vue'
export default {
    components: { B }
}
</script>
```



`components/B.vue`

```vue
<template>
    <div>{{ data }}</div>
    <button @click="updateComponents('写代码')">按钮</button>
</template>

<script>
import { inject } from 'vue'
export default {
    setup() {
        const data = inject("data")
        const updateComponents = inject("update")

        return { data, updateComponents }
    }
}
</script>
```



## Teleport

`teleport` 组件可以将指定的组件渲染到项目外部的其他位置。

比如弹框、返回顶部组件，它可能在任意组件中使用，我们可以把他渲染到指定的位置，这样就不用来回引入了



`src/App.vue`

```vue
<template>
    <!-- to是标签的ID或类名 -->
    <teleport to='#modal'>
        <Modal />
    </teleport>
</template>

<script>
import Modal from './components/Modal.vue'
export default {
    components: { Modal }
}
</script>
```



`components/Modal.vue`

```vue
<!-- Modal.vue -->
<template>
  <div class="wrapper">
    <div class="content">
      <a class="close" href="javascript:">关闭</a>
    </div>
  </div>
</template>
<script>
export default {
  name: "Modal",
};
</script>
<style scoped>
.wrapper {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
}
.content {
  width: 660px;
  height: 400px;
  background: white;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.close {
  position: absolute;
  right: 10px;
  top: 10px;
  color: #999;
  text-decoration: none;
}
</style>
```



`index.html`

```html
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <link rel="icon" type="image/svg+xml" href="/vite.svg" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Vite + Vue</title>
</head>

<body>
  <div id="app"></div>

  <!-- 写在这里，所有组件都可以看到了 -->
  <div id="modal"></div>

  <script type="module" src="/src/main.js"></script>
</body>

</html>
```



## Suspense

通过 `Suspense` 组件可以为异步操作添加等待提示效果

`src/App.vue`

```vue
<template>
    <Suspense>
        <template v-slot:default>
            <Posts />
        </template>

        <template v-slot:fallback> 加载中，请稍后... </template>
    </Suspense>
</template>

<script>
import Posts from './components/Posts.vue'
import { ref } from 'vue'
export default {
    components: { Posts },
    setup() {
        let show = ref(true)
        return { show }
    }
}
</script>
```

从上面的代码片段中，使用组件 `suspense`  可以很简单就实现了 `loading` 的效果

带有` #default`  为初始化模板组件，用于显示异步请求完成之后的 **UI** 

带有 `#fallback` 为异步请求中的处理 **UI** ，即常见的 `loading` 效果。



`components/Posts.vue`

```vue
<template>
    <pre>{{ data }}</pre>
</template>
<script>
import axios from "axios";

export default {
    name: "Posts",
    async setup() {
        let response = await axios.get(
            "https://jsonplaceholder.typicode.com/posts"
        );
        return { data: response.data };
    },
};
</script>
```



## Transition

`Vue` 提供了 `transition` 内置组件供我们执行过渡动画,，我们只需要使用 `transition` 组件包裹你要执行动画的元素即可。



 `transition` 的触发方式如下：

- 由 `v-if` 所触发的切换
- 由 `v-show` 所触发的切换
- 由特殊元素 `<component>` 切换的动态组件
- 改变特殊的 `key` 属性



执行过渡动画的前提条件是元素具有创建与销毁的操作。

```vue
<template>
    <Transition>
        <h1 v-if="show">hello world</h1>
    </Transition>

    <button @click="show = !show">显示隐藏</button>
</template>

<script>
import { ref } from 'vue'
export default {
    setup() {
        let show = ref(true)
        return { show }
    }
}
</script>

<style>
/* 从无到有 */
/* 开始显示 */
.v-enter-from {
    opacity: 0
}

/* 结束显示 */
.v-enter-to {
    opacity: 1
}

/* 显示动画 */
.v-enter-active {
    transition: opacity 0.5s ease-in
}


/* 从有到无 */
/* 开始隐藏 */
.v-leave-from {
    opacity: 1
}

/* 结束隐藏 */
.v-leave-to {
    opacity: 0
}

/* 隐藏动画 */
.v-leave-active {
    transition: opacity 0.5s ease-out
}
</style>
```



**为过渡效果命名**

我们可以给 `<Transition>` 组件传一个 `name` prop 来声明一个过渡效果名：

```vue
<Transition name="fade">
  ...
</Transition>
```



对于一个有名字的过渡效果，对它起作用的过渡 `class` 会以其名字而不是 `v` 作为前缀。比如，上方例子中被应用的 `class` 将会是 `fade-enter-active` 而不是 `v-enter-active`。这个 `“fade”` 过渡的 `class` 应该是这样：

```css
<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
```



## 数据双向绑定

### 表单双向数据绑定

```vue
<template>
    <div>
        <input type="text" v-model="msg">
        <button @click="change">Button</button>
    </div>
</template>

<script setup>
import { ref } from "vue"

const msg = ref("")
const change = () => {
    msg.value += "!"
}
</script>
```



### 组件双向数据绑定

#### 普通版

```vue
<template>
    <div @click="update">父组件：{{ msg }}</div>
    <Hello :msg="msg" @change="change" />
    <button @click="update">Button</button>
</template>

<script setup>
import Hello from './components/Hello.vue'
import { ref } from "vue"

const msg = ref("Hello World!")

const change = () => {
    msg.value = "子组件触发了!"
}

const update = () => {
    msg.value = "父组件触发了!"
}
</script>

<style></style>
```

```vue
<template>
  <div @click="update">子组件：{{ msg }}</div>
</template>


<script setup>
import { defineProps } from 'vue'
import { defineEmits } from 'vue'

// 使用父组件传递的数据
const props = defineProps(["msg"])

// 使用emit
const emit = defineEmits(['change'])

const update = () => {
  emit("change")
}
</script>

<style></style>
```



#### 升级版

父组件向子组件通过 `v-model` 传递的数据

```vue
<template>
    <div @click="update">父组件：{{ msg }}</div>
	 <!-- v-model 没有指定参数名时，子组件默认参数名是 modelValue -->
    <Hello v-model="msg" />
</template>

<script setup>
import Hello from './components/Hello.vue'
import { ref } from "vue"

const msg = ref("Hello World!")

const update = () => {
    msg.value = "父组件触发了!"
}
</script>

<style></style>
```



接收父组件 `v-model` 传递的数据 

如果父组件的 `v-model` 没有指定参数名时，子组件默认参数名是 `modelValue`

```vue
<template>
  <div @click="update">子组件：{{ modelValue }}</div>
</template>

<script setup>
import { defineProps } from 'vue'
import { defineEmits } from 'vue'

// 使用父组件传递的数据
const props = defineProps(["modelValue"])

// 使用emit
const emit = defineEmits(['change'])

// 通过update修改数据
const update = () => {
  emit("update:modelValue", "子组件触发了!")
}
</script>

<style></style>
```



#### 终极版

父组件通过 `v-model` 传递多个数据

```vue
<template>
    <div @click="update">父组件：{{ msg1 }} {{ msg2 }}</div>
    <Hello v-model:msg1="msg1" v-model:msg2="msg2" />
</template>

<script setup>
import Hello from './components/Hello.vue'
import { ref } from "vue"

const msg1 = ref("Hello")
const msg2 = ref("World!")

const update = () => {
    msg1.value = "Hello"
    msg2.value = "World"
}
</script>

<style></style>
```



接收多个数据

```vue
<template>
  <div @click="update">子组件：{{ msg1 }} {{ msg2 }}</div>
</template>


<script setup>
import { defineProps } from 'vue'
import { defineEmits } from 'vue'

// 使用父组件传递的数据
const props = defineProps(["msg1", "msg2"])

// 使用emit
const emit = defineEmits(['change'])

// 通过update修改数据
const update = () => {
  emit("update:msg1", "World")
  emit("update:msg2", "Hello")
}
</script>

<style></style>
```



## ref

某些特殊情况下我们需要调用 `dom` 的 `api`，则需要使用 `ref` 方法来获取真实 `dom` 元素

```vue
<script setup lang='ts'>
const target = ref()

// 获取 & 操作DOM必须在DOM渲染完毕之后，所以必须写在onMounted中或使用nextTick
onMounted(() => {
  // 获取真实DOM
  console.log(target);

  // 操作真实DOM
  target.value.style.color = "red"
})
</script>

<template>
  <div ref="target">Hello</div>
</template>
```



## nextTick

等 `DOM` 渲染完毕后，在下一次更新循环结束时执行 `nextTick` 中的回调函数

```vue
<script setup lang='ts'>
const target = ref("")

// 此时DOM还没有开始渲染，所以获取不到元素
console.log(target.value);

// 等DOM渲染完毕后，在下一次更新循环结束时执行nextTick中的回调函数
nextTick(() => {
  console.log(target.value);
  // <div>Hello World!</div>
})
</script>

<template>
  <div ref="target">Hello World!</div>
</template>
```



**应用场景**

1. 如果想要在修改数据后立刻得到更新后的 `DOM` 结构，可以使用 `Vue.nextTick()`

2. 在 `created` 生命周期中进行 `DOM` 操作



**示例**

```vue
<template>
  <div>
    <h1 v-if="show" ref="target">这是标题</h1>
    <button @click="update">点击按钮</button>
  </div>
</template>

<script setup lang="ts">
const show = ref(false)
const target = ref()

function update() {
  show.value = true

  // 此时show的值其实还没有改成true，相当于v-if="false" 所以页面上还没有h1这个标签
  console.log(target.value); // undefined

  // // 使用nextTick等Dom渲染完毕后 在下一次更新循环结束时执行里面的回调函数
  nextTick(() => {
    console.log('DOM更新完成')

    // 此时DOM已经渲染完毕，所以可以获取DOM了
    console.log(target.value); // <h1 style="color: red;">这是标题</h1>

    // 也可以直接操作DOM
    target.value.style.color = "red"
  })
}
</script>
```



**总结**

在 `$nextTick` 当中的代码不会立即执行，而是等 `DOM` 更新完成之后再执行，这样我们拿到的一定是最新的数据



## customRef

**什么是防抖？**防抖就好比如王者荣耀中的回城,被打断就要重新来



**使用传统方式实现防抖**

```vue
<template>
    <div>{{ content }}</div>
    <input type="text" v-model="text">
</template>

<script setup>
import { watch, ref } from "vue";

const content = ref("")
const text = ref("")
let time = null

// 核心代码：每次输入先清除之前的定时器，等输入完毕后执行定时器中的代码实现防抖功能
watch(() => text.value, (data) => {
    clearTimeout(time)

    time = setTimeout(() => {
        content.value = data
    }, 500)
})
</script>
```



**customRef基本语法**

```vue
<template>
  <div>{{ msg }}</div>
  <input type="text" v-model="msg">
</template>

<script setup lang="ts">
import { customRef, ref } from "vue";

let initValue = ""
let msg = customRef((track, trigger) => {
  return {
    // 默认get方法会执行一次，以后只要在set中调用了trigger方法就会触发get方法
    get() {
      // 追踪数据
      track()

      // 返回最新数据：initValue
      return initValue
    },
    set(value) {
      // 最新值：value

      // 拿到最新的值赋值给：initValue
      initValue = value

      // 视图更新
      trigger()
    }
  }
})
</script>

<style></style>
```

**注意：** 默认 `get` 方法会执行一次，以后只要在 `set` 中调用了 `trigger` 方法就会自动触发 `get` 方法



使用 `customRef` 实现防抖

```vue
<script setup lang="ts">
import { customRef, ref } from "vue";

let initValue = ""
let msg = customRef((track, trigger) => {
  let time: any = null

  return {
    // 默认get方法会执行一次，以后只要在set中调用了trigger方法就会触发get方法
    get() {
      // 追踪数据
      track()

      // 返回最新数据：initValue
      return initValue
    },
    set(value) {
      // 最新值：value

      // 每次值发生改变，先清除之前的定时器
      clearTimeout(time)

      // 执行现在的定时器
      time = setTimeout(() => {
        // 拿到最新的值赋值给：initValue
        initValue = value

        // 视图更新
        trigger()
      }, 500)
    }
  }
})
</script>
```



使用 `customRef` 封装一个防抖的方法

```vue
<template>
  <div>{{ msg }}</div>
  <input type="text" v-model="msg">
</template>

<script setup lang="ts">
import { customRef, ref } from "vue";

// 使用 customRef 封装一个防抖的方法
const custom = (initValue: string, delay: number) => {
  return customRef((track, trigger) => {
    let time: any = null

    return {
      // 默认get方法会执行一次，以后只要在set中调用了trigger方法就会触发get方法
      get() {
        // 追踪数据
        track()

        // 返回最新数据：initValue
        return initValue
      },
      set(value) {
        // 最新值：value

        // 每次值发生改变，先清除之前的定时器
        clearTimeout(time)

        // 执行现在的定时器
        time = setTimeout(() => {
          // 拿到最新的值赋值给：initValue
          initValue = value

          // 视图更新
          trigger()
        }, 500)
      }
    }
  })
}

let msg = custom("", 500)
</script>
```



## 代理对象

数据驱动视图, 即数据和视图进行绑定, 当数据发生变化后, 视图自动更新.

实现数据响应式的核心在于监听数据的变化, 当数据发生变化后, 执行视图更新操作.

Vue3 使用代理对象监听数据变化.

创建对象的代理对象, 从而实现对对象操作的拦截和自定义.



### Proxy

**基本使用**

```javascript
const person = { name: "zs", age: 20 }

const p = new Proxy(person, {
    get(target, pro) {
        console.log("GET");
        return target[pro]
    },
    set(target, pro, val) {
        console.log("SET");
        target[pro] = val
        return true
    },
    deleteProperty(target, pro) {
        console.log("DEL");
        delete target[pro]
        return true
    }
})
```



**用法**

```javascript
const person = { name: "zs", hobby: "敲代码", obj: { age: 20, sex: "男" } }

const p = new Proxy(person, {
    // 当被读取属性时会触发
    get(target, pro) {
        // target：{name: '宇阳', hobby: '敲代码'}
        // pro：name
        console.log("拦截到了读取操作");
        return target[pro]
    },
    // 当设置或新增属性时触发
    set(target, pro, value) {
        console.log("拦截到了设置/新增操作");
        target[pro] = value
        return true
    },
    // 拦截删除操作
    deleteProperty(target, pro) {
        console.log("拦截到了删除操作");
        return true
        // return delete target[pro]
    }
})

p.obj.age = 23 // 拦截到了设置/新增操作
console.log(p.obj.age); // 拦截到了读取操作

delete p.obj.sex // 拦截到了删除操作
console.log(p.obj); // {age: 23}
```

被代理的对象 `person` 也会被改变



### Object.defineProperty

```javascript
const person = { name: "zs", age: 20 }

let value = ''
// 拦截person中的name属性
Object.defineProperty(person, 'name', {
    get() {
        console.log('GET');
        return value
    },
    set(val) {
        console.log('SET');
        value = val
    },
})

person.name = 'ls'
console.log(person);
```



```javascript
const person = { name: "zs", age: 20 }

let value = ''
// 拦截person中的name属性
Object.defineProperty(person, 'name', {
    get() {
        console.log('GET');
        return value
    },
    set(val) {
        console.log('SET');
        value = val
    },
})

// 拦截person中的age属性
Object.defineProperty(person, 'age', {
    get() {
        console.log('GET');
        return value
    },
    set(val) {
        console.log('SET');
        value = val
    },
})
```

可以看出 `Vue2` 响应式原理需要把每个属性都要写一遍`Object.defineProperty`，十分麻烦，而 `Vue3` 只需要给一个对象设置 `Proxy` 即可



## setup语法糖

`setup` 是 `Vue3` 的一个语法糖，它可以简化语法，使代码更加简单



### 使用setup

使用它的方法很简单，只需要在 `script` 标签中加上 `setup` 关键字即可

```vue
<script setup></script>
```

话不多说，看以下示例你会爱上 `setup`



### 组件注册

**使用前：** 需要手动注册组件

```vue
<template>
    <Hello />
</template>

<script>
import Hello from './components/Hello.vue'
export default {
    components: { Hello }
}
```



**使用后：** 自动注册组件

```vue
<template>
    <Hello />
</template>

<script setup>
import Hello from './components/Hello.vue'
</script>
```



### 组件通讯

**使用前**

```vue
<script>
export default {
  props: ["msg"],

  setup(props, { emit }) {
    const update = () => {
      emit("change")
    }

    return { update }
  }
}
</script>
```



**使用后**

```vue
<script setup>
import { defineProps } from 'vue'
import { defineEmits } from 'vue'

// 约束对父组件传递的数据类型
// const props = defineProps({
//   msg: String,
// })

// 使用父组件传递的数据
const props = defineProps(["msg"])

// 核心代码：在defineEmits里面写父组件的方法
const emit = defineEmits(['change'])

const update = () => {
  // 然后通过emit进行使用
  emit("change")
  
  // 传参 emit("change","这里写参数", "可以写多个参数哦")
}
</script>
```



## onBeforeRouteUpdate

`onBeforeRouteUpdate` 是 `Vue3` 中的路由守卫之一，用于在路由更新前执行一些操作。它的作用是在当前组件复用时调用，即当路由参数发生变化时，同一个组件会被复用，此时 `onBeforeRouteUpdate` 会被调用，可以在这个钩子函数中执行一些操作，比如更新组件内的数据。

`onBeforeRouteUpdate` 接收三个参数，分别是 `to`、`from `和 `next`。其中 `to` 表示即将要进入的路由对象，`from` 表示即将离开的路由对象，`next` 是一个函数，用于执行跳转或取消跳转。



## render

渲染函数允许开发者通过 `JavaScript` 的方式创建组件模板。

```typescript
// 选项式 API - JavaScript 
export default {
  // render 函数返回什么, 页面中就显示什么
  render () {}
}
```

```typescript
// 选项式 API - TypeScript
defineComponent({
  // render 函数返回什么, 页面中就显示什么
  render() {},
});
```

```vue
<!-- 组合式 API 语法糖 -->
<script lang="ts" setup>
// render 函数返回什么, 页面中就显示什么
const render = () => {};
</script>
<template>
	<!-- 以组件的方式调用 render 方法 -->
	<render />
</template>
```



**示例**

```vue
<script setup lang='ts'>
import { h } from 'vue'

// 写一个渲染函数
const render = () => {
  // 渲染一个h1标签
  return h('h1')
}
</script>

<template>
  <!-- 通过组件的方式调用render函数 -->
  <render />
</template>

<style scoped>
.col {
  color: red;
}
</style>
```



给一个标签赋值

```javascript
const render = () => {
  return h('h1', "Hello World")
}
```



给一个标签赋值并绑定样式

```javascript
const render = () => {
  return h('h1', { class: "col" }, "Hello World")
}
```



给一个标签赋值并绑定样式与事件

```javascript
const render = () => {
  return h('h1', { class: "col", onclick: () => alert(123) }, "Hello World")
}
```



在 `h1` 标签中嵌套一个 `span` 标签

```javascript
const render = () => {
  return h('h1', { class: "col" }, h("span", "嵌套一个span标签"))
}
```



## useSlots

**Vue 中的 useSlots 的作用**

`useSlots` 是 `Vue` 的一个内部函数，它用于在函数式组件中访问插槽。它接受一个参数 `context`，该参数包含组件的 `props、slots、parent、children` 等信息。`useSlots` 函数返回的是一个 `Proxy` 对象，里面包含着父组件插槽中的所有虚拟 `DOM` 对象

在实际开发中，`useSlots` 函数通常用于函数式组件中访问插槽内容。由于函数式组件没有实例，无法直接访问`this.$slots` 属性，因此需要使用 `useSlots` 函数来获取插槽内容。



**通过 useSlots 获取的插槽内容能做什么**

通过 `useSlots` 获取的插槽内容可以用于在子组件内部访问父组件的内容。这对于在父组件中定义一些通用的模板或布局，并在子组件中根据需要使用它们非常有用。通过 `useSlots`，子组件可以访问父组件中定义的插槽内容，并将其渲染到自己的模板中。这样，子组件就可以利用父组件中的模板和布局，同时保持自己的独立性和可重用性。

**如下：** 子组件通过调用 `default()` 获取父组件插槽中的内容

```vue
  <XtxBrand>
    <div>100</div>
    <span>200</span>
  </XtxBrand>
```

```javascript
import { h, useSlots } from 'vue'

// 返回的是一个Proxy对象，里面包含着父组件插槽中的所有虚拟DOM对象
let defaults = useSlots().default?.()
console.log(defaults, 666);

const render = () => {
    return h("div", { class: "xtx-bread" }, [defaults])
}
```



## 配置组件自动导入

**目标**

配置组件不需要手动引入，直接使用

```vue
<script setup lang='ts'>
// 不需要引入组件，可以直接使用
// import XtxTopNav from './XtxTopNav.vue';
// import XtxHeader from './XtxHeader.vue';
// import XtxFooter from './XtxFooter.vue';
</script>

<template>
    <!-- 不需要引入组件，可以直接使用 -->
    <XtxTopNav />
    <XtxHeader />
    <RouterView />
    <XtxFooter />
</template>
```



**安装**

```bash
npm install unplugin-vue-components@0.22.7 -D
```



**示例**

`vite.config.ts`

```javascript
import vueJsx from '@vitejs/plugin-vue-jsx'
import Components from 'unplugin-vue-components/vite'

export default defineConfig({
  plugins: [
    vue(), vueJsx(),
    // 配置组件自动导入
    Components({
      // 使这两个目录下的文件不需要引入组件就能直接使用
      dirs: ["src/components", "src/views"],
      // 是否包括所有子目录都可以直接使用
      deep: true,
      // 组件的有效文件扩展名
      extensions: ["vue"],
      // 配置自动导入组件的文件生成的位置
      dts: "components.d.ts"
    })
  ],
})
```

`tsconfig.json`

```json
{
  // 规定只有src目录包含模块才会进行编译
  "include": ["components.d.ts"],
}
```



## 配置库自动导入

**目标**

配置库不导入，直接使用

```javascript
// 不需要导入 直接使用
// import { defineStore } from 'pinia'

// 不需要导入 直接使用
export const useCounterStore = defineStore('counter', () => {
  return {}
})
```



**安装**

```bash
npm i -D unplugin-auto-import@0.11.2
npm i @vueuse/core@9.2.0
```



**示例**

`vite.config.ts`

```javascript
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

// 核心代码 - 导入 unplugin-auto-import/vite
import AutoImport from "unplugin-auto-import/vite"

import { join } from 'path'

export default defineConfig({
  plugins: [
    vue(), vueJsx(),
    // 配置指定自动导入的库
    AutoImport({
      // 这些库在使用时候不需要再导入了，可以直接使用
      imports: ["vue", "vue-router", "pinia", "@vueuse/core"],
      eslintrc: {
        enabled: true, // Default `false`
        filepath: "./.eslintrc-auto-import.json",
        globalsPropValue: true,
      },
      // 配置文件
      dts: "auto-import.d.ts"
    })
  ],
})
```



解决在不引入 `hooks` 就使用 `hooks` 时 `TS` 编译器报错的问题

```json
// tsconfig.json
{
  "include": ["auto-import.d.ts"],
}
```

解决在不引入 `hooks` 就使用 `hooks` 时 `ESLint` 报错问题

```typescript
// .eslintrc.cjs
module.exports = {
  'extends': [
    "./.eslintrc-auto-import.json"
  ],
}
```



# 技术文章推荐

**reactive：**https://www.cnblogs.com/IwishIcould/p/15096750.html

**watch：** https://zhuanlan.zhihu.com/p/465651353
