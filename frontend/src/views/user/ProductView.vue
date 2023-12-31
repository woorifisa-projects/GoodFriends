<template>
  <div class="product-page">
    <div class="product-info">
      <div class="imgs box">
        <button name="이전 버튼" @click="onClickBannerBtn('prev')" :disabled="viewImage === 0">
          <span class="material-icons-outlined"> arrow_back_ios </span>
        </button>
        <img :src="data.imageUrls[viewImage] || image" alt="상품 이미지" />
        <button
          name="다음 버튼"
          @click="onClickBannerBtn('next')"
          :disabled="viewImage === data.imageUrls.length - 1"
        >
          <span class="material-icons-outlined"> arrow_forward_ios </span>
        </button>
      </div>
      <div class="info box">
        <div v-if="isWriter" class="wrap-btn">
          <button name="수정하기 버튼" @click="onClickEditBtn">{{ PRODUCT.EDIT }}</button>
          <button name="삭제하기 버튼" @click="onClickDelete">{{ PRODUCT.DELETE }}</button>
          <button name="주문서 확인하기 버튼" class="deco-btn" @click="onClickOrderView">
            {{ PRODUCT.VIEW_ORDER }}
          </button>
        </div>
        <div v-else class="wrap-btn">
          <button name="신고하기 버튼" @click="onClickReport">{{ PRODUCT.REPORT }}</button>
          <button name="주문하기 버튼" class="deco-btn" @click="onClickOrder">
            {{ PRODUCT.ORDER }}
          </button>
        </div>
        <div class="detail-info">
          <div class="name">{{ data.title }}</div>
          <div class="status">{{ PRODUCT_STATUS[data.status] }}</div>
          <div class="price">{{ data.sellPrice.toLocaleString() }}원</div>
          <div class="category">{{ CATEGORY[data.productCategory] }}</div>
          <div class="date">{{ PRODUCT.CREATE_AT }}: {{ data.createdDate }}</div>
        </div>
      </div>
    </div>
    <div class="product-user">
      <div class="img">
        <img :src="data.profileImageUrl" alt="프로필 이미지" />
      </div>
      <div>{{ data.nickName }}</div>
    </div>
    <div class="product-detail box">{{ data.description }}</div>
    <OrderModal v-model:is-visible="orderModalVisible" :product-id="id" />
    <ConfirmModal
      :content="[`정말로 삭제하시겠습니까?`, `삭제후 다시 복구는 불가능합니다.`]"
      v-model:is-visible="confirm.isVisible"
      v-model:response="confirm.response"
    />
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { onMounted, ref, watchEffect } from 'vue';
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
import image from '@/assets/tmp/images/image.png';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import ConfirmModal from '@/components/ConfirmModal.vue';

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

const orderModalVisible = ref(false);
const confirm = ref({
  isVisible: false,
  response: false
});

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
  confirm.value.isVisible = true;
};

const onClickOrderView = () => {
  router.push({
    name: 'view order', // Vue Router에서 설정한 라우트 이름
    params: {
      id: id
    },
    state: {
      title: data.value.title,
      imageUrl: data.value.imageUrls[0] || image
    }
  });
};

const onClickReport = () => {
  // TODO: 신고하기
  router.push(`/report/` + id);
};

const onClickOrder = () => {
  orderModalVisible.value = true;
};
watchEffect(async () => {
  if (!confirm.value.response) return;
  loadingStore.setLoading(true);
  const res = await productAPI.deleteProduct(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    id
  );
  if (res.isSuccess) {
    goPageWithReload('');
    loadingStore.setLoading(false);
  } else {
    alert(res.message);
    loadingStore.setLoading(false);
  }
});
onMounted(async () => {
  loadingStore.setLoading(true);
  const res = await productAPI.getProduct(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    id
  );
  loadingStore.setLoading(false);

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
  box-shadow: 0px 0px 3px #888;
  transition: all 0.3s ease;
  font-family: 'LINESeedKR-Bd';
}
.wrap-btn > button:hover {
  transform: scale(1.05);
  background-color: var(--color-yellow);
}
.deco-btn {
  background-color: var(--color-yellow);
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
  font-size: 20px;
}
.product-user > .img {
  width: 48px;
  height: 48px;

  border: 1px solid rgb(240, 240, 240);
  border-radius: 50%;

  overflow: hidden;
}
.product-user > .img > img {
  width: 100%;
}
.product-detail {
  min-height: 320px;

  margin-top: 32px;
  margin-bottom: 42px;
  padding: 32px;
  font-size: 24px;
}
.box {
  border: 1px solid rgb(240, 240, 240);
  box-shadow: 0px 0px 3px #eaeaea;
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
