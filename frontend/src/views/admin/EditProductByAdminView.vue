<template>
  <div id="edit-product-View">
    <div class="edit-product-total">
      <div class="edit-product-grid">
        <div class="page-title-total">
          <!-- TODO:  상품 수정시 제목 변수 할당 필요-->
          <div class="page-title">{{ productTitle }} : 상품 수정</div>
        </div>
        <div class="product-Info-UP">
          <div class="detail-layout">
            <Detail type="edit" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import Detail from '@/components/ProductDetailByAdmin.vue';

//TODO: 상픔정보 받아오기 api
const productTitle = ref('상품명');
</script>

<style scoped>
.edit-product-total {
  display: flex;
  justify-content: center;
}
.edit-product-grid {
  width: 600px;
  height: 670px;
  display: flex;
  flex-direction: column;
  justify-content: normal;
  align-items: center;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}
.page-title-total {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.page-title {
  font-size: 20px;
  width: 600px;
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
