<template>
  <div class="order-page">
    <div class="product-info">
      <div class="img">
        <img :src="product.image" alt="" />
      </div>
      <div class="title">{{ product.title }}</div>
    </div>
    <div v-if="dealStatus" class="deal-status">이미 주문서를 선택한 제품입니다.</div>
    <div class="order-list">
      <ul>
        <div class="order-list-header">
          <div>닉네임</div>
          <div>가능한 날짜</div>
          <div>가능한 시간</div>
        </div>
        <div v-if="!orderList">
          <EmptyProduct />
        </div>
        <li v-else class="order" v-for="order in orderList" :key="order.id">
          <div class="info" @click="onClickItem($event, order.id)">
            <div>{{ order.nickName }}</div>
            <div>{{ order.possibleDate }}</div>
            <div>{{ order.possibleTime }}</div>
          </div>
          <div class="info-detail">
            <div class="user">
              <div class="img">
                <img :src="order.profileImageUrl" alt="상품 이미지" />
              </div>
              <div>{{ order.nickName }}</div>
            </div>
            <div class="requirement">
              {{ order.requirements }}
            </div>
            <div class="btn">
              <button v-if="dealStatus" @click="onClickDeal">{{ PRODUCT.CONFIRM }}</button>
              <button v-else @click="onClickDeal">{{ PRODUCT.DEAL }}</button>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <ConfirmModal
      v-if="dealStatus"
      v-model:is-visible="isVisible"
      v-model:response="response"
      v-model:visibleButton="showOnlyYes"
      :content="dealCompleteContents"
    />
    <ConfirmModal
      v-else
      v-model:is-visible="isVisible"
      v-model:response="response"
      :content="contents"
    />
    <!-- :content="['정말 거래하시겠습니까?', '이후 취소는 불가능합니다.']" -->
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { ORDER_MODAL, PRODUCT } from '@/constants/strings/product';
import type { IOrderResponse } from '@/types/order';
import ConfirmModal from '@/components/ConfirmModal.vue';
import orderAPI from '@/apis/user/order';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import EmptyProduct from '@/components/EmptyProduct.vue';
import router from '@/router';
const route = useRoute();

const isVisible = ref(false);
const response = ref(false);
const showOnlyYes = ref(true);
const id = route.params.id.toString();
const clickOrderId = ref(0);

const { title, imageUrl } = history.state;

const orderList = ref<Array<IOrderResponse>>();
const dealStatus = ref(false);

const product = ref({
  image: imageUrl,
  title: title
});

const dealCompleteContents = ref(['', '']);
const contents = ref(ORDER_MODAL.CONFIRM);

const onClickItem = (event: Event, id: number) => {
  const target = event.target as HTMLDivElement;
  target.classList.toggle('open');
  clickOrderId.value = id;
};

const onClickDeal = async () => {
  if (dealStatus.value === true) {
    const res = await orderAPI.patchDealOrder(
      localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
      clickOrderId.value.toString()
    );
    if (res.isSuccess) {
      isVisible.value = true;
      response.value = true;
      showOnlyYes.value = false;
      dealCompleteContents.value = [`이름: ${res.data?.nickName}`, `이메일: ${res.data?.email}`];
    }
  }

  isVisible.value = true;
};
watchEffect(async () => {
  if (!response.value) return;
  // TODO: api
  const res = await orderAPI.patchDealOrder(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    clickOrderId.value.toString()
  );
  if (res.isSuccess) {
    isVisible.value = true;
    contents.value = [`이름: ${res.data?.nickName}`, `이메일: ${res.data?.email}`];
  }
});

onMounted(async () => {
  const res = await orderAPI.getOrder(localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '', id);
  if (!res.isSuccess) {
    alert(res.message);
    router.go(-1);
  }
  if (res.data) {
    orderList.value = res.data.responses;
    dealStatus.value = res.data.dealStatus;
  }
});
</script>

<style scoped>
.order-page {
  width: 100%;
  height: 100%;
  margin-bottom: 120px;
}
.product-info {
  display: flex;
  align-items: center;
  padding: 24px;

  border-bottom: 1px solid rgb(166, 166, 166);
}
.product-info > .img {
  box-shadow: 0px 0px 1px rgba(153, 153, 153, 0.552);
  border: 1px solid rgb(240, 240, 240);
  border-radius: 16px;

  width: 100px;
  height: 100px;
  overflow: hidden;

  margin-right: 24px;
}
.img > img {
  /* over */
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}
.product-info > .title {
  font-family: 'LINESeedKR-Bd';
  font-size: 32px;
}

.deal-status {
  font-size: 25px;
  text-align: center;
  background-color: #fbd668;
}

.order-list {
  margin-top: 24px;
}
.order-list-header {
  padding: 0 24px;
  display: flex;
  justify-content: space-around;
  font-size: 16px;
}
.order-list > ul {
  display: flex;
  flex-direction: column;
  gap: 24px;
}
.order {
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
}
.info {
  height: 80px;

  border: 1px solid var(--color-yellow);
  background-color: #fff;
  z-index: 2;
  border-radius: 18px;
  cursor: pointer;

  display: flex;
  justify-content: space-around;
  font-size: 18px;

  padding: 24px;

  transition: all 0.2s ease;
}
.info > div {
  pointer-events: none;
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.info-detail {
  opacity: 0;
  z-index: -1;

  position: absolute;
  left: 0;
  top: 0;

  display: flex;
  flex-direction: column;
  padding: 12px 24px;

  width: 100%;
  height: 250px;
  background-color: transparent;
  transition: all 0.2s ease-in;
}
.open ~ .info-detail {
  opacity: 1;
  background-color: white;

  top: 80px;

  z-index: 1;
}
.open {
  margin-bottom: 250px;
}
.info-detail > .user {
  padding: 12px;
  display: flex;
  align-items: center;
  gap: 18px;
  font-size: 18px;
}
.user > .img {
  width: 50px;
  height: 50px;
  overflow: hidden;
  border-radius: 50%;
  border: 1px solid #88888852;
}
.requirement {
  border: 1px solid rgb(240, 240, 240);
  padding: 24px;
  border-radius: 12px;
  height: 300px;
  overflow-y: auto;
}
.btn {
  margin-top: 12px;
  display: flex;
  flex-direction: row-reverse;
}
.btn > button {
  height: 40px;
  padding: 20px;

  overflow: hidden;

  background-color: #fbd668;
  box-shadow: 1px 1px 2px rgb(202, 202, 202);
  border-radius: 12px;

  display: flex;
  align-items: center;

  font-family: 'LINESeedKR-Bd';
  text-align: center;
  font-size: 16px;

  transition: all 0.2s ease;
}
.btn > button:hover {
  transform: scale(1.05);
}

@keyframes open {
  0% {
    opacity: 0;
    margin-left: -20px;
  }
  100% {
    opacity: 1;
    margin-left: 0px;
  }
}

@keyframes close {
  0% {
    opacity: 1;
    margin-left: 0px;
  }
  100% {
    opacity: 0;
    margin-left: -20px;
  }
}
</style>
