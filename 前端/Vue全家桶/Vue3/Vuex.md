# Vuex

Vuex 目前有两个版本，一个是 `3.6.2` 另一个是 `4.0.2` 

前者 `3x` 版本是用于 `Vue2`  **而** 后者 `4x` 版本是用于 `Vue3`使用



## State

`src/App.vue`

```vue
<template>
    <h1>{{ $store.state.msg }}</h1>
</template>
```



`src/store/index.js`

```javascript
// 导入Vuex
import { createStore } from "vuex";

// 导出Vuex
export default createStore({
  state: {
    msg: "Hello World!",
  },
});
```



`src/main.js`

```javascript
import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";

// 导入store/index.js
import store from "./store";

// 挂载
createApp(App).use(store).mount("#app");
```



**简化写法**

将 `$store.state.msg` 转换为 `msg`

```vue
<template>
    <!-- 方法一 -->
    <h1>{{ $store.state.msg }}</h1>

    <!-- 方法二 -->
    <h1>简化写法：{{ msg }}</h1>
</template>

<script>
// import store from './store/index.js'
import store from './store'
export default {
    setup() {
        return {
            msg: store.state.msg
        }
    }
}
</script>
```



## Getters

`getters` 是 `Vuex` 中的计算属性, 基于现有状态计算出新的状态。

只要依赖的值发生变化就会自动执行

```vue
<template>
    <!-- 方法一 -->
    <h1>{{ $store.getters.newMsg }}</h1>

    <!-- 方法二 -->
    <h1>{{ msg }}</h1>
</template>

<script>
import store from './store'
export default {
    setup() {
        return {
            msg: store.getters.newMsg
        }
    }
}
</script>
```

```javascript
import { createStore } from "vuex";

export default createStore({
  state: {
    msg: "Hello ",
  },
  getters: {
    newMsg(state) {
      // getters 必须要有返回值
      return state.msg + "World!";
    },
  },
});
```



## Mutations

**mutations** 是 `Vuex` 中用于修改状态的方法。

```vue
<template>
    <h1>{{ $store.state.msg }}</h1>

    <!-- 方法一 -->
    <button @click="$store.commit('updMsg', '!')"> 按钮 </button>

    <!-- 方法二 -->
    <button @click="updMsg">按钮</button>
</template>

<script>
import store from './store'
export default {
    setup() {
        const updMsg = () => {
            store.commit('updMsg', '!')
        }

        return {
            updMsg
        }
    }
}
</script>
```

```javascript
import { createStore } from "vuex";

export default createStore({
  state: {
    msg: "Hello World",
  },
  mutations: {
    updMsg(state, val) {
      state.msg += val;
    },
  },
});
```



**求奇偶数** 每次点击递增值，求出偶数还是奇数

```vue
<template>
    <div>当前值：{{ $store.state.num }}</div>
    <div>奇偶数：{{ $store.getters.msg }}</div>
    <button @click="$store.commit(`updNum`)">按 钮</button>
</template>
```

```javascript
import { createStore } from "vuex";

export default createStore({
  state: {
    num: 1000,
  },
  // 计算属性
  getters: {
    msg(state) {
      // 当num属性发生变化就会自动触发
      return state.num % 2 === 0 ? "偶数" : "奇数";
    },
  },
  mutations: {
    updNum(state) {
      state.num += 1;
    },
  },
});
```



## Actions

**actions** 在 `Vuex` 中用于执行异步操作, 当异步操作执行完成以后可以调用 `commit` 方法触发 `mutation` 来修改应用状态

```vue
<template>
    <div>当前值：{{ $store.state.num }}</div>
    
    <!-- 方法一 -->
    <button @click="$store.dispatch('asyncUpdNum')">按 钮</button>

    <!-- 方法二 -->
    <button @click="asyncUpdNum">按 钮</button>
</template>

<script>
import store from './store'
export default {
    setup() {
        const asyncUpdNum = () => {
            store.dispatch("asyncUpdNum")
        }

        return { asyncUpdNum }
    }
}
</script>
```

```javascript
import { createStore } from "vuex";

export default createStore({
  state: {
    num: 1000,
  },
  mutations: {
    updNum(state) {
      state.num += 1;
    },
  },
  actions: {
    asyncUpdNum({ commit }) {
      setInterval(() => {
        commit("updNum");
      }, 1000);
    },
  },
});
```

一般 `actions` 用于处理异步操作，比如调用接口拿数据



## Module

Vuex 允许开发者通过模块对状态进行拆分，允许开发者将不同功能的状态代码拆分到不同的模块中。

模块分为两种，一种是不具备命名空间的模块，另一种是具备命名空间的模块，推荐使用 **命名空间**，命名空间使模块更加独立。

```vue
<template>
    <!-- 写法一 -->
    <span>{{ $store.state.moduleA.msg }}, </span>
    <span>{{ $store.state.moduleB.msg }}! </span>

    <!-- 写法二 -->
    <div>{{ A }}, {{ B }}!</div>
</template>

<script>
import { ref } from 'vue'
import store from './store'
export default {
    setup() {
        const A = ref(store.state.moduleA.msg)
        const B = ref(store.state.moduleB.msg)

        return { A, B }
    }
}
</script>
```

