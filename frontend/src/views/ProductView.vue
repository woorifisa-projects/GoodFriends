<template>
  <div class="product-page">
    <div class="product-info">
      <div class="imgs">
        <img src="@/assets/tmp/images/image.png" alt="" />
      </div>
      <div class="info">
        <div v-if="isWriter" class="wrap-btn">
          <button @click="onClickEditBtn">수정하기</button>
          <button @click="onClickDelete">삭제하기</button>
        </div>
        <div v-else class="wrap-btn">
          <button @click="onClickReport">신고하기</button>
        </div>
        <div class="detail-info">
          <div class="name">0000 팝니다~</div>
          <div class="price">100000 원</div>
          <div class="category">category</div>
          <div class="date">등록날짜: yyyy-mm-dd</div>
        </div>
      </div>
    </div>
    <div class="product-user">
      <div class="img">
        <img src="@/assets/tmp/images/image.png" alt="" />
      </div>
      <div>name</div>
      <div>gender</div>
    </div>
    <div class="product-detail">상세 내용</div>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const id = ref(route.params.id);
// TODO: 작성자 인지 아닌지 -> API 연결 필요
const isWriter = ref(Math.random() < 0.5);

const onClickEditBtn = () => {
  router.push('/product/edit/' + id.value);
};

const onClickDelete = () => {
  // TODO: delete
};

const onClickReport = () => {
  // TODO: 신고하기
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

  width: 100%;

  overflow: hidden;
  text-align: center;

  margin: auto;

  overflow: hidden;

  border: 1px solid rgba(0, 0, 0, 0.278);
  border-radius: 8px;
}
.imgs > img {
  width: 100%;
  object-fit: cover;
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
</style>
