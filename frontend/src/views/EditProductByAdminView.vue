<template>
  <div id="edit-product-View">
    <div class="edit-product-total">
      <div class="edit-product-grid">
        <div class="page-title-total">
          <div class="page-title">OOO 상품 수정</div>
          <div class="image">
            <div class="product-images">
              <img src="@/assets/tmp/images/image.png" alt="profile img" />
            </div>
          </div>
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

const inputProductTitle = ref();
const inputProductPrice = ref();
const inputProductDate = ref();
const inputProductDes = ref();
const selectedCategory = ref('0');
</script>

<style scoped>
.edit-product-total {
  display: flex;
  justify-content: center;
}
.edit-product-grid {
  width: 600px;
  height: 700px;
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
.product-title-total {
  display: flex;
  height: 100%;
}
.product-Info-total {
  width: 100px;
}
.image {
  width: 170px;
  height: 170px;
  display: flex;
  justify-content: center;
}
.product-images {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.product-butns {
  display: flex;
  justify-content: center;
  gap: 30px;
}
</style>
