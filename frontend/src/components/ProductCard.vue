<template>
  <div
    @click="emits('click', product.id)"
    class="card"
    v-for="product in props.products"
    :key="product.id"
  >
    <div class="img">
      <img :src="product.imageUrl || image" alt="임시 이미지" loading="lazy" />
    </div>
    <div class="detail">
      <p class="title">{{ product.title }}</p>
      <p class="address">{{ product.address }}</p>
      <p class="price">{{ product.sellPrice.toLocaleString() }}원</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { IAllProduct, IAllProductAdmin } from '@/types/product';
import image from '@/assets/tmp/images/image.png';

const props = defineProps({
  products: {
    type: Array<IAllProduct | IAllProductAdmin>,
    required: true
  }
});
const emits = defineEmits(['click']);
</script>

<style scoped>
.card {
  box-sizing: content-box;
  background-color: white;
  /* padding: 12px; */
  overflow: hidden;
  width: 300px;
  height: 400px;

  box-shadow: 1px 1px 10px rgb(196, 196, 196);
  border: 1px solid lightgray;
  border-radius: 12px;

  cursor: pointer;

  transition: all 0.5s ease;
}
.card:hover {
  transform: scale(1.02);
}
.card > .img {
  width: 300px;
  height: 300px;
  overflow: hidden;

  display: flex;
  align-content: center;
  justify-content: center;

  background-color: white;

  /* border: 1px solid rgb(164, 164, 164);
  border-radius: 12px; */
}
.card > .img > img {
  width: 100%;
  object-fit: cover;
}
.detail {
  display: flex;
  flex-direction: column;
  gap: 4px;
  padding: 0 12px;
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

  border-bottom: 1px solid lightgray;
}
.detail > .address {
  padding-right: 10px;
  font-size: 16px;
  text-align: right;
}
.detail > .price {
  padding-right: 10px;

  text-align: right;
}
</style>
