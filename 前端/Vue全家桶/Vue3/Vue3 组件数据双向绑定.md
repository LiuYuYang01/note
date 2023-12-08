# Vue3 组件数据双向绑定

在 `Vue` 中，组件数据双向绑定是一项非常重要的特性，它使得我们能够轻松地在组件中处理数据的变化并将其同步到视图

比如我们想要在父组件中修改数据能够同步给子组件，并且子组件修改数据也能同步给父组件，使他们数据一方发生变化，则双方都发生改变。

实现组件数据双向绑定有三种写法分别是 `常规写法`、`v-model`、`defineModel`，下面给大家一一演示

## 常规写法

在点击父组件按钮时修改数据并同步给子组件

```vue
<script setup lang="ts">
import { ref } from 'vue';
import Hello from './components/Hello.vue';

const data = ref("Hello World")

// 通过调用该事件完成修改数据的操作
const changeData = (value: string) => {
  data.value = value
}
</script>

<template>
  <h1>{{ data }}</h1>
  <button @click="data = 'Hello Vue3'">修改子组件的数据</button>

  <!-- 简写：<Hello :data="data" @update="data = $event" /> -->
  <Hello :data="data" @update="changeData" />
</template>
```



点击子组件中的按钮触发父组件的自定义事件 `update` 绑定的 `changeData` 方法，并将参数 `你好，世界！` 传递给父组件的方法： `changeData` 从而实现更改父组件的数据

```vue
<script setup lang="ts">
const props = defineProps<{ data: string }>()
const emit = defineEmits<(e: "update", value: string) => void>()
</script>

<template>
  <h1>{{ data }}</h1>
  <button @click="emit('update', '你好, 世界!')">修改父组件的数据</button>
</template>
```

可以发现这种写法非常繁琐，子组件修改数据还要通过触发事件来完成



## v-model

在 `Vue3` 中，使用 `v-model` 指令来实现组件数据的双向绑定非常简单。我们可以在组件中使用 `v-model` 指令来绑定一个值，并且在组件内部可以直接操作这个值。

接下来这种写法才是 `Vue` 主流的方式：

```vue
<script setup lang="ts">
import { ref } from 'vue';
import Hello from './components/Hello.vue';

const data = ref("Hello World")
</script>

<template>
  <h1>{{ data }}</h1>
  <button @click="data = 'Hello Vue3'">修改子组件的数据</button>
  
  <!-- 核心代码 -->
  <Hello v-model="data" />
</template>
```



1. 在子组件中我们将数据以 `modelValue` 命名来接收（固定命名）

2. 给 `defineEmits` 定义一个修改数据的事件：`(e: "update:modelValue", value: string) => void`

3. 通过触发 `emit('update:modelValue', '你好, 世界!')` 来实现数据的更新操作

```vue
<script setup lang="ts">
const props = defineProps<{ modelValue: string }>()
const emit = defineEmits<(e: "update:modelValue", value: string) => void>()
</script>

<template>
  <h1>{{ modelValue }}</h1>
  <button @click="emit('update:modelValue', '你好, 世界!')">修改父组件的数据</button>
</template>
```



接下来想必大家就会有疑惑了，如果是多个数据需要双向绑定该怎么做呢？其实也很简单：

```vue
<script setup lang="ts">
import { ref } from 'vue';
import Hello from './components/Hello.vue';

const data1 = ref("Hello World")
const data2 = ref("Hello World")
</script>

<template>
  <h1>{{ data1 }}</h1>
  <h1>{{ data2 }}</h1>
  <button @click="data1 = 'Hello Vue3'">修改子组件的数据</button>
  <button @click="data2 = 'Hello TypeScript'">修改子组件的数据</button>

  <!-- 核心代码 -->
  <Hello v-model:data1="data1" v-model:data2="data2" />
</template>
```

```vue
<script setup lang="ts">
const props = defineProps<{ data1: string, data2: string }>()
const emit = defineEmits<{ (e: "update:data1", value: string): void, (e: "update:data2", value: string): void }>()
</script>

<template>
  <h1>{{ data1 }}</h1>
  <h1>{{ data2 }}</h1>
  <button @click="emit('update:data1', '你好, 世界!')">修改父组件的数据</button>
  <button @click="emit('update:data2', '你好, 世界!')">修改父组件的数据</button>
</template>
```

对比一下常规的写法，可以发现不必再通过手动绑定事件就可以达到双向绑定的需求。

而常规写法其实就是这种写法的实现原理



## defineModel

学习了上述两种写法后，接下来这种写法你一定会爱不释手，话不多说直接代码演示：

```vue
<script setup lang="ts">
import { ref } from 'vue';
import Hello from './components/Hello.vue';

const data = ref("Hello World")
</script>

<template>
  <h1>{{ data }}</h1>
  <button @click="data = 'Hello Vue3'">修改子组件的数据</button>

  <!-- 核心代码 -->
  <Hello v-model="data" />
</template>
```

```vue
<script setup lang="ts">
import { defineModel } from 'vue'
const modelValue = defineModel()
</script>

<template>
  <h1>{{ modelValue }}</h1>
  <button @click="modelValue = '你好, 世界!'">修改父组件的数据</button>
</template>
```



当然，如果想要实现多个双向数据绑定可以这么做：

```vue
<script setup lang="ts">
import { ref } from 'vue';
import Hello from './components/Hello.vue';

const data1 = ref("Hello World")
const data2 = ref("Hello World")
</script>

<template>
  <h1>{{ data1 }}</h1>
  <h1>{{ data2 }}</h1>
  <button @click="data1 = 'Hello Vue3'">修改子组件的数据</button>
  <button @click="data2 = 'Hello TypeScript'">修改子组件的数据</button>

  <!-- 核心代码 -->
  <Hello v-model:data1="data1" v-model:data2="data2" />
</template>
```

```vue
<script setup lang="ts">
import { defineModel } from 'vue'
const data1 = defineModel("data1")
const data2 = defineModel("data2")
</script>

<template>
  <h1>{{ data1 }}</h1>
  <h1>{{ data2 }}</h1>
  <button @click="data1 = '你好, 世界!'">修改父组件的数据</button>
  <button @click="data2 = '你好, 世界!'">修改父组件的数据</button>
</template>
```

使用该语法糖可以直接调用 `defineModel` 拿到父组件传递的数据，不需要再使用  `defineEmits` 与 `defineProps` 就可以操作数据，告别繁琐的组件双向数据绑定。

**注意：** 该方式仅支持 `Vue3.2.0` 及以上版本



目前该方式处于Vue官方实验阶段，所以在控制台会出现以下警告：

```
defineModel() is a compiler-hint helper that is only usable inside <script setup> of a single file component. Its arguments should be compiled away and passing it at runtime has no effect
```



解决它的办法也很简单，在 `vite.config.ts` 文件中配置如下代码即可：

```javascript
export default defineConfig({
  plugins: [
    // 核心代码
    vue({
      script: {
        defineModel: true,
      },
    }),
  ]
});
```

