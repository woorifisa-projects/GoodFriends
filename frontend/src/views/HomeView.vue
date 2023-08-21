<template>
  <div class="main-page">
    <div class="banner">
      <button @click="onClickBannerBtn('prev')">
        <span class="material-icons-outlined"> arrow_back_ios </span>
      </button>
      <div class="banner-img">
        <img :src="bannerList[viewBanner]" alt="배너" />
      </div>
      <button @click="onClickBannerBtn('next')">
        <span class="material-icons-outlined"> arrow_forward_ios </span>
      </button>
    </div>
    <div class="main">
      <div class="category">
        <button @click="onClickPrevCategory">
          <span class="material-icons-outlined"> arrow_back_ios </span>
        </button>
        <ul>
          <li v-for="category in viewCategory" :key="category.id">
            <button
              :class="category.id === selectedCategoryId ? `selected` : ``"
              @click="onClickCategory(category.id)"
            >
              {{ category.name }}
            </button>
          </li>
        </ul>
        <button @click="onClickNextCategory">
          <span class="material-icons-outlined"> arrow_forward_ios </span>
        </button>
      </div>
      <div class="search-bar">
        <input type="text" id="search" @keyup.enter="onClickSearch" />
        <label @click="onClickSearch">
          <span class="material-icons-outlined"> search </span>
        </label>
      </div>
      <div class="card-list">
        <div class="card" v-for="product in products" :key="product.id">
          <div class="img">
            <img :src="product.image" alt="임시 이미지" />
          </div>
          <div class="detail">
            <p class="title">{{ product.title }}</p>
            <p class="address">{{ product.address }}</p>
            <p class="price">{{ product.price }}원</p>
          </div>
        </div>
      </div>
    </div>
    <div class="add-button">
      <button @click="onClickAddProduct">
        <span class="material-icons-outlined"> add </span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { category } from '@/types/product';
import { computed, ref } from 'vue';
import image from '@/assets/tmp/images/image.png';
import { getBannerList } from '@/utils/image';
import router from '@/router';

// TODO: 수정 -> 서버로부터
const categories = ref<Array<category>>([
  {
    id: 1,
    name: '전체'
  },
  {
    id: 2,
    name: '2'
  },
  {
    id: 3,
    name: '3'
  },
  {
    id: 4,
    name: '4'
  },
  {
    id: 5,
    name: '5'
  },
  {
    id: 6,
    name: '6'
  },
  {
    id: 7,
    name: '7'
  },
  {
    id: 8,
    name: '8'
  },
  {
    id: 9,
    name: '9'
  },
  {
    id: 10,
    name: '10'
  },
  {
    id: 11,
    name: '11'
  },
  {
    id: 12,
    name: '12'
  },
  {
    id: 13,
    name: '13'
  },
  {
    id: 14,
    name: '14'
  },
  {
    id: 15,
    name: '15'
  },
  {
    id: 16,
    name: '16'
  },
  {
    id: 17,
    name: '17'
  },
  {
    id: 18,
    name: '18'
  },
  {
    id: 19,
    name: '19'
  },
  {
    id: 20,
    name: '20'
  }
]);

const products = ref([
  {
    id: 0,
    image: image,
    title: 'title',
    address: '주소가 길어지면 어떻게 될려나 길어지면 길어지면 길어지면 길어지면',
    price: 1000
  },
  {
    id: 1,
    image: image,
    title: 'title1',
    address: 'address',
    price: 1000
  },
  {
    id: 2,
    image: image,
    title: 'title2',
    address: 'address',
    price: 1000
  },
  {
    id: 3,
    image: image,
    title: 'title3',
    address: 'address',
    price: 1000
  },
  {
    id: 4,
    image: image,
    title: 'title4',
    address: 'address',
    price: 1000
  }
]);

