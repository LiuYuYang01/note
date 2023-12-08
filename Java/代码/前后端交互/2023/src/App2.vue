<script setup lang="ts">
import { ref } from 'vue';
import User from '@/view/User.vue'
import Order from '@/view/Order.vue'

const activeKey = ref('3');
</script>

<template>
  <div class="page">
    <a-tabs v-model:activeKey="activeKey" centered size="large">
      <a-tab-pane key="1" tab="首页">
        <h1>Hello World!</h1>
      </a-tab-pane>

      <a-tab-pane key="2" tab="用户管理">
        <User />
      </a-tab-pane>

      <a-tab-pane key="3" tab="订单管理" force-render>
        <Order />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="scss">
.page {
  width: 1000px;
  margin: 50px auto;
}
</style>
