<template>
  <div class="container">
    <h1>Hello World!</h1>

    <img src="~assets/xlb.jpg" alt="">
    <img src="@/assets/xlb.jpg" alt="">
    <img src="../assets/xlb.jpg" alt="">
  </div>
</template>

<script lang="ts" setup>
import Vue from 'vue'

Vue.extend({
  name: 'IndexPage'
})
</script>
