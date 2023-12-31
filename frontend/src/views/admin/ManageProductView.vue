<template>
  <div class="main">
    <div class="search-bar">
      <input type="text" id="search" @keyup.enter="onClickSearch" v-model="keyword"/>
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
import { onMounted, ref } from 'vue';
import image from '@/assets/tmp/images/image.png';

import router from '@/router';
import ProductCardVue from '@/components/ProductCard.vue';
import type { IAllProductAdmin } from '@/types/product';
import manageLogAPI from '@/apis/admin/log';
import type { IDataType } from '@/types/table';
import adminProductAPI from '@/apis/admin/adminProduct';
import { useAdminStore } from '@/stores/admin';

const logApi = await manageLogAPI.selectLog();
const store = useAdminStore();
const data: Array<IDataType> = [];

if (logApi.isSuccess === true && logApi.data) {
  const temp = logApi.data;
  for (let i = 0; i < temp.length; i++) {
    const item = temp[i];

    let dataObject = {
      id: i + 1,
      email: item.email,
      nickname: item.nickname,
      ban: item.banCount.toString(),
      last_modified_at: item.lastModifiedAt
    };
    data.push(dataObject);
  }
} else if (logApi.isSuccess === false) {
  alert('페이지 오류입니다.');
  router.push('/404');
}

const products = ref<Array<IAllProductAdmin>>([]);

const keyword = ref('');

const onClickSearch = async () => {
  const res = await adminProductAPI.getSearchTitleProduct(store.accessToken, keyword.value);
  if(res.isSuccess && res.data) {
    products.value = res.data;
  }
  else {
    products.value = [];
  }
};

const onClickAddProduct = () => {
  router.push('/admin/product/manage/add');
};

const onClickProductCard = (id: number) => {
  //router.push(`/admin/product/manage/${id}`); //임시코드(사용자-상품수정페이지)
  router.push(`/admin/product/edit/${id}`);
};

onMounted(async () => {
  const res = await adminProductAPI.getAll(store.accessToken);
  if (res.isSuccess && res.data) {
    products.value = res.data;
  } else {
    console.error(res.message);
  }
});
</script>

<style scoped>

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
  color: black;
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
