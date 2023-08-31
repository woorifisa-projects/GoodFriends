<template>
  <div class="main">
    <div class="search-bar">
      <input type="text" id="search" @keyup.enter="onClickSearch" />
      <label @click="onClickSearch">
        <span class="material-icons-outlined"> search </span>
      </label>
    </div>

    <div class="card-list">
      <ProductCardVue :products="products" @click="onClickProductCard" />
    </div>
    <div class="add-button">
      <button @click="onClickAddProduct">
        <span class="material-icons-outlined"> add </span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import image from '@/assets/tmp/images/image.png';

import router from '@/router';
import ProductCardVue from '@/components/ProductCard.vue';
import type { IProduct } from '@/types/product';

// TODO: 수정 -> 서버로부터

const products = ref<Array<IProduct>>([
  {
    id: 0,
    imageUrl: image,
    title: 'title',
    address: '주소가 길어지면 어떻게 될려나 길어지면 길어지면 길어지면 길어지면',
    sellPrice: 1000,
    status: 'sell'
  },
  {
    id: 1,
    imageUrl: image,
    title: 'title1',
    address: 'address',
    sellPrice: 1000,
    status: 'sell'
  },
  {
    id: 2,
    imageUrl: image,
    title: 'title2',
    address: 'address',
    sellPrice: 1000,
    status: 'sell'
  },
  {
    id: 3,
    imageUrl: image,
    title: 'title3',
    address: 'address',
    sellPrice: 1000,
    status: 'sell'
  },
  {
    id: 4,
    imageUrl: image,
    title: 'title4',
    address: 'address',
    sellPrice: 1000,
    status: 'sell'
  }
]);

const onClickSearch = () => {
  // TODO: 상품 검색
};

const onClickAddProduct = () => {
  router.push('/admin/product/manage/add');
};

const onClickProductCard = (id: number) => {
  //router.push(`/admin/product/manage/${id}`); //임시코드(사용자-상품수정페이지)
  router.push(`/admin/product/edit/${id}`);
};
</script>

<style scoped>
.main-page {
  width: 100%;
  height: 100%;
}

.main {
  width: 100%;
  max-height: 95vh;
  justify-content: center;
  overflow-y: scroll;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}
.search-bar {
  position: relative;
  width: 100%;
  margin-top: 12px;

  display: flex;
  justify-content: center;
  align-items: center;
}
.search-bar > input {
  width: 80%;
  padding: 12px;
  font-size: 18px;
  padding-right: 36px;
}
.search-bar > label {
  position: absolute;
  right: calc(10% + 12px);
  top: 12px;
  cursor: pointer;
}
.card-list {
  width: 80%;

  margin: auto;
  margin-top: 24px;
  padding: 24px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  justify-items: center;
  gap: 24px;
}
.add-button {
  position: sticky;
  bottom: 24px;
  right: 0;
  text-align: end;
}
.add-button > button {
  border: 1px solid dodgerblue;
  background-color: dodgerblue;
  color: var(--product-add-btn-text);
  border-radius: 50%;
  width: 64px;
  height: 64px;
}

@media screen and (max-width: 1200px) {
  .card-list {
    grid-template-columns: 1fr 1fr;
  }
}

@media screen and (max-width: 1023px) {
  .card-list {
    grid-template-columns: 1fr 1fr;
  }

  .add-button {
    padding-right: 24px;
  }
}

@media screen and (max-width: 767px) {
  .card-list {
    grid-template-columns: 1fr;
  }
}
</style>