const categoryPageNumber = ref(0);
const viewCategoryNumber = ref(8);
const selectedCategoryId = ref(1);
const bannerList = ref<Array<string>>([]);
const viewBanner = ref(0);

bannerList.value = getBannerList();

const onClickBannerBtn = (flag: string) => {
  if (flag === 'next') {
    viewBanner.value = (viewBanner.value + 1) % bannerList.value.length;
  } else if (flag === 'prev') {
    viewBanner.value -= 1;
    if (viewBanner.value === 0) viewBanner.value = bannerList.value.length - 1;
  }
};

const onClickPrevCategory = () => {
  categoryPageNumber.value -= 1;
  if (categoryPageNumber.value < 0) categoryPageNumber.value = 0;
};

const onClickNextCategory = () => {
  categoryPageNumber.value += 1;
  const maxCategoryPage = Math.floor(categories.value.length / viewCategoryNumber.value);
  if (maxCategoryPage < categoryPageNumber.value) categoryPageNumber.value = maxCategoryPage;
};

const onClickSearch = () => {
  // TODO: 상품 검색
};

const onClickCategory = (id: number) => {
  selectedCategoryId.value = id;
  // TODO: 카테고리별 상품 출력
};

const viewCategory = computed(() => {
  const start = categoryPageNumber.value * viewCategoryNumber.value;
  return categories.value.slice(start, start + viewCategoryNumber.value);
});

const onClickAddProduct = () => {
  router.push('/product/add');
};

setInterval(() => {
  onClickBannerBtn('next');
}, 3000);
</script>

<style scoped>
.main-page {
  width: 100%;
  height: 100%;
}
.banner {
  width: 100%;
  height: 240px;
  position: relative;
  display: flex;
  align-items: center;
  border: 1px solid rgba(109, 109, 109, 0.155);
}
.banner > button {
  position: absolute;
  background-color: transparent;
}
.banner > button:first-child {
  left: 0;
}
.banner > button:last-child {
  right: 0;
}
.banner-img {
  width: 100%;
  height: 100%;
  text-align: center;
}
.banner-img > img {
  height: 100%;
  object-fit: cover;
}
.main {
  width: 100%;
  height: 100%;
  margin-top: 24px;
}
.category {
  width: 100%;
  padding: 20px;

  display: flex;
  justify-content: center;
  align-items: center;
  gap: 12px;
}
.category > button {
  box-shadow: 1px 1px 10px rgba(149, 149, 149, 0.482);

  border: 1px solid rgba(0, 0, 0, 0.523);
  border-radius: 12px;

  font-size: 16px;
  padding: 12px 18px;
}
.category > button:active {
  background-color: lightgray;
}
.category > ul {
  width: 950px;
  background-color: rgb(232, 249, 255);
  border: 1px solid rgba(135, 135, 135, 0.32);
  border-radius: 12px;

  display: flex;
  flex-wrap: nowrap;
  gap: 12px;
  justify-content: center;

  padding: 12px;
}
.category > ul > li {
  width: fit-content;
}
.category > ul > li > button {
  background-color: rgb(166, 209, 245);

  width: 105px;
  padding: 16px 0;

  border: 1px solid rgba(66, 66, 66, 0.605);
  border-radius: 6px;
}

.category > ul > li > .selected {
  background-color: rgb(102, 154, 226);
  color: white;
  box-shadow: inset 1px 1px 10px rgba(133, 133, 133, 0.827);
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
.card {
  box-sizing: content-box;
  background-color: white;
  padding: 12px;
  width: 250px;
  height: 350px;

  box-shadow: 1px 1px 10px rgb(196, 196, 196);
  border-radius: 12px;
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
.add-button {
  position: sticky;
  bottom: 24px;
  right: 0;
  text-align: end;
}
.add-button > button {
  border: 1px solid rgba(0, 0, 0, 0.576);
  background-color: lightblue;
  border-radius: 50%;
  width: 64px;
  height: 64px;
}
</style>
