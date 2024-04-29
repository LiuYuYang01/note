# 快速上手

## computed

```vue
<script setup lang="ts">
import { computed, ref } from 'vue';

const x = ref<number>(0)
const y = ref<number>(0)

const sum = computed<number>({
  // 当sum被赋值时触发
  set(v: number) {
    x.value = v
    y.value = v
  },
  // 当依赖项发生变化后触发
  get() {
    return x.value + y.value
  }
})
</script>

<template>
  <h1>{{ sum }}</h1>
  <button @click="x++">相加</button>
  <button @click="x--">相减</button>

  <button @click="sum = 0">还原</button>
</template>
```

