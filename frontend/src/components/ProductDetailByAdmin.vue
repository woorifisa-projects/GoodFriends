<template>
  <div id="edit-product-View">
    <div class="edit-product-total">
      <div class="edit-product-grid">
        <div class="product-Info-UP">
          <div class="image-total">
            <div class="image">
              <div class="image-wrap">
                <div class="image-card" v-for="(img, index) in previewImg" :key="index">
                  <div class="upload-img">
                    <img :src="img" alt="상품 이미지" />
                  </div>
                  <div class="delete-btn" @click="onClickDeleteBtn(index)">
                    <img src="@/assets/images/delete.png" alt="삭제 버튼" />
                  </div>
                </div>
              </div>
              <input
                id="image"
                type="file"
                multiple
                accept="image/png, image/gif, image/jpeg"
                @change="uploadImage"
              />
            </div>
          </div>
          <div class="product-title-total">
            <div class="product-Info-total">
              <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_TITLE }}</div>
            </div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="data.title" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info-total">
              <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_CATEGORY }}</div>
            </div>
            <div class="product-Info-Detail">
              <select name="" class="seelct-category" v-model="data.productCategory">
                <option disabled value="0">{{ ADMIN_PRODUCT.PRODCUT_DEFAULT_CATEGORY }}</option>
                <option v-for="(category, index) in categories" :key="index" :value="category">
                  {{ CATEGORY[category] }}
                </option>
              </select>
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_PRICE }}</div>
            <div class="product-Info-Detail">
              <input class="input-product-title" v-model="data.sellPrice" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_SELL_DATE }}</div>
            <div class="product-Info-Detail">
              <input class="input-product-title" :value="dateFormat(registerDate)" />
            </div>
          </div>

          <div class="product-title-total">
            <div class="product-Info">{{ ADMIN_PRODUCT.PRODUCT_DESCRIPTION }}</div>
            <div class="product-Info-Detail">
              <textarea class="input-product-des" v-model="data.description"></textarea>
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
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { ADMIN_PRODUCT } from '@/constants/strings/admin';
import { uploadFile, urlToFile } from '@/utils/file';
import router from '@/router';
import { CATEGORY, CATEGORY_LIST } from '@/constants/category';
import adminProductAPI from '@/apis/admin/adminProduct';
import { useAdminStore } from '@/stores/admin';
import { useLoadingStore } from '@/stores/loading';
import { checkProductValue } from '@/utils/validation';
import { dateFormat } from '@/utils/format';
import type { IPostProduct } from '@/types/product';

const route = useRoute();
const loadingStore = useLoadingStore();

const props = defineProps({
  type: {
    type: String,
    validator(value: string) {
      return ['edit', 'add'].includes(value);
    },
    required: true
  }
});

const data = ref<IPostProduct>({
  title: '',
  productCategory: 'ALL',
  description: '',
  sellPrice: 0
});

const inputImage = ref<Array<File>>([]);
const previewImg = ref<Array<string>>([]);
const registerDate = ref(new Date());
const categories = CATEGORY_LIST;

// TODO: 이미지 관련 작업 백엔드 연동시 재확인 필요
const uploadImage = async (event: Event) => {
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  if (!fileList) return;
  await uploadFile('img', fileList, previewImg.value, 0, inputImage.value);
};

const onClickDeleteBtn = (index: number) => {
  previewImg.value.splice(index, 1);
  inputImage.value.splice(index, 1);
};

// TODO: API 요청 -> price, name, content, category, image, date 가져오기
const store = useAdminStore();
const id = route.params.id?.toString() || '0';
// -------------------------

