<template>
  <div>
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

<style scoped></style>
