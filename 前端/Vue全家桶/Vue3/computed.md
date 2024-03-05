# Computed

```vue
<script setup lang="ts">
import { computed, ref } from 'vue'
const data = ref<string>("")

const newData = computed(() => "data值为：" + data.value)
</script>

<template>
  <input type="text" v-model="data">
  <div>{{ newData }}</div>
</template>

<style scoped lang="scss"></style>
```



```vue
<script setup lang="ts">
import { computed, ref } from 'vue'
const data = ref<string>("")

const newData = computed({
  // 返回值发生变化时触发
  get() {
    console.log("值发生了变化");
    return "data值为：" + data.value
  },
  // 当newData被修改后触发
  set(val) {
    console.log("值被修改了");
    data.value = val
  }
})
</script>

<template>
  <input type="text" v-model="data">
  <div>{{ newData }}</div>

  <button @click="newData = 'hello'">修改</button>
</template>

<style scoped lang="scss"></style>
```

