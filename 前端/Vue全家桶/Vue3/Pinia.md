# Pinia

- `Pinia` 是一个状态管理工具，它和 `Vuex` 一样，为 `Vue` 应用程序提供全局数据共享能力。
- 语法和 `Vue3` 一样，它实现状态管理有两种语法：选项式API 与 组合式API，我们学习组合式 `API` 语法。
- 它支持 `Vue2` 也支持 `devtools`，当然它也是类型安全的，支持 `TypeScript`



## 快速上手

**安装 Pinia**

```bash
npm i pinia
```



**导入实例化**，当做插件使用，和其他插件使用套路相同

`src/main.ts`

```javascript
import { createApp } from "vue";
import App from "./App.vue";

// 1. 导入pinia
import { createPinia } from "pinia";

// 2. 创建pinia实例
const pinia = createPinia()

const app = createApp(App)

// 3. 安装pinia
app.use(pinia);
app.mount("#app");
```



## 使用数据

`src/store/index.ts`

```javascript
import { ref } from "vue";
import { defineStore } from "pinia";

// 导出共享状态管理
export default defineStore("count", () => {
  const n = ref(1024);

  return { n };
});
```

`src/App.vue`

```vue
<template>
  <div>{{ store.n }}</div>
</template>

<script setup lang="ts">
import Store from './store'
const store = Store()
</script>
```



## 修改数据

### 同步修改数据

```javascript
import { ref } from "vue";
import { defineStore } from "pinia";

// 导出共享状态管理
export default defineStore("count", () => {
  const n = ref(1024);

  const update = () => {
    n.value = 2048
  };

  return { n, update };
});
```

```vue
<template>
  {{ store.n }}

  <button @click="update">按钮</button>
</template>

<script setup lang="ts">
import Store from './store'
const store = Store()

const { update } = Store()
</script>
```



### 异步修改数据

```javascript
import { ref } from "vue";
import { defineStore } from "pinia";

// 导出共享状态管理
export default defineStore("count", () => {
  const n = ref(1024);

  const update = () => {
    setTimeout(() => {
      n.value = 2048;
    }, 1000);
  };

  return { n, update };
});
```

```vue
<template>
  <div>{{ store.n }}</div>

  <button @click="update">按钮</button>
</template>

<script setup lang="ts">
import Store from './store'
const store = Store()

const { update } = Store()
</script>
```



## computed

```javascript
import { ref, computed } from "vue";
import { defineStore } from "pinia";

// 导出共享状态管理
export default defineStore("count", () => {
  const n = ref(1024);

  const update = () => {
    n.value = 2048;
  };

  // 直接使用computed，watch同理
  const num = computed<number>(() => n.value * 2);

  return { num, update };
});
```

```vue
<template>
  <div>{{ store.num }}</div>

  <button @click="update">按钮</button>
</template>

<script setup lang="ts">
import Store from './store'
const store = Store()

const { update } = Store()
</script>
```



## storeToRefs

如果直接从 `Store` 中解构，数据会丢失响应式， 可以使用 `storeToRefs` 保证解构出来的数据为响应式的

```vue
<script setup lang="ts">
import Store from './store'
import { storeToRefs } from 'pinia'
    
// 不适合解构的方式拿到数据，会导致丢失响应式
// const { n } = Store()

// 所以我们可以通过 storeToRefs 拿到数据，并且是响应式的
const { n } = storeToRefs(store)
</script>
```



**示例**

```vue
<template>
  <div>{{ store.n }}</div>

  <button @click="update">按钮</button>
</template>

<script setup lang="ts">
import Store from './store'
import { storeToRefs } from 'pinia'

const store = Store()
    
// 方法可以直接解构，而普通数据需要先转换为响应式的
const { update } = store

// 不支持如下解构，会导致数据丢失响应式
// const { n } = store

// 解构数据我们可以使用 storeToRefs
const { n } = storeToRefs(store)

// 数据也可以这样导出使用
// const n = store.n
</script>
```



## $patch

在 `pinia` 中可以直接修改 `store` 的值，也可以调用 `$patch` 方法来批量修改

