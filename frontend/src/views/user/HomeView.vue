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
      <div class="category small-category">
        <ul v-if="smallCategoryIsOpen">
          <li v-for="category in categories" :key="category.id">
            <button
              :class="category.id === selectedCategoryId ? `selected` : ``"
              @click="onClickCategory(category.id)"
            >
              {{ category.name }}
            </button>
          </li>
        </ul>
        <div v-else>카테고리 열기</div>
        <button @click="openCategory">{{ smallCategoryIsOpen ? `닫기` : `열기` }}</button>
      </div>
      <div class="search-bar">
        <input type="text" id="search" @keyup.enter="onClickSearch" />
        <label @click="onClickSearch">
          <span class="material-icons-outlined"> search </span>
        </label>
      </div>
      <div class="card-list">
        <ProductCardVue :products="products" @click="onClickProductCard" />
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
import { computed, ref } from 'vue';
import image from '@/assets/tmp/images/image.png';
import { getBannerList } from '@/utils/image';
import router from '@/router';
import ProductCardVue from '@/components/ProductCard.vue';
import type { category } from '@/types/product';

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
const smallCategoryIsOpen = ref(false);

const openCategory = () => {
  smallCategoryIsOpen.value = !smallCategoryIsOpen.value;
};

bannerList.value = getBannerList();

const onClickBannerBtn = (flag: string) => {
  if (flag === 'next') {
    viewBanner.value = (viewBanner.value + 1) % bannerList.value.length;
  } else if (flag === 'prev') {
    viewBanner.value -= 1;
    if (viewBanner.value < 0) {
      viewBanner.value = bannerList.value.length - 1;
    }
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

const onClickProductCard = (id: number) => {
  router.push(`product/${id}`);
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
  box-sizing: content-box;
  max-width: 1300px;
  height: 300px;
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
  background-color: #198bf3;
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
  font-size: 16px;
  padding: 12px;
}
.category > button:active {
  opacity: 0.4;
}
.category > ul {
  width: 950px;
  /* background-color: var(--category-bg); */
  /* border: 1px solid rgba(135, 135, 135, 0.32); */
  /* border-radius: 32px; */

  display: flex;
  flex-wrap: nowrap;
  gap: 12px;
  justify-content: center;

  padding: 12px 24px;
}
.category > ul > li {
  width: fit-content;
}
.category > ul > li > button {
  background-color: var(--category-item-bg);

  width: 100px;
  padding: 16px 0;

  border: 1px solid rgba(66, 66, 66, 0.605);
  border-radius: 12px;

  font-family: 'LINESeedKR-Bd';
  font-size: 16px;
}

.category > ul > li > .selected {
  background-color: var(--category-item-point-bg);
  color: var(--category-item-point-text);
  /* box-shadow: 1px 1px 10px rgba(176, 176, 176, 0.827); */
}
.small-category {
  display: none;
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
  border: 1px solid #48583f;
  background-color: var(--product-add-btn-bg);
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
