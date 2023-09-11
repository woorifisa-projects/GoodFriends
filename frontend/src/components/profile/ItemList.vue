<template>
  <div class="wrap">
    <ul>
      <li>
        <div class="item" v-for="item in props.items" :key="item.productId" @click="onClick(item)">
          <div class="img">
            <img :src="item.imageUrl || tmpImage" alt="" />
          </div>
          <div class="detail">
            <div>{{ item.title }}</div>
            <div>{{ item.sellPrice.toLocaleString() }}원</div>
            <div class="status">{{ item.status }}</div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import type { ISellAndPurchaseList } from '@/types/profile';
import { tmpImage } from '@/utils/image';

const props = defineProps({
  items: {
    type: Array<ISellAndPurchaseList>,
    required: true
  }
});

const onClick = (item: ISellAndPurchaseList) => {
  router.push('/product/' + item.productId);
};
</script>

<style scoped>
.wrap {
  margin: 20px 56px;
}

.wrap > ul {
  display: flex;
  flex-direction: column;
}
.wrap > ul > li {
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 10px 20px;
  width: 100%;
  height: fit-content;
  border: 1px solid rgb(240, 240, 240);
  border-radius: 10px;
  box-shadow: 0px 0px 3px #0000001b;
}

.item > .img {
  width: 80px;
  height: 80px;
  overflow: hidden;
  border: 1px solid rgb(240, 240, 240);
  box-shadow: 0px 0px 2px #888;
  border-radius: 20px;
}

.img > img {
  height: 100%;
}

.detail {
  display: flex;
  justify-content: space-around;
  /* border: 1px solid gray; */
  width: calc(100% - 100px);
  border-radius: 20px;
  overflow: hidden;
  cursor: pointer;
}

.detail > div {
  /* background-color: rgb(232, 232, 232); */
  width: 100%;
  /* height: 100%; */
  /* padding: 20px; */
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
}
.detail > div:last-child {
  font-size: 18px;
}
@media screen and (max-width: 1023px) {
  .detail {
    height: 60px;
  }
  .detail > div {
    font-size: 15px;
    line-height: 24px;
    padding: 0;
  }
  .item > .img {
    width: 50px;
    height: 50px;
  }
}

@media screen and (max-width: 767px) {
}
</style>