```javascript
store.$patch({
  x: 100,
  y: 200,
  z: 300
})
```



**示例**

```vue
<template>
  <div>{{ store.x }} {{ store.y }}</div>

  <button @click="btn">按钮</button>
</template>

<script setup lang="ts">
import useMainStore from './store'
const store = useMainStore()

const btn = () => {
  // 方法一、直接接修改
  store.x = 100

  // 方法二、通过$patch修改
  store.$patch({
    y: 200
  })
}
</script>
```



## $state

通过 `store.$state` 可以很直观和方便地实现数据管理，不需要编写额外的代码来定义和注册 `actions` 或者`getter`，减少了开发时间和复杂性

```vue
<script setup lang='ts'>
import useMainStore from './store'

const store = useMainStore()

// 核心代码
const add = () => store.$state.num += 10
</script>

<template>
    <div>state：{{ store.num }}</div>
    <button @click="add">+</button>
</template>
```

尽管直接操作 `store.$state` 可能会在某些情况下看起来更简单和快速，但从长远来看，为了更好的可维护性、可读性和扩展性所以不推荐这么做。



**注意：** 不能直接给 `store` 的 `state` 赋值，这样会破坏其响应性

```javascript
// 这实际上并没有替换`$state`
store.$state = { count: 24 }
```



但可以 `patch` 它

```javascript
// 在它内部调用 `$patch()`：
store.$patch({ count: 24 })
```



也可以通过给 `pinia` 实例的 `state` 来设置整个应用的初始 state。

```
pinia.state.value = {}
```



## 状态持久化

通过 `Pinia` 持久化插件可以确保应用程序的状态在页面刷新后仍然保持不变。这对于需要长期保存用户操作或应用程序配置的场景非常有用，例如保存用户的登录状态、用户的偏好设置等。



① 安装 [Pinia](https://www.npmjs.com/package/pinia-plugin-persistedstate) 状态持久化插件

```bash
npm i pinia-plugin-persistedstate@1.6.1
```



② 配置 `Pinia` 状态持久化插件

```javascript
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

// 核心代码 导入状态持久化
import piniaPluginPersistedState from 'pinia-plugin-persistedstate'

const app = createApp(App)
const pinia = createPinia()

// 将状态持久化安装到pinia
pinia.use(piniaPluginPersistedState)

app.use(pinia)
app.use(router)

app.mount('#app')
```



③ 使用状态持久化

`src/stores/user.ts`

```javascript
import { defineStore } from 'pinia'

const store = defineStore("user", {
    state() {
        return {
            nickname: "鱿鱼须"
        }
    },
    // pinia 状态是否持久化到本地存储
    persist: true
})

export default store
```



`src/App.vue`

```vue
<script setup lang='ts'>
import useUserStore from '@/stores/user'

const store = useUserStore()

const update = () => {
  // 修改数据后，当数据发生变化就会把最新的数据本地存储
  store.$patch({
    nickname: "尤雨溪"
  })
}
</script>

<template>
  <div>{{ store.nickname }}</div>

  <button @click="update">按钮</button>
</template>
```



## 综合案例

`src/App.vue`

```vue
<script setup lang='ts'>
import useMainStore from './store'

const store = useMainStore()

const { add, subtract } = store
</script>

<template>
  <div>state：{{ store.num }}</div>
  <div>computed：{{ store.newNum }}</div>

  <button @click="add(10)">+</button>
  <button @click="subtract(10)">-</button>
</template>

<style scoped></style>
```



`src/store/index.ts`

```javascript
import { ref, watch, computed } from 'vue'
import { defineStore } from 'pinia'

const store = defineStore("main", () => {
    const num = ref<number>(100)

    // 同步操作数据
    const add = (n: number) => {
        num.value += n
    }

    // 异步操作数据
    const subtract = (n: number) => {
        setTimeout(() => {
            num.value -= n
        }, 1000)
    }

    // 计算属性监听值的变化
    const newNum = computed<number>(() => {
        return num.value * 2
    })

    // 使用侦听器监听值的变化
	watch(num, (newData: number) => {
        console.log("watch：" + newData);
    })

    return { num, newNum, add, subtract }
})

export default store
```

