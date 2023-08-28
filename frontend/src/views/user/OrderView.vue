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
        <li class="order" v-for="order in orderList" :key="order.id">
          <div class="info" @click="onClickItem">
            <div>{{ order.name }}</div>
            <div>{{ order.date }}</div>
            <div>{{ order.time }}</div>
          </div>
          <div class="info-detail">
            <div class="user">
              <div class="img">
                <img :src="order.image" alt="" />
              </div>
              <div>{{ order.gender }}</div>
            </div>
            <div class="requirement">
              {{ order.requirement }}
            </div>
            <div class="btn">
              <button @click="onClickDeal">{{ PRODUCT.DEAL }}</button>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import image from '@/assets/tmp/images/image.png';
import { PRODUCT } from '@/constants/strings/product';
const route = useRoute();

const id = ref(route.params.id);

const orderList = ref([
  {
    id: 0,
    name: 'user1',
    image: image,
    gender: '여',
    date: 'yyyy-mm-dd ~ yyyy-mm-dd',
    time: '00:00~00:00',
    requirement:
      '요구사항12345요구사항12345요구사항12345요구사항12345요구사항12345요구사항12345요구사항12345'
  },
  {
    id: 1,
    name: 'user1',
    image: image,
    gender: '남',
    date: 'yyyy-mm-dd ~ yyyy-mm-dd',
    time: '00:00~00:00',
    requirement: '요구사항12'
  },
  {
    id: 2,
    name: 'user2',
    image: image,
    gender: '여',
    date: 'yyyy-mm-dd ~ yyyy-mm-dd',
    time: '00:00~00:00',
    requirement: '요구사항12'
  },
  {
    id: 3,
    name: 'user3',
    image: image,
    gender: '여',
    date: 'yyyy-mm-dd ~ yyyy-mm-dd',
    time: '00:00~00:00',
    requirement: '요구사항12'
  },
  {
    id: 4,
    name: 'user4',
    image: image,
    gender: '여',
    date: 'yyyy-mm-dd ~ yyyy-mm-dd',
    time: '00:00~00:00',
    requirement: '요구사항12'
  },
  {
    id: 5,
    name: 'user5',
    image: image,
    gender: '여',
    date: 'yyyy-mm-dd ~ yyyy-mm-dd',
    time: '00:00~00:00',
    requirement: '요구사항12'
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
  // TODO: api
};
</script>

<style scoped>
.order-page {
  width: 100%;
  height: 100%;
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
  font-size: 32px;
  font-weight: 700;
}
.order-list {
  margin-top: 24px;
}
.order-list > ul {
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.order {
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;
}
.info {
  z-index: 2;
  height: 80px;

  border: 1px solid var(--order-list-item-border);
  background-color: var(--order-list-item-bg);
  border-radius: 18px;
  cursor: pointer;

  display: flex;
  justify-content: space-around;
  font-size: 18px;

  padding: 24px;
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
}
.btn {
  margin-top: 12px;
  display: flex;
  flex-direction: row-reverse;
}
.btn > button {
  border: 1px solid lightgray;
  box-shadow: 1px 1px 10px rgba(186, 186, 186, 0.488);
  padding: 12px 18px;
  border-radius: 12px;
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
