<template>
  <div id="edit-product-View">
    <div class="edit-product-total">
      <div class="edit-product-grid">
        <div class="product-Info-UP">
          <div class="product-title-total">
            <div class="product-Info-total">
              <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_TITLE }}</div>
            </div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="inputProductTitle" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info-total">
              <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_CATEGORY }}</div>
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
            <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_PRICE }}</div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="inputProductPrice" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_SELL_DATE }}</div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="inputProductDate" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_DESCRIPTION }}</div>
            <div class="product-Info-Detail">
              <textarea class="input-product-des" v-model="inputProductDes"></textarea>
            </div>
          </div>

          <div class="product-butns" v-if="type === 'edit'">
            <div class="btnbox-edit">
              <div class="btn">
                <button class="work-btn" @click="clickEdit">
                  {{ ADMIN_PRODUCT.PRODUCT_EDIT }}
                </button>
              </div>
            </div>
            <div class="btnbox-delete">
              <div class="btn">
                <button class="work-btn" @click="clickDelete">
                  {{ ADMIN_PRODUCT.PRODUCT_DELETE }}
                </button>
              </div>
            </div>
          </div>
          <div class="product-butns" v-else-if="type === 'add'">
            <div class="btnbox-edit">
              <div class="btn">
                <button class="work-btn" @click="clickAdd">{{ ADMIN_PRODUCT.PRODUCT_ADD }}</button>
              </div>
            </div>
            <div class="btnbox-delete">
              <div class="btn">
                <button class="work-btn" @click="clickCancle">
                  {{ ADMIN_PRODUCT.PRODUCT_CANCEL }}
                </button>
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
import { useRoute } from 'vue-router';
import { ADMIN_PRODUCT } from '@/constants/strings/admin';

const props = defineProps({
  type: {
    type: String,
    validator(value: string) {
      return ['edit', 'add'].includes(value);
    },
    required: true
  }
});
if (props.type === 'edit') {
  const route = useRoute();
  const id = route.params.id;

  // TODO: API 요청 -> price, name, content, category, image, date 가져오기
}

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
    name: '음식'
  },
  {
    id: 3,
    name: '생활'
  },
  {
    id: 4,
    name: '의류'
  },
  {
    id: 5,
    name: '악세사리 '
  }
]);

const clickEdit = () => {};
const clickDelete = () => {};
const clickAdd = () => {};
const clickCancle = () => {};
</script>

<style scoped>
.edit-product-total {
  display: flex;
  justify-content: center;
  padding: 20px;
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
