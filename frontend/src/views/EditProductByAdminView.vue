<template>
  <div id="edit-product-View">
    <div class="edit-product-total">
      <div class="edit-product-grid">
        <div class="page-title">OOO 상품 수정</div>
        <div class="image">
          <div class="product-images">
            <img src="@/assets/tmp/images/image.png" alt="profile img" />
          </div>
        </div>
        <div class="product-Info-UP">
          <div class="product-title-total">
            <div class="product-Info-total">
              <div class="product-Info">상품제목</div>
            </div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="inputProductTitle" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info-total">
              <div class="product-Info">카테고리</div>
            </div>
            <div class="product-Info-Detail">
              <select name="" class="seelct-category" v-model="selectedCategory">
                <option disabled value="0">선택해주세요</option>
                <option v-for="category in categories" :key="category.id" :value="category.id">
                  {{ category.name }}
                </option>
              </select>
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">가격</div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="inputProductPrice" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">판매일자</div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="inputProductDate" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">상품설명</div>
            <div class="product-Info-Detail">
              <textarea class="input-product-des" v-model="inputProductDes"></textarea>
            </div>
          </div>

          <div class="product-butns">
            <div class="btnbox-edit">
              <div class="btn">
                <button class="work-btn" @click="clickEdit">수정하기</button>
              </div>
            </div>
            <div class="btnbox-delete">
              <div class="btn">
                <button class="work-btn" @click="clickDelete">삭제하기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { category } from '@/types/product';
import { ref } from 'vue';

const inputProductTitle = ref();
const inputProductPrice = ref();
const inputProductDate = ref();
const inputProductDes = ref();
const selectedCategory = ref('0');
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

const clickEdit = () => {};
const clickDelete = () => {};
</script>

<style scoped>
.edit-product-total {
  display: flex;
  justify-content: center;
  padding: 50px;
}
.edit-product-grid {
  width: 600px;
  height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}

.page-title {
  font-size: 20px;
  width: 600px;
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.product-title-total {
  display: flex;
  height: 100%;
}
.product-Info-total {
  width: 100px;
}
.image {
  width: 100px;
  height: 100px;
  padding: 20px;
}
.product-images {
  width: 100px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.product-sellinfo-toal {
  width: 600px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.product-Info {
  width: 100px;
  text-align: center;
}
.product-Info-Detail {
  display: flex;
  width: 400px;
  height: 100%;
}
.input-product-title {
  width: 400px;
  height: 30px;
}
.input-product-des {
  width: 400px;
  height: 100px;
  vertical-align: top;
}
.seelct-category {
  width: 200px;
  height: 30px;
  display: flex;
}

.product-Info-UP {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 40px;
}
.product-butns {
  display: flex;
  justify-content: center;
  gap: 30px;
}
.btn {
  padding: 5px;
}
.work-btn {
  width: 80px;
  height: 40px;
  background-color: #6db1ff;
  font-size: 15px;
  border-radius: 16px;
}
</style>
