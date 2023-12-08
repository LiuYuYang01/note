# Vue3应用TS

## 创建TS项目

```bash
npm init vite 项目名称
```



## 类型系统

### 类型约束

### ref

基本数据类型约束

```vue
<script setup lang="ts">
import { ref } from "vue"

let n = ref<number>(100)
n.value = "200" //no
n.value = 200 //yes
</script>
```



### reactive

复杂数据类型约束

```vue
<script setup lang="ts">
import { reactive, ref } from "vue"
type Person = {
  name: string,
  age: number
}

let person: Person[] = reactive([
  { name: "zs", age: 20 }
])
</script>
```



### computed

约束计算属性的返回值

```javascript
computed<number>(() => 1)
```



### 获取DOM元素

```vue
<script setup lang="ts">
import { onMounted, ref } from 'vue';

const box = ref<HTMLElement | null>(null);

// 等dom元素加载完毕后执行
onMounted(() => {
  console.log(box.value);
})

// 等下一次dom循环更新结束后执行
nextTick(() => {
  console.log(box.value);
})
</script>

<template>
  <div class="box" ref="box">Hello World!</div>
</template>
```



### 组件通讯

#### 组件父向子

约束父组件向子组件传递的数据

**父组件**

```vue
<template>
    <Hello :name="name" :age="age" />
</template>

<script setup>
import { ref } from 'vue'
import Hello from './components/Hello.vue'
const name = ref("Hello")
const age = ref(100)
</script>
```



**子组件**

```vue
<template>
  <div>{{ name }}  {{ age }}</div>
</template>
```



##### 传统写法

```vue
<script setup lang="ts">
import { defineProps } from 'vue'

// 基本使用
defineProps({
  name: {
    type: String, //类型
    required: true, //是否必填
    default: "我是默认值" //默认值
  },
  age: {
    type: Number,
    required: false
  }
})
    
// or
// definedProps(["name", "age"])
</script>
```



##### TS写法

```vue
<script setup lang="ts">
import { defineProps } from 'vue'

// 参数name为字符串类型并且为可选，age为数值类型
defineProps<{ name?: string, age: number }>()
</script>
```



##### withDefaults

通过 `withDefaults` 来设置属性的默认值

```vue
<script setup lang="ts">
import { defineProps } from 'vue'

withDefaults(defineProps<{ name?: string, age: number }>(), {
  name: "我是默认值"
})
</script>
```

上面设置属性默认值的方法还是有些笨拙，所以可以使用 响应式语法糖 `ineProps`

```vue
<script setup lang="ts">
import { defineProps } from 'vue'

const { name = "我是默认值", age } = defineProps<{ name?: string, age: number }>()
</script>
```

**注意：** 目前需要 显式地选择开启，因为它还是一个实验性特性。

```javascript
export default defineConfig({
  plugins: [
    vue({
      // 核心代码
      reactivityTransform: true,
    }),
  ],
});
```



#### 组件子向父

**父组件**

```vue
<template>
    <Hello :name="name" :age="age" @updateName="update" />
</template>

<script setup>
import { ref } from 'vue'
import Hello from './components/Hello.vue'
const name = ref("Hello")
const age = ref(100)

const update = () => {
    name.value = "Hello World!"
}
</script>
```



**子组件**

```vue
<template>
  <div>{{ name }} {{ age }}</div>
  <button @click="update">按钮</button>
</template>
```



##### 传统写法

```vue
<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

defineProps<{ name?: string, age: number }>()

// 核心代码：在defineEmits里面写父组件的方法
const emit = defineEmits(['updateName'])

const update = () => {
  // 然后通过emit进行使用
  emit("updateName")
}
</script>
```



##### TS写法

```javascript
<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

defineProps<{ name?: string, age: number }>()

// const emit = defineEmits(['updateName'])

// 核心代码
// 多个
// const emit = defineEmits<{
// 	(e: "updateName"): void,
// 	(e: "updateName2"): void,
// }>()

// 单个
const emit = defineEmits<(e:"updateName") => void>()

const update = () => {
  // 然后通过emit进行使用
  emit("updateName")
}
</script>
```



**传参**

父组件

```vue
<script setup lang="ts">
import Hello from '@/components/Hello.vue'

const fun = (num: number) => {
  // 接收子组件传递的参数
  console.log(num);
}
</script>

<template>
  <Hello @parent="fun($event)" />
</template>
```



子组件