```javascript
import { createStore } from "vuex";

// 模块一、
const moduleA = {
  state() {
    return { msg: "Hello" };
  },
};

// 模块二
const moduleB = {
  // 推荐使用模块一的写法，通过函数将数据返回出来
  state: {
    msg: "World",
  },
};

export default createStore({
  modules: {
    moduleA,
    moduleB,
  },
});
```



### 不使用命名空间

如果不使用命名空间的话，当执行了 `mutations` 时，则同名的方法都会触发一次

```vue
<template>
    <div>~ {{ $store.state }} ~</div>
    <button @click="$store.commit(`updMsg`)">按 钮</button>
</template>

<script>
    export default {
        setup() {}
    }
</script>
```

```javascript
import { createStore } from "vuex";

// 模块一、
const moduleA = {
  state() {
    return { msg: "Hello" };
  },
  mutations: {
    updMsg(state) {
      state.msg += "!";
    },
  },
};

// 模块二
const moduleB = {
  state() {
    return { msg: "World" };
  },
  mutations: {
    updMsg(state) {
      state.msg += "~";
    },
  },
};

export default createStore({
  modules: {
    moduleA,
    moduleB,
  },
});
```



### 使用命名空间

使用命名空间模块需要在模块对象中添加 `namespaced: true` 选项。

这样 `Getters` 或 `Mutations` 中的属性可以重复命名并且每个模块互不影响

使用 `$store.getters["模块名"].属性名` 、 `$store.commit("模块名/方法名")`

```vue
<template>
    <!-- <span>{{ $store.state.moduleA.msg }}, </span> -->

    <!-- 推荐写法 -->
    <div>{{ $store.state['moduleA'].msg }}! </div>

    <div>~ {{ $store.state }} ~</div>

    <!-- 调用指定模块中的 mutations -->
    <button @click="$store.commit('moduleA/updMsg')">按 钮</button>
</template>

<script>
export default {
    setup() { }
}
</script>
```

```javascript
import { createStore } from "vuex";

// 模块一、
const moduleA = {
  // 使用命名空间
  namespaced: true,
  state() {
    return { msg: "Hello" };
  },
  mutations: {
    updMsg(state) {
      state.msg += "!";
    },
  },
};

// 模块二
const moduleB = {
  // 使用命名空间
  namespaced: true,
  state() {
    return { msg: "World" };
  },
  mutations: {
    updMsg(state) {
      state.msg += "~";
    },
  },
};

export default createStore({
  modules: {
    moduleA,
    moduleB,
  },
});
```



使用 `Getters` 访问模块化中的属性

```vue
<template>
    <div>{{ msg }}</div>
    <div>{{ $store.getters['msg'] }}</div>
</template>

<script setup>
import store from './store';
const msg = store.state.moduleA['msg']
</script>
```

```javascript
import { createStore } from "vuex";

export default createStore({
  modules: {
    moduleA: {
      namespaced: true,
      state() {
        return {
          msg: "Hello World!",
        };
      },
    },
  },

  getters: {
    msg: state => state.moduleA.msg,
  },
});
```



## 综合案例

```vue
<template>
    <!-- 数据 -->
    <div>
        <!-- 方法一 -->
        <span>{{ msg }}</span>
        <span> ~~~~~ </span>
        <!-- 方法二 -->
        <span>{{ $store.state.moduleA['msg'] }}</span>
    </div>

    <!-- 修改数据 -->
    <div>
        <button @click="updMsg">修改state数据</button>
        <span> ~~~~~ </span>
        <button @click="$store.commit('moduleA/updMsg', '!dlroW olleH')">修改state数据</button>
    </div>

    <!-- 异步修改数据 -->
    <div>
        <button @click="asyncMsg">异步修改state数据</button>
        <span> ~~~~~ </span>
        <button @click="$store.dispatch('moduleA/asyncMsg')">异步修改state数据</button>
    </div>

    <!-- 通过getters计算属性拿到数据 -->
    <div>
        <span>{{ str }}</span>
        <span> ~~~~~ </span>
        <span>{{ $store.getters['str'] }}</span>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import store from './store';
const msg = ref(store.state.moduleA['msg'])
const str = ref(store.getters['str'])

// 调用updMsg方法修改msg数据
const updMsg = () => {
    store.commit("moduleA/updMsg", "!dlroW olleH")
}

// 调用asyncMsg方法使msg数据每500毫秒自增加1
const asyncMsg = () => {
    store.dispatch("moduleA/asyncMsg")
}

// 组合监听多个数据是否发生变化
watch([() => store.state.moduleA['msg'], () => store.getters['str']], (data) => {
    const [msg, str] = data
    console.log(msg, 111);
    console.log(str, 222);
    
    msg.value = msg
    str.value = str
})
</script>
```

```javascript
import { createStore } from "vuex";

export default createStore({
  modules: {
    moduleA: {
      namespaced: true,
      state() {
        return {
          msg: "Hello World!",
        };
      },

      mutations: {
        updMsg(state, val) {
          state.msg = val;
        },
      },

      actions: {
        asyncMsg({ commit }) {
          let n = 0;

          // 每500毫秒自增加1
          setInterval(() => {
            commit("updMsg", ++n);
            // ++n;
          }, 500);
        },
      },
    },
  },

  getters: {
    str: state => state.moduleA.msg,
  },
});
```

