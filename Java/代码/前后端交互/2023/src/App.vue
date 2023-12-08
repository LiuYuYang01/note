<script setup lang="ts">

</script>

<template>
  <div class="box">
    <p>有志者，事竟成，破釜沉舟，百二秦关终属楚;有心人，天不负，卧薪尝胆，三千越甲可吞吴</p>
  </div>
</template>

<style lang="scss">
* {
  padding: 0;
  margin: 0;
}

p {
  margin: 0 !important;
}

.box {
  width: 150px;
  padding: 20px;
  margin: 50px auto;
  color: #fff;
  background-color: cornflowerblue;

  p {
    display: -webkit-box !important;
    overflow: hidden;
    word-break: break-all;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
  }
}
</style>
