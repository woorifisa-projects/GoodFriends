<template>
  <div class="add-product">
    <div class="title">내 물건 팔기</div>
    <div class="form">
      <div class="button">
        <button>임시저장</button>
      </div>
      <div class="imgs">
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="" />
        </div>
      </div>
      <div class="input-detail">
        <form action="">
          <div class="item">
            <label for="">제목</label>
            <input type="text" />
          </div>
          <div class="item">
            <label for="">카테고리</label>
            <select name="" id="">
              <option v-for="category in categories" :value="category.name" :key="category.id">
                {{ category.name }}
              </option>
            </select>
          </div>
          <div class="item">
            <label for="">가격</label>
            <input type="number" />
          </div>
          <div class="item">
            <label for="">판매 일자</label>
            <input type="date" />
          </div>
          <div class="item">
            <label for="">상품 설명</label>
            <textarea name="" id="" cols="30" rows="10"></textarea>
          </div>
          <button type="submit">등록하기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { category } from '@/types/product';
import { ref } from 'vue';

// TODO: 수정 -> 서버로부터
const categories = ref<Array<category>>([
  {
    id: 1,
    name: '가전'
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
  }
]);
</script>

<style scoped>
.add-product {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 400px !important;
}
.title {
  font-size: 36px;
  font-weight: 700;

  margin: 24px 0;
}
.button {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
  margin-bottom: 16px;
}
.button > button {
  background-color: lightgray;
  padding: 16px 24px;
  border: 1px solid black;
  border-radius: 16px;
}
.form {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;

  border: 1px solid rgba(197, 197, 197, 0.593);
  border-radius: 16px;
  box-shadow: 1px 1px 10px rgba(174, 174, 174, 0.666);
  padding: 24px;

  margin-bottom: 40px;
}
.imgs {
  overflow-x: auto;
  display: flex;
  flex-wrap: nowrap;
  gap: 12px;
  padding: 0 24px;
  margin-bottom: 12px;
}
.img {
  flex: 0 0 auto;
  width: 150px;
  height: 150px;
  border: 1px solid black;
  border-radius: 16px;
  overflow: hidden;
}
.img > img {
  width: 100%;
}
.input-detail {
}

.input-detail > form {
  display: flex;
  flex-direction: column;
  gap: 32px;
}
.item {
  display: flex;

  font-size: 24px;
}

.item > label {
  width: 160px;
}
.item > input,
.item > select,
.item > textarea {
  width: calc(100% - 160px);
}
</style>
