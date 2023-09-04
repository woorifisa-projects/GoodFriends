<template>
  <div class="form-product">
    <div class="form">
      <div class="input-detail">
        <div class="title detail">
          <input type="text" id="title" placeholder="제목을 입력해주세요" v-model="data.title" />
        </div>
        <div class="price detail">
          <label for="price">{{ PRODUCT.PRICE }}</label>
          <input type="number" id="price" v-model="data.sellPrice" />
        </div>
        <div class="explain detail">
          <label for="explain">{{ PRODUCT.DESCRIPTION }}</label>
          <textarea name="" id="explain" cols="30" rows="10" v-model="data.description"></textarea>
        </div>
        <div class="buttons">
          <button class="remove-btn" v-if="props.type === 'edit'" @click="remove">
            {{ PRODUCT.DELETE }}
          </button>
          <button v-else @click="save">{{ PRODUCT.TEMPORARY }}</button>
          <button class="submit-btn" @click="submit">
            {{ props.type === 'edit' ? PRODUCT.EDIT : PRODUCT.SUBMIT }}
          </button>
        </div>
      </div>
      <div class="select-detail">
        <div class="image">
          <span for="image">{{ PRODUCT.IMAGE }}</span>
          <label for="image">{{ PRODUCT.UPLOAD }}</label>
          <input
            id="image"
            type="file"
            multiple
            accept="image/png, image/gif, image/jpeg"
            @change="uploadImage"
          />
          <div class="image-wrap">
            <div class="image-card" v-for="(img, index) in previewImg" :key="index">
              <div class="upload-img">
                <img :src="img" alt="" />
              </div>
              <div class="delete-btn" @click="onClickDeleteBtn(index)">
                <img src="@/assets/images/delete.png" alt="" />
              </div>
            </div>
          </div>
        </div>
        <div class="category">
          <span for="category">{{ PRODUCT.CATEGORY }}</span>
          <select name="" id="" v-model="data.productCategory">
            <option disabled value="ALL">{{ PRODUCT.PLEASE_SELECT }}</option>
            <option v-for="(category, index) in categories.slice(1)" :key="index" :value="category">
              {{ CATEGORY[category] }}
            </option>
          </select>
        </div>
        <div class="date">
          <span for="date">{{ PRODUCT.CREATE_AT }}</span>
          <input type="date" :value="dateFormat(registerDate)" @change="onChangeDate" disabled />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { dateFormat } from '@/utils/format';
import { uploadFile, urlToFile } from '@/utils/file';
import { useRoute } from 'vue-router';
import { PRODUCT } from '@/constants/strings/product';
import { useUserInfoStore } from '@/stores/userInfo';
import { CATEGORY_LIST } from '@/constants/category';
import { CATEGORY } from '@/constants/category';
import productAPI from '@/apis/user/product';
import type { IEditProduct } from '@/types/product';
import { checkProductValue } from '@/utils/validation';
import { goPageWithReload } from '@/utils/goPage';
import { useLoadingStore } from '@/stores/loading';

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
const id = route.params.id?.toString() || '0';
const categories = CATEGORY_LIST;

const data = ref<IEditProduct>({
  title: '',
  productCategory: 'ALL',
  description: '',
  sellPrice: ''
});

const inputImage = ref<Array<File>>([]);
const previewImg = ref<Array<string>>([]);
const registerDate = ref(new Date());
const store = useUserInfoStore();

const onChangeDate = (event: Event) => {
  const date = (event.target as HTMLInputElement).value;
  registerDate.value = new Date(date);
};

const uploadImage = async (event: Event) => {
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  if (!fileList) return;
  await uploadFile('img', fileList, previewImg.value, 0, inputImage.value);
};

const onClickDeleteBtn = (index: number) => {
  previewImg.value.splice(index, 1);
  inputImage.value.splice(index, 1);
};

const submit = async () => {
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

  formData.append(
    'request',
    new Blob([JSON.stringify(data.value)], {
      type: 'application/json'
    })
  );
  if (props.type === 'edit') {
    const res = await productAPI.editProduct(store.accessToken, id, formData);
    if (!res.isSuccess) {
      alert(res.message);
      loadingStore.setLoading(false);

      return;
    }
    goPageWithReload('product/' + id);
    loadingStore.setLoading(false);
  } else if (props.type === 'add') {
    const res = await productAPI.postProduct(store.accessToken, formData);
    if (!res.isSuccess) {
      alert(res.message);
      loadingStore.setLoading(false);
      return;
    }
    goPageWithReload();
    loadingStore.setLoading(false);
  }
};

const save = (e: Event) => {
  if (props.type === 'add') {
    // TODO: add 관련 API 호출
    console.log('임시저장 버튼 클릭');
  }
};