```vue
<script setup lang="ts">
// 单个
const emit = defineEmits<(e: "parent", params: number) => void>()

// 多个
// const emit = defineEmits<{ (e: "parent", params: number): void, (e: "绑定自定义事件", 需要传递的参数: 参数类型): 返回值类型 }>()

const btn = () => {
  // 调用父组件的parent方法，并传递值：100
  emit("parent", 100)
}
</script>

<template>
  <button @click="btn">点击</button>
</template>
```



#### 双向绑定

在 `Vue3` 中可以通过 `v-model` 来实现组件之间的数据双向绑定，下面是一个简单的示列

**父组件**

```html
<script setup lang="ts">
import { ref } from 'vue';
import Hello from '@/components/Hello.vue'

const data = ref<string>("TypeScript")
</script>

<template>
    <Hello v-model="data"></Hello>

    <button @click="data = 'TypeScript'">通过父组件修改数据</button>
</template>
```



**子组件**

```html
<script setup lang="ts">
// modelValue是固定属性名称，不能更改
// 在模板中可以直接使用modelValue，如果在script中需要通过props.modelValue使用
const props = defineProps<{ modelValue: string }>()
const emit = defineEmits<{ (e: "update:modelValue", value: string): void }>()
</script>

<template>
  <div>数据：{{ modelValue }}</div>

  <button @click="emit('update:modelValue', 'JavaScript')">通过子组件修改数据</button>
</template>
```

这样一来不论是父组件还是子组件修改了数据，数据都会同步发生变化



如果需要实现多个数据双向绑定，可以这么写

```html
<script setup lang="ts">
import { ref } from 'vue';
import Hello from '@/components/Hello.vue'

const data1 = ref<string>("TypeScript")
const data2 = ref<string>("JavaScript")
</script>

<template>
    <Hello v-model:data1="data1" v-model:data2="data2"></Hello>

    <button @click="data1 = 'JavaScript', data2 = 'TypeScript'">通过父组件修改数据</button>
</template>
```

```html
<script setup lang="ts">
const props = defineProps<{ data1: string, data2: string }>()
const emit = defineEmits<{ (e: "update:data1", value: string): void, (e: "update:data2", value: string): void }>()
</script>

<template>
  <div>数据1：{{ data1 }}</div>
  <div>数据2：{{ data2 }}</div>

  <button @click="emit('update:data1', 'TypeScript'), emit('update:data2', 'JavaScript')">通过子组件修改数据</button>
</template>
```



**通过 v-model 实现的原理如下**

在父组件中定义一个 `update` 方法，用于修改数据

```html
<script setup lang="ts">
import { ref } from 'vue';
import Hello from '@/components/Hello.vue'

const data = ref<string>("TypeScript")

// 修改数据
const update = (value: string) => data.value = value
</script>

<template>
    <Hello :data="data" @update="update"></Hello>
    <!-- 等价于 -->
    <!-- <Hello v-model="data"></Hello> -->

    <button @click="update('TypeScript')">通过父组件修改数据</button>
</template>
```



在子组件中调用父组件的 `update` 方法来修改数据 实现双向绑定

```html
<script setup lang="ts">
const props = defineProps<{ data: string }>()
const emit = defineEmits<{ (e: "update", value: string): void }>()
</script>

<template>
  <div>数据：{{ data }}</div>

  <!-- 通过调用父组件中的update方法来修改数据 -->
  <button @click="emit('update', 'JavaScript')">通过子组件修改数据</button>
</template>
```



### Event

```vue
<template>
  <input type="text" @change="handleChange" />
</template>

<script setup lang="ts">
// 不加类型，event默认是any，类型不安全：
// 约束参数为Event类型
const handleChange = (event: Event) => {
  // 约束event.target为HTMLInputElement类型,否则没有value属性
  const value = (<HTMLInputElement>event.target).value
  // or
  // const value = (event.target as HTMLInputElement).value

  console.log(value)
}
</script>
```



### Template Ref

`Template Ref` 表单聚焦案例

```vue
<template>
  <input type="text" ref="myInput">
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'

// 模板 ref 需要通过一个显式指定的泛型参数，建议默认值 null
const myInput = ref<HTMLElement | null>(null)

onMounted(() => {  
  // 注意为了严格的类型安全，有必要在访问 el.value 时使用可选链或类型守卫
  myInput.value?.focus()
})
</script>
```

注意为了严格的类型安全，有必要在访问 `el.value` 时使用可选链或类型守卫



### 非空断言

处理类型可能是 `null` 或 `undefined` 的值，下面的属性或函数的访问赋值：

**可选链**

有值就返回，没有值就返回 `undefined`，防止报错

