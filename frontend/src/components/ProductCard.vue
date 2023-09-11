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
      <div class="line"></div>
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
  overflow: hidden;
  width: 300px;
  height: 410px;

  box-shadow: 1px 1px 3px rgba(218, 218, 218, 0.446);
  border: 1px solid lightgray;
  border-radius: 12px;

  cursor: pointer;

  transition: all 0.5s ease;
}
.card:hover {
  transform: scale(1.02);
}
.card > .img {
  padding: 24px;
  width: 300px;
  height: 300px;
  overflow: hidden;

  display: flex;
  align-content: center;
  justify-content: center;

  background-color: white;
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
  margin: auto;
  font-weight: 600;
  width: 252px;
}
.line {
  width: 252px;
  text-align: center;
  margin: auto;
  border: 0.7px solid lightgray;
}
.detail > .address {
  padding-right: 14px;
  font-size: 16px;
  text-align: right;
}
.detail > .price {
  padding-right: 14px;

  text-align: right;
}
</style>
