<template>
  <EditProduct @submit="submit" type="edit" />
</template>

<script setup lang="ts">
import EditProduct from '@/components/product/EditProduct.vue';

const submit = (obj) => {
  console.log('parent', obj);
};
</script>

<style scoped></style>
