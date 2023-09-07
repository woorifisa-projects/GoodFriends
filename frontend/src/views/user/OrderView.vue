<template>
  <div class="order-page">
    <div class="product-info">
      <div class="img">
        <img :src="product.image" alt="" />
      </div>
      <div class="title">{{ product.title }}</div>
    </div>
    <div class="order-list">
      <ul>
        <div class="order-list-header">
          <div>닉네임</div>
          <div>가능한 날짜</div>
          <div>가능한 시간</div>
        </div>
        <li class="order" v-for="order in orderList" :key="order.id">
          <div class="info" @click="onClickItem">
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
              <button @click="onClickDeal">{{ PRODUCT.DEAL }}</button>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <ConfirmModal
      v-model:is-visible="isVisible"
      v-model:response="response"
      :content="['정말 거래하시겠습니까?', '이후 취소는 불가능합니다.']"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import image from '@/assets/tmp/images/image.png';
import { PRODUCT } from '@/constants/strings/product';
import type { IOrderResponse } from '@/types/order';
import ConfirmModal from '@/components/ConfirmModal.vue';
const route = useRoute();

const isVisible = ref(false);
const response = ref(false);

const id = ref(route.params.id);

const orderList = ref<Array<IOrderResponse>>([
  {
    id: 1,
    userId: 1,
    profileImageUrl: image,
    nickName: '작업완료함1',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements:
      '에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다!v 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다!s 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다!v 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다! 에어팟 구매하고 싶습니다!s'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  },
  {
    id: 2,
    userId: 2,
    profileImageUrl: image,
    nickName: 'nickname2',
    possibleDate: '2023-08-30 ~ 2023-09-01',
    possibleTime: '12:00 ~ 16:00',
    requirements: '에어팟 구매하고 싶습니다!'
  }
]);

const product = ref({
  image,
  title: 'title'
});

const onClickItem = (event: Event) => {
  const target = event.target as HTMLDivElement;
  target.classList.toggle('open');
};

const onClickDeal = () => {
  isVisible.value = true;
};
watchEffect(() => {
  console.log(response.value);
  if (!response.value) return;
  // TODO: api
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
  box-shadow: 1px 1px 10px rgba(153, 153, 153, 0.552);
  border: 1px solid rgb(153, 153, 153);
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
.order-list {
  margin-top: 24px;
}
.order-list-header {
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

  border: 2px solid var(--order-list-item-border);
  background-color: var(--order-list-item-bg);
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
  border: 1px solid black;
}
.requirement {
  border: 1px solid lightgray;
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
  border: 1px solid lightgray;
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