const createFormData = () => {
  // 모든 값들이 존재 하는지 체크
  const checkData = checkProductValue(data.value);
  if (!checkData.isSuccess) {
    alert(checkData.type);
    return;
  }

  loadingStore.setLoading(true);

  const formData = new FormData();
  Array.from(inputImage.value).map((v) => {
    formData.append('multipartFiles', v);
  });

  if (formData.getAll('multipartFiles').length === 0) {
    formData.append('multipartFiles', new Blob());
  }

  formData.append(
    'request',
    new Blob([JSON.stringify(data.value)], {
      type: 'application/json'
    })
  );
  loadingStore.setLoading(false);
  return formData;
};

const clickEdit = async () => {
  if (props.type === 'add') {
    return;
  }

  const formData = createFormData();
  if (formData === undefined) {
    return;
  }

  loadingStore.setLoading(true);
  const res = await adminProductAPI.editProduct(store.accessToken, id, formData);
  loadingStore.setLoading(false);

  if (res.isSuccess) {
    alert(res.message);
  } else {
    alert(res.message);
    return;
  }
  router.go(-1);
};

const clickDelete = async () => {
  if (props.type === 'add') {
    return;
  }

  loadingStore.setLoading(true);
  const res = await adminProductAPI.deleteProduct(store.accessToken, id);
  loadingStore.setLoading(false);

  if (res.isSuccess) {
    router.go(-1);
    return;
  }
  alert(res.message);
};

const clickAdd = async () => {
  if (props.type === 'edit') {
    return;
  }

  const formData = createFormData();
  if (formData === undefined) {
    return;
  }

  loadingStore.setLoading(true);
  const res = await adminProductAPI.postProduct(store.accessToken, formData);
  loadingStore.setLoading(false);

  if (res.isSuccess) {
    alert(res.message);
  } else {
    alert(res.message);
    return;
  }
  router.go(-1);
};

const clickCancle = () => {
  // TODO: 이전 페이지 이동
  router.go(-1);
};

onMounted(async () => {
  if (props.type === 'add') {
    return;
  }

  loadingStore.setLoading(true);
  const res = await adminProductAPI.getEditProduct(store.accessToken, id);
  loadingStore.setLoading(false);

  if (res.data === undefined || !res.isSuccess) {
    alert(res.message);
    router.go(-1);
    return;
  }
  const resData = res.data;
  const imageUrl = resData.imageUrls || [];

  previewImg.value = imageUrl;

  const images = await urlToFile(imageUrl);

  if (images === null) {
    alert('이미지를 불러오는 중 오류가 발생했습니다');
    router.go(-1);
    return;
  }

  images.forEach(async (promiseFile: Promise<File | null>) => {
    const file = await promiseFile;
    if (!file) throw new Error('파일 변환 과정중 오류 발생');
    inputImage.value.push(file);
  });

  data.value = {
    title: resData.title,
    productCategory: resData.productCategory,
    description: resData.description,
    sellPrice: resData.sellPrice
  };
  loadingStore.setLoading(false);
});
</script>

<style scoped>
.edit-product-total {
  display: flex;
  justify-content: center;
}
.image-total {
  width: 100%;
  height: 145px;
  display: flex;
  justify-content: center;
}
.image {
  width: 100%;
  height: 100%;
  display: flex;

  flex-direction: column;
}
.image-wrap {
  border: 1px solid rgb(195, 195, 195);
  box-shadow: 1px 1px 10px rgba(176, 176, 176, 0.578);

  padding: 6px;

  display: flex;
  flex-wrap: wrap;
  gap: 12px;

  overflow-x: auto;

  height: 125px;
}
.image-card {
  position: relative;
}
.image-wrap img {
  width: 100%;
  height: 100%;
}
.upload-img {
  width: 100px;
  height: 100px;
  border: 1px solid gray;
  border-radius: 12px;
  overflow: hidden;
}
.delete-btn {
  position: absolute;
  top: -5px;
  right: -5px;
  width: 20px;
  height: 20px;
  background-color: #ffffff;
  border-radius: 16px;

  cursor: pointer;
}
.product-title-total {
  display: flex;
  height: 100%;
}
.product-Info-total {
  width: 100px;
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
  padding: 35px;
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
