<template>
  <div id="wrap">
    <DefaultHeader />
    <RouterView />
    <DefaultFooter />
  </div>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router';
import DefaultHeader from '@/layouts/DefaultHeader.vue';
import DefaultFooter from '@/layouts/DefaultFooter.vue';
</script>

<style scoped>
#wrap {
  min-height: 100%;
  position: relative;
  padding-bottom: 150px;
}
</style>
