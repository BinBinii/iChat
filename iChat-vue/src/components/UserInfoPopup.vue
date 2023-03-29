<script setup lang="ts">
import { ref } from 'vue'
</script>

<template>
    <div class="box"></div>
</template>

<style lang="less" scoped>
.box {
    width: 400px;
    height: 400px;
    background-color: #FFF;
    border: solid #CBCBCB 1px;
    position: absolute;
    top: 20px;
    left: 80px;
    border-radius: 8px;
    z-index: 99;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, .1);
}
</style>
