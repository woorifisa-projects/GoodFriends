<template>
  <div class="product-page">
    <div class="product-info">
      <div class="imgs">
        <button @click="onClickBannerBtn('prev')" :disabled="viewImage === 0">
          <span class="material-icons-outlined"> arrow_back_ios </span>
        </button>
        <img :src="data.imageUrls[viewImage]" alt="" />
        <button
          @click="onClickBannerBtn('next')"
          :disabled="viewImage === data.imageUrls.length - 1"
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
          <div class="name">{{ data.title }}</div>
          <div class="status select" v-if="isWriter">
            <select name="select" id="select">
              <option value="SELL">판매중</option>
              <option value="RESERVATION">예약중</option>
              <option value="COMPLETED">거래완료</option>
            </select>
          </div>
          <div class="status" v-else>{{ PRODUCT_STATUS[data.status] }}</div>
          <div class="price">{{ data.sellPrice }}원</div>
          <div class="category">{{ CATEGORY[data.productCategory] }}</div>
          <div class="date">{{ PRODUCT.CREATE_AT }}: {{ data.createdDate }}</div>
        </div>
      </div>
    </div>
    <div class="product-user">
      <div class="img">
        <img :src="data.profileImageUrl" alt="" />
      </div>
      <div>{{ data.nickName }}</div>
    </div>
    <div class="product-detail">{{ data.description }}</div>
    <OrderModal v-model:is-visible="isVisible" :product-id="0" />
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import OrderModal from '@/components/OrderModal.vue';
import { PRODUCT, PRODUCT_STATUS } from '@/constants/strings/product';
import productAPI from '@/apis/user/product';
import { CATEGORY } from '@/constants/category';
import { useUserInfoStore } from '@/stores/userInfo';
import { useLoadingStore } from '@/stores/loading';
import { goPageWithReload } from '@/utils/goPage';
import { dateFormat } from '@/utils/format';
import type { IDetailProduct } from '@/types/product';

const store = useUserInfoStore();
const route = useRoute();
const loadingStore = useLoadingStore();

const id = route.params.id.toString();
const isWriter = ref(false);

const viewImage = ref(0);

const data = ref<IDetailProduct>({
  id: 0,
  userId: null,
  adminId: null,
  description: '',
  createdDate: new Date().toString(),
  status: '',
  lastModifiedDate: '',
  imageUrls: [],
  profileImageUrl: '',
  nickName: '',
  title: '',
  productCategory: '',
  sellPrice: 0
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
  router.push('/product/edit/' + id);
};

// TODO: 확인 후 삭제하도록 수정
const onClickDelete = async () => {
  loadingStore.setLoading(true);
  const res = await productAPI.deleteProduct(store.accessToken, id);
  if (res.isSuccess) {
    goPageWithReload('');
    loadingStore.setLoading(true);
  } else {
    alert(res.message);
    loadingStore.setLoading(true);
  }
};

const onClickReport = () => {
  // TODO: 신고하기
};

const onClickOrder = () => {
  isVisible.value = true;
};

onMounted(async () => {
  const res = await productAPI.getProduct(store.accessToken, id);
  if (res.isSuccess && res.data) {
    data.value = res.data;
    data.value.createdDate = dateFormat(new Date(res.data.createdDate));
    if (store.id > 0) {
      isWriter.value = data.value.userId === store.id;
    } else isWriter.value = false;
  } else {
    alert(res.message);
    router.go(-1);
    return;
  }
});
</script>

<style scoped>
.product-page {
  width: 100%;
  height: 199%;
  /* color: black; */
}
.product-info {
  display: flex;
  gap: 12px;
  margin-top: 32px;
}
.imgs {
  flex: 1;

  height: 500px;

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
  flex: 1;

  display: flex;
  flex-direction: column;

  border: 1px solid rgba(159, 159, 159, 0.38);

  box-shadow: 1px 1px 10px rgba(150, 150, 150, 0.247);
}
.wrap-btn {
  margin-top: 24px;
  margin-right: 24px;
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
  background-color: var(--category-item-point-bg);
}
.detail-info {
  padding: 24px;

  display: flex;
  flex-direction: column;

  gap: 24px;
  font-size: 24px;
  height: 100%;
}
.name {
  font-size: 36px;
  font-weight: 700;
  text-align: center;
  margin-bottom: 12px;
  font-family: 'LINESeedKR-Bd';
}
.status {
  text-align: end;
  padding-right: 42px;
}
.select > select {
  font-size: 18px;
  padding: 6px 12px;
  font-family: 'LINESeedKR-Rg';
  border-radius: 10px;
  border: 1px solid rgb(200, 200, 200);
}
.select > select > option {
  font-family: 'LINESeedKR-Rg';
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
  padding: 32px;
  font-size: 32px;
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