const remove = async () => {
  console.log('!@3');
  if (props.type === 'edit') {
    // TODO: 현재 게시물 삭제 API 호출
    console.log('삭제 버튼 클릭');
    loadingStore.setLoading(true);
    const res = await productAPI.deleteProduct(store.accessToken, id);
    if (res.isSuccess) {
      console.log('success');
      goPageWithReload('');
      loadingStore.setLoading(true);
    } else {
      alert(res.message);
      loadingStore.setLoading(true);
    }
  }
};

onMounted(async () => {
  if (props.type === 'add') {
    return;
  }
  loadingStore.setLoading(true);

  const res = await productAPI.getEditProduct(store.accessToken, id);
  if (res.data === undefined || !res.isSuccess) {
    loadingStore.setLoading(false);
    return;
  }
  const resData = res.data;
  const imageUrl = resData.imageUrls || [];

  previewImg.value = imageUrl;

  const images = await urlToFile(imageUrl);

  if (images === null) {
    alert('이미지를 불러오는 중 오류가 발생했습니다');
    loadingStore.setLoading(false);
    goPageWithReload('product/' + id);
    return;
  }

  images.forEach(async (promiseFile: Promise<File | null>) => {
    const file = await promiseFile;
    if (!file) throw new Error('파일 변환 과정중 오류 발생');
    inputImage.value.push(file);
    loadingStore.setLoading(false);
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
.form-product {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;

  margin-top: 48px;
}
.form {
  display: flex;
  gap: 12px;
  width: 100%;
  height: 100%;
}

.input-detail {
  flex: 2;

  background-color: white;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  display: flex;
  flex-direction: column;

  padding: 20px;

  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}

.input-detail input,
.input-detail textarea {
  font-size: 24px;
  border: none;
  border-radius: 8px;
  padding: 12px;
}
.input-detail textarea {
  border: 1px solid rgb(200, 200, 200);
}
.input-detail label {
  font-size: 18px;
}

.input-detail > .detail {
  padding: 12px;
  display: flex;
  flex-direction: column;
}

.input-detail > .title {
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;

  background-color: var(--edit-product-point-bg);

  border: 1px solid lightgray;
  border-bottom: none;
}

.input-detail > .price {
  background-color: var(--edit-product-point-bg);
  border: 1px solid lightgray;
  border-top: none;
  flex-direction: row;
  align-items: center;
  justify-content: end;
  gap: 36px;
}

.input-detail > .price > input {
  width: 400px;
}

.input-detail > .explain {
  border: 1px solid lightgray;
  border-top: none;

  border-bottom-left-radius: 16px;
  border-bottom-right-radius: 16px;
}
.select-detail {
  flex: 1;

  display: flex;
  flex-direction: column;
  gap: 24px;

  height: fit-content;

  box-sizing: border-box;

  background-color: white;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;

  padding: 20px;
}
.select-detail > div {
  display: flex;
  flex-direction: column;

  padding: 24px;
}
.select-detail span {
  border-bottom: 1px solid black;
  font-size: 24px;
  font-family: 'LINESeedKR-Bd';
}

.select-detail input,
.select-detail select {
  font-size: 18px;
  margin-top: 12px;
}
.image {
  width: 100%;
}
.image label {
  margin-top: 12px;
  display: inline-block;

  width: fit-content;
  padding: 10px 15px;

  color: #ffffff;
  background-color: var(--edit-product-btn-1-bg);
  border: 1px solid #ebebeb;
  border-radius: 0.25em;

  cursor: pointer;
}
.image-wrap {
  border: 1px solid rgb(195, 195, 195);
  box-shadow: 1px 1px 10px rgba(176, 176, 176, 0.578);

  margin-top: 8px;
  padding: 6px;

  display: flex;
  flex-wrap: wrap;
  gap: 12px;

  overflow-x: auto;

  height: 140px;
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

.delete-btn > img {
  width: 100%;
}
.buttons {
  display: flex;
  justify-content: space-around;
  margin-top: 24px;
  gap: 24px;
  padding: 0 32px;
}

.buttons > button {
  flex: 1;
  font-size: 24px;
  padding: 8px 24px;
  border: 1px solid rgb(110, 110, 110);
  border-radius: 12px;
}

.remove-btn {
  color: #ffffff;
  background-color: var(--edit-product-remove-btn-bg);
}

.submit-btn {
  color: #ffffff;
  background-color: var(--edit-product-btn-1-bg);
}

input[type='number']::-webkit-outer-spin-button,
input[type='number']::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type='file'] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

@media screen and (max-width: 1023px) {
  .form-product {
    margin-top: 64px;
  }
  .input-detail input,
  .input-detail textarea {
    font-size: 18px;
  }

  .input-detail label {
    font-size: 14px;
  }

  .buttons > button {
    font-size: 18px;
  }

  .select-detail span {
    border-bottom: 1px solid black;
    font-size: 18px;
  }

  .select-detail input,
  .select-detail select {
    font-size: 14px;
    margin-top: 10px;
  }

  .select-detail > div {
    padding: 12px;
  }
}

@media screen and (max-width: 767px) {
  .form {
    flex-direction: column-reverse;
  }
  .image-wrap {
    height: 120px;
  }
}
</style>
