<template>
  <div class="wrap">
    <ul>
      <li>
        <div class="item" v-for="item in props.items" :key="item.id" @click="onClick(item)">
          <div class="img">
            <img src="@/assets/tmp/images/image.png" alt="" />
          </div>
          <div class="detail">
            <div>{{ item.name }}</div>
            <div>{{ item.date }}</div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import type { Item } from '@/types/Profile';

const props = defineProps({
  items: {
    type: Array<Item>,
    required: true
  }
});

const onClick = (item: Item) => {
  console.log(item);
  // TODO: 상세 페이지 완성 후 수정 + 삭제 기능 추가할 시 삭제 및 페이지 이동으로 수정
};
</script>

<style scoped>
.wrap {
  margin: 20px;
}

.wrap > ul {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 12px 20px;
  width: 100%;
  border: 1px solid rgb(235, 235, 235);
  border-radius: 10px;
  box-shadow: 1px 1px 7px #0000001b;
}

.item > .img {
  width: 100px;
  height: 100px;
  overflow: hidden;
  border: 1px solid gray;
  border-radius: 20px;
}

.img > img {
  height: 100%;
}

.detail {
  display: flex;
  justify-content: space-around;
  border: 1px solid gray;
  height: 100px;
  width: calc(100% - 100px);
  border-radius: 20px;
  overflow: hidden;
}

.detail > div {
  background-color: rgb(232, 232, 232);
  width: 100%;
  height: 100%;
  padding: 20px;
  text-align: center;
  line-height: 58px;
  font-size: 24px;
}

@media screen and (max-width: 1023px) {
  .detail {
    height: 60px;
  }
  .detail > div {
    font-size: 18px;
    line-height: 24px;
  }
  .item > .img {
    width: 50px;
    height: 50px;
  }
}

@media screen and (max-width: 767px) {
}
</style>
