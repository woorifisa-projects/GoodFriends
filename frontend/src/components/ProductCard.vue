<template>
  <div
    @click="emits('click', product.id)"
    class="card"
    v-for="product in props.products"
    :key="product.id"
  >
    <div class="img">
      <img :src="product.image" alt="임시 이미지" />
    </div>
    <div class="detail">
      <p class="title">{{ product.title }}</p>
      <p class="address">{{ product.address }}</p>
      <p class="price">{{ product.price }}원</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { product } from '@/types/product';

const props = defineProps({
  products: {
    type: Array<product>,
    required: true
  }
});
const emits = defineEmits(['click']);
</script>

<style scoped>
.card {
  box-sizing: content-box;
  background-color: white;
  padding: 12px;
  width: 250px;
  height: 350px;

  box-shadow: 1px 1px 10px rgb(196, 196, 196);
  border-radius: 12px;

  cursor: pointer;

  transition: all 0.5s ease;
}
.card:hover {
  transform: scale(1.05);
}
.card > .img {
  width: 250px;
  height: 250px;
  overflow: hidden;

  background-color: white;

  border: 1px solid black;
  border-radius: 12px;
}
.card > .img > img {
  width: 100%;
}
.detail {
  display: flex;
  flex-direction: column;
  gap: 4px;

  text-align: center;
}
.detail > p {
  width: 100%;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.detail > .title {
  font-size: 24px;
  font-weight: 600;

  border-bottom: 1px solid black;
}
.detail > .address {
  font-size: 16px;
}
.detail > .price {
  text-align: right;
}
</style>
