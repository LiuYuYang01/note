# Pinia

- `Pinia` 是一个状态管理工具，它和 `Vuex` 一样，为 `Vue` 应用程序提供全局数据共享能力。
- 语法和 `Vue3` 一样，它实现状态管理有两种语法：选项式API 与 组合式API，我们学习组合式 `API` 语法。
- 它也支持 `Vue2` 也支持 `devtools`，当然它也是类型安全的，支持 `TypeScript`



## 快速上手

**安装 Pinia**

```bash
yarn add pinia
# or
npm i pinia
```



**导入实例化**，当做插件使用，和其他插件使用套路相同

`src/main.ts`

```javascript
import { createApp } from "vue";
import "./style.css";
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



## 同步修改数据

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



## 异步修改数据

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



## 计算属性

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



## 综合案例

`src/App.vue`

```vue
<script setup lang='ts'>
import useMainStore from './store'

const store = useMainStore()

const { add, subtract } = useMainStore()
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
    let num = ref<number>(100)

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
    watch(() => num.value, (newData: number) => {
        console.log("watch：" + newData);
    })

    return { num, newNum, add, subtract }
})

export default store
```



## storeToRefs

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

// 方法可以直接解构，数据需要先转换为响应式的
const { update } = Store()

// 解构数据我们可以使用 storeToRefs
const store = Store()
const { n } = storeToRefs(store)

// 数据也可以这样导出使用
// const n = store.n
</script>
```



## $patch

通过 `$patch` 批量修改 `state` 的数据

```javascript
store.$patch({
  n: 300
})
```



**示例**

```vue
<template>
  <div>{{ store.n }}</div>

  <button @click="btn">按钮</button>
</template>

<script setup lang="ts">
import useMainStore from './store'
const store = useMainStore()

const btn = () => {
  // 方法一、直接接修改（不推荐）
  store.n = 200

  // 方法二、通过$patch修改
  store.$patch({
    n: 300
  })
}
</script>
```



## 状态持久化

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
  // 修改数据就会把最新的数据存储到本地
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



为什么不推荐直接修改state中的数据？



## 选项式写法

### 修改数据

`src/stores/index`

```typescript
import { defineStore } from "pinia"

type State = {
    info: any
}

type Getter = {}

type Actions = {
    update(): void
}

const Store = defineStore<"user", State, Getter, Actions>("user", {
    state: () => ({
        info: "aaaaaaaaaa"
    }),
    actions: {
        async update() {
            this.info = "bbbbbbbbbb"
        },
    }
})

export default Store
```



`src/App.vue`

```vue
<script setup lang='ts'>
import useUserStore from '@/stores/user'
import { storeToRefs } from 'pinia';

// 将数据转换为响应式
const { info } = storeToRefs(useUserStore())

// console.log(useUserStore().info);
console.log(info.value); //aaaaaaaaaa
useUserStore().update()
console.log(info.value); //bbbbbbbbbb
</script>
```

