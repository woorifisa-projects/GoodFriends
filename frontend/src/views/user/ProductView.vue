<template>
  <div class="product-page">
    <div class="product-info">
      <div class="imgs">
        <button @click="onClickBannerBtn('prev')" :disabled="viewImage === 0">
          <span class="material-icons-outlined"> arrow_back_ios </span>
        </button>
        <img :src="product.images[viewImage]" alt="" />
        <button
          @click="onClickBannerBtn('next')"
          :disabled="viewImage === product.images.length - 1"
        >
          <span class="material-icons-outlined"> arrow_forward_ios </span>
        </button>
      </div>
      <div class="info">
        <div v-if="isWriter" class="wrap-btn">
          <button @click="onClickEditBtn">{{ PRODUCT.EDIT }}</button>
          <button @click="onClickDelete">{{ PRODUCT.DELETE }}</button>
          <button @click="router.push(`${id}/order`)">{{ PRODUCT.VIEW_ORDER }}</button>
        </div>
        <div v-else class="wrap-btn">
          <button @click="onClickReport">{{ PRODUCT.REPORT }}</button>
          <button @click="onClickOrder">{{ PRODUCT.ORDER }}</button>
        </div>
        <div class="detail-info">
          <div class="name">{{ product.title }}~</div>
          <div class="price">{{ product.price }}원</div>
          <div class="category">{{ product.category }}</div>
          <div class="date">{{ PRODUCT.CREATE_AT }}: {{ product.createdAt }}</div>
        </div>
      </div>
    </div>
    <div class="product-user">
      <div class="img">
        <img :src="user.image" alt="" />
      </div>
      <div>{{ user.name }}</div>
    </div>
    <div class="product-detail">{{ product.description }}</div>
    <OrderModal v-model:is-visible="isVisible" :product-id="0" />
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import OrderModal from '@/components/OrderModal.vue';
import image from '@/assets/tmp/images/image.png';
import banner1 from '@/assets/tmp/images/banner1.jpeg';
import { PRODUCT } from '@/constants/strings/product';

const route = useRoute();

const id = ref(route.params.id);
// TODO: 작성자 인지 아닌지 -> API 연결 필요
const isWriter = ref(Math.random() < 0.5);
// TODO: API 연결
const product = ref({
  title: '상품 제목',
  description: '상세 내용',
  createdAt: 'yyyy-mm-dd',
  category: 'category',
  price: 1000,
  images: [image, banner1]
});
const viewImage = ref(0);
// TODO: API 연결
const user = ref({
  image: image,
  name: 'name'
});
const isVisible = ref(false);

const onClickBannerBtn = (flag: string) => {
  if (flag === 'next') {
    viewImage.value = viewImage.value + 1;
  } else if (flag === 'prev') {
    viewImage.value -= 1;
  }
};
const onClickEditBtn = () => {
  router.push('/product/edit/' + id.value);
};

const onClickDelete = () => {
  // TODO: delete
};

const onClickReport = () => {
  // TODO: 신고하기
};

const onClickOrder = () => {
  isVisible.value = true;
};
</script>

<style scoped>
.product-page {
  width: 100%;
  height: 199%;
}
.product-info {
  display: flex;
  gap: 12px;
  margin-top: 32px;
}
.imgs {
  flex: 1;

  width: 450px;
  height: 450px;

  display: flex;
  align-items: center;
  position: relative;

  overflow: hidden;
  text-align: center;

  margin: auto;

  overflow: hidden;

  border: 1px solid rgba(0, 0, 0, 0.278);
  border-radius: 8px;
}
.imgs > button {
  position: absolute;
  background-color: transparent;
}
.imgs > button:first-child {
  left: 0;
}
.imgs > button:last-child {
  right: 0;
}

.imgs > img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}
.info {
  flex: 2;

  display: flex;
  flex-direction: column;

  border: 1px solid rgba(159, 159, 159, 0.38);

  box-shadow: 1px 1px 10px rgba(150, 150, 150, 0.247);
}
.wrap-btn {
  display: flex;
  justify-content: end;
  gap: 24px;
  padding: 8px;
}
.wrap-btn > button {
  padding: 12px 24px;

  border-radius: 8px;
  border: 1px solid rgb(159, 159, 159);
  box-shadow: 1px 1px 10px rgba(150, 150, 150, 0.247);
  transition: all 0.3s ease;
}
.wrap-btn > button:hover {
  transform: scale(1.05);
}
.detail-info {
  padding: 24px;

  display: flex;
  flex-direction: column;

  gap: 18px;
  font-size: 24px;
  height: 100%;
}
.name {
  font-size: 48px;
  font-weight: 700;
  text-align: center;
  margin-bottom: 24px;
  font-family: 'LINESeedKR-Bd';
}
.price {
  text-align: end;
  padding-right: 42px;
  font-size: 24px;
  font-weight: 600;
}
.date {
  font-size: 14px;
  text-align: end;
  padding-right: 42px;
}
.category {
  text-align: end;
  padding-right: 42px;
}
.product-user {
  display: flex;
  align-items: center;
  gap: 12px;

  margin-top: 12px;
}
.product-user > .img {
  width: 48px;
  height: 48px;

  border: 1px solid rgba(0, 0, 0, 0.358);
  border-radius: 50%;

  overflow: hidden;
}
.product-user > .img > img {
  width: 100%;
}
.product-detail {
  min-height: 320px;

  border: 1px solid rgba(0, 0, 0, 0.339);

  margin-top: 32px;
  margin-bottom: 42px;
  padding: 24px;
}
@media screen and (max-width: 1023px) {
  .info {
    flex: 1;
  }
}

@media screen and (max-width: 767px) {
  .product-info {
    flex-direction: column;
    align-items: center;
  }
  .imgs {
    width: 350px;
    max-height: 350px;
    overflow: hidden;
  }
  .info {
    width: 100%;
    border: none;
    border-bottom: 1px solid rgba(0, 0, 0, 0.339);
    box-shadow: none;
  }
  .detail-info {
    font-size: 16px;
    gap: 12px;
  }
  .name {
    font-size: 32px;
  }
  .price {
    font-size: 18px;
  }
  .product-user {
    padding-left: 10%;
  }
  .product-detail {
    padding: 0 10%;
    border: none;
    border-top: 1px solid rgba(0, 0, 0, 0.339);
  }
}
</style>
