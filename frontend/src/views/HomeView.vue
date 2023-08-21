<template>
  <div class="main-page">
    <div class="banner">배너</div>
    <div class="main">
      <div class="category">
        <button @click="onClickPrevCategory">뒤로</button>
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
        <button @click="onClickNextCategory">앞으로</button>
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
      <button>
        <span class="material-icons-outlined"> add </span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { category } from '@/types/product';
import { computed, ref } from 'vue';
import image from '@/assets/tmp/images/image.png';

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
};

const viewCategory = computed(() => {
  const start = categoryPageNumber.value * viewCategoryNumber.value;
  return categories.value.slice(start, start + viewCategoryNumber.value);
});
</script>

<style scoped>
.main-page {
  width: 100%;
  height: 100%;
}
.banner {
  background-color: blue;
  width: 100%;
  height: 240px;
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
  background-color: skyblue;
  border: 1px solid rgba(0, 0, 0, 0.32);
  border-radius: 12px;

  display: flex;
  flex-wrap: nowrap;
  gap: 12px;

  padding: 12px;
}
.category > ul > li {
  width: 100%;
}
.category > ul > li > button {
  background-color: lightblue;

  width: 105px;
  padding: 16px 0;

  border: 1px solid black;
  border-radius: 8px;
}

.category > ul > li > .selected {
  background-color: blue;
}
.search-bar {
  position: relative;
  width: 100%;
  margin-top: 12px;

  display: flex;
  justify-content: center;
  align-items: center;

  z-index: -1;
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
  bottom: 12px;
  right: 0;
  text-align: end;
}
.add-button > button {
  border: 1px solid black;
  background-color: lightblue;
  border-radius: 50%;
  width: 56px;
  height: 56px;
}
</style>
