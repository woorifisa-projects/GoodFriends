<template>
  <div class="empty">
    <p>{{ props.text }}</p>
  </div>
</template>

<script setup lang="ts">
import { PRODUCT } from '@/constants/strings/product';

const props = defineProps({
  text: {
    type: String,
    default: '찾으시는 상품이 없어요 ㅠ.ㅠ'
  }
});
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
