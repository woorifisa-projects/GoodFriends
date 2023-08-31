<template>
  <div class="empty">
    <p>{{ PRODUCT.NOTHING }}</p>
  </div>
</template>

<script setup lang="ts">
import { PRODUCT } from '@/constants/strings/product';
</script>

<style scoped>
.empty {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 100px 0;
}
.empty > p {
  font-size: 32px;
  font-family: 'LINESeedKR-Bd';
}
</style>