```vue
<script setup lang="ts">
import { onMounted, ref } from 'vue';

const input = ref< HTMLInputElement | null >(null)

onMounted(()=>{
  // 只能访问
  console.log(input.value?.value);
})

</script>

<template>
  <div>App组件</div>
  <input type="text" ref="input" value="abc">
</template>
```



**逻辑判断**

有值就赋值，没有值就略过

```javascript
  if (input.value) {
    console.log(input.value.value)
    input.value.value = '123'
  }
```



**非空断言**

告诉TS，这个类型一定有值

```javascript
  console.log(input.value!.value)
  input.value!.value = '123'
```



### 自定义文件类型声明

#### 共享TS类型

> 掌握：使用类型声明文件提供需要共享的TS类型

- 如果多个 `.ts` 文件中都用到同一个类型，此时可以创建 `.d.ts` 文件提供该类型，实现类型共享。

- 操作步骤:

  1. 创建 `index.d.ts` 类型声明文件。
  2. 创建需要共享的类型，并使用 `export` 导出(TS 中的类型也可以使用 `import/export` 实现模块化功能)。
  3. 在需要使用共享类型的 `.ts` 文件中，通过 `import` 导入， 后缀 `.d.ts` 导入直接省略。

  

`src/types/index.d.ts`

自定义共享类型

```ts
export type userInfo = {
  name: string;
  age: number;
  hobby: string;
};
```



`src/App.vue`

使用自定义共享的类型

```vue
<script setup lang="ts">
// 通过import导入, 后缀.d.ts导入直接省略。
import { userInfo } from './types/index'
    
const info: userInfo = {
  name: "宇阳",
  age: 20,
  hobby: "敲代码"
}
</script>
```



#### 对JS文件类型声明

> 了解：使用类型声明文件给JS文件添加类型

- 在导入 `.js` 文件时，TS 会自动加载与 `.js` 同名的 `.d.ts` 文件，以提供类型声明。

- **declare** 关键字：

  1. 用于类型声明，为其他地方 (比如，.js 文件) **已存在** 的变量声明类型，而不是创建一个新的变量。

  2. 对于 `type`  `interface` 等这些明确就是 TS 类型的(只能在 TS 中使用的)，可以省略 `declare` 关键字。

  3. 其他 JS 变量，应该使用 `declare` 关键字，明确指定此处用于类型声明。



`src/util/index.js`

使用 `JS` 写一个求和的工具

```javascript
export const add = (x, y) => {
  console.log(x + y);
};
```



`src/util/index.d.ts`

通过 `declare`  关键字给刚刚写的那个求和工具声明类型

```javascript
export declare const add: (x: number, y: number) => void;
```



`src/App.vue`

如果没有在 `index.d.ts` 中声明类型，则引入时候会有 **类型提示的错误**

```vue
<script setup lang="ts">
import { add } from './util/index'
add(100, 200)
</script>
```

在调用 `add` 函数时，会自动加载同名的 `.d.ts` 文件，验证类型是否正确



## 扩展

将变量挂载到全局使用

```js
// main.ts
import { createApp } from "vue";
import App from "./App.vue";

const app = createApp(App);

// 方式一
app.config.globalProperties.liuyuyang = "Hello World!";

// 方式二
app.provide("liuyuyang", "Hello World!");

app.mount("#app");
```

```vue
<!-- App.vue -->
<script setup lang="ts">
import { ComponentInternalInstance, getCurrentInstance, inject } from 'vue';

// 方式一
const instance: ComponentInternalInstance = getCurrentInstance()!
const liuyuyang = instance.appContext.config.globalProperties.liuyuyang
console.log(liuyuyang);

// 方式二
const data = inject("liuyuyang");
console.log(data);
</script>
```



# vite

## 端口号

配置项目端口号

```js
export default defineConfig({
  server: {
    port: 8888,
  }
})
```



## 路径别名

配置 `@/` 路径别名

```javascript
// vite.config.ts
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 配置@路径别名
import { resolve } from "path"; 
 
export default defineConfig({
  plugins: [vue()],
      // ↓解析配置
  resolve: {
  	// ↓路径别名
  	alias: {
    	"@": resolve(__dirname, "./src")
    }
  }
})
```

```javascript
// tsconfig.json
{
  "compilerOptions": {
    "target": "ESNext",
    ...
 
    // 配置@路径别名
    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"]
    }, 
  },
}
```

如果 **运行不了或报错** 试下安装下 `@types/node` ，没有报错可以不用安装，有安装也没事

```bash
 npm install @types/node 
```

