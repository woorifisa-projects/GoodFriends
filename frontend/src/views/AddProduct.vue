<template>
  <div class="add-product">
    <div class="title">내 물건 팔기</div>
    <div class="button">
      <button>임시저장</button>
    </div>
    <div class="form">
      <div class="imgs">imgs</div>
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
    name: '1'
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

<style scoped></style>
