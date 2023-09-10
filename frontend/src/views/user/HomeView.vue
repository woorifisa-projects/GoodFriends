<template>
  <div id="main-page">
    <CommonBannerVue />
    <div class="main">
      <div class="search">
        <div class="search-bar">
          <input
            type="text"
            id="search"
            @keyup.enter="onClickSearch"
            v-model="keyword"
            :placeholder="PLACEHOLDER.SEARCH_INPUT"
          />
          <label @click="onClickSearch">
            <span class="material-icons-outlined"> search </span>
          </label>
        </div>
        <CategoryList v-model:selectedCategory="selectedCategory" />
      </div>
      <div v-if="products.length" class="card-list">
        <ProductCardVue :products="products" @click="onClickProductCard" />
      </div>
      <EmptyProduct v-else />
    </div>
    <div class="add-button">
      <button @click="onClickAddProduct">
        <span class="material-icons-outlined"> add </span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from 'vue';
import router from '@/router';
import ProductCardVue from '@/components/ProductCard.vue';
import CommonBannerVue from '@/components/CommonBanner.vue';
import CategoryList from '@/components/CategoryList.vue';
import productAPI from '@/apis/user/product';
import type { IAllProduct } from '@/types/product';
import EmptyProduct from '@/components/EmptyProduct.vue';
import { PLACEHOLDER } from '@/constants/strings/defaultInput';

const selectedCategory = ref('ALL');

const keyword = ref('');

const products = ref<Array<IAllProduct>>([]);

const onClickSearch = async () => {
  const res = await productAPI.getSearchTitleProduct(selectedCategory.value, keyword.value);
  if (res.isSuccess && res.data) {
    products.value = res.data;
  } else {
    products.value = [];
  }
};

const onClickAddProduct = () => {
  router.push('/product/add');
};

const onClickProductCard = (id: number) => {
  router.push(`product/${id}`);
};

watchEffect(async () => {
  if (selectedCategory.value === 'ALL') {
    const res = await productAPI.getAll();
    if (res.isSuccess && res.data) {
      products.value = res.data;
    } else {
      console.error(res.message);
    }

    return;
  }

  const res = await productAPI.getCategoryProduct(selectedCategory.value);
  if (res.isSuccess && res.data) {
    products.value = res.data;
  } else {
    products.value = [];
  }
});

onMounted(async () => {
  const res = await productAPI.getAll();
  if (res.isSuccess && res.data) {
    products.value = res.data;
  } else {
    console.error(res.message);
  }
});
</script>

<style scoped>
#main-page {
  width: 100%;
  height: 100%;
  padding: 0;
  padding-bottom: 50px;
}
.main {
  width: 100%;
  height: 100%;
  /* margin-top: 24px; */
  padding: 10px 200px;
}
.search {
  display: flex;
  flex-direction: column;
}

.search-bar {
  position: relative;
  width: 100%;
  margin-top: 12px;
  margin-right: 24px;
  right: 0;

  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-left: auto;
}
.search-bar > input {
  width: 274px;
  padding: 12px;
  font-size: 18px;
  border-radius: 24px;
  border: 1px solid rgb(240, 240, 240);
}
.search-bar > label {
  position: absolute;
  right: 12px;
  top: 12px;
  cursor: pointer;
  color: #888888;
  /* color: rgb(240, 240, 240); */
}
.card-list {
  margin: auto;
  margin-top: 24px;
  padding: 24px;

  display: grid;
  /* grid-template-columns: 1fr 1fr 1fr; */
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  grid-gap: 15px;
  justify-items: center;
  align-content: center;
  gap: 24px;
}
.add-button {
  position: sticky;
  bottom: 32px;
  text-align: end;
  padding-right: 20px;
}
.add-button > button {
  border: 1px solid rgb(240, 240, 240);
  box-shadow: 1px 1px 6px rgba(161, 161, 161, 0.224);
  /* background-color: var(--product-add-btn-bg); */
  background-color: white;
  color: #888888;
  border-radius: 50%;
  width: 64px;
  height: 64px;
}
.add-button > button > span {
  line-height: 32px;
  font-size: 32px;
  font-weight: 400;
}

@media screen and (max-width: 1200px) {
  .card-list {
    /* grid-template-columns: 1fr 1fr; */
  }
  .add-button {
    padding-right: 24px;
  }
}

@media screen and (max-width: 1023px) {
  #main-page {
    padding: 10px 0;
  }
  .search {
    margin-top: 12px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 0 48px;
  }
  .search-bar {
    width: fit-content;
    /* height: fit-content; */
    margin-top: 0;
    margin-left: 0;
  }
  .search-bar > label {
    margin-top: 12px;
  }
  .main {
    padding: 0;
  }
  .card-list {
    grid-template-columns: 1fr 1fr;
  }
  .category {
    display: none;
  }
  .small-category {
    display: flex;
    flex-direction: column;
    align-items: end;
    width: 100%;
  }
  .small-category > ul {
    width: fit-content;
    flex-wrap: wrap;
  }
  .small-category > div {
    width: 100%;
    background-color: rgb(232, 249, 255);
    border: 1px solid rgba(135, 135, 135, 0.32);
    border-radius: 12px;
    padding: 12px 24px;
    text-align: center;
    font-size: 18px;
    font-weight: 700;
    justify-content: right;
  }
}

@media screen and (max-width: 767px) {
  #main-page {
    padding: 10px 0;
  }
  .card-list {
    grid-template-columns: 1fr;
  }
  .search {
    padding: 0 12px;
  }
}
</style>
