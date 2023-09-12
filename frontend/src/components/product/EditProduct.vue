<template>
  <div class="form-product">
    <div class="form">
      <div class="input-detail box">
        <div class="title-price">
          <div>
            <label for="">{{ LABEL.TITLE_LABEL }}</label>
            <input
              type="text"
              id="title"
              :placeholder="PLACEHOLDER.TITLE_INPUT"
              v-model="data.title"
            />
          </div>

          <div>
            <label for="">{{ LABEL.PRICE_LABEL }}</label>
            <input
              onfocus="this.select()"
              type="number"
              :placeholder="PLACEHOLDER.PRICE_INPUT"
              id="price"
              v-model="data.sellPrice"
            />
          </div>
        </div>

        <div class="explain detail">
          <label for="explain">{{ LABEL.DESCRIPTION_LABEL }}</label>
          <textarea name="" id="explain" cols="30" rows="10" v-model="data.description"></textarea>
          <div class="text-length">{{ data.description.length }}/{{ maxLength }}</div>
        </div>
        <div class="buttons input-buttons">
          <button class="remove-btn" v-if="props.type === 'edit'" @click="remove">
            {{ PRODUCT.DELETE }}
          </button>
          <button v-else @click="save">{{ PRODUCT.TEMPORARY }}</button>
          <button class="submit-btn" @click="submit">
            {{ props.type === 'edit' ? PRODUCT.EDIT : PRODUCT.SUBMIT }}
          </button>
        </div>
      </div>
      <div class="select-detail box">
        <div class="category">
          <span for="category">{{ PRODUCT.CATEGORY }}</span>
          <select name="" id="" v-model="data.productCategory">
            <option disabled value="ALL">{{ SELECT.CATEGORY_SELECT }}</option>
            <option v-for="(category, index) in categories.slice(1)" :key="index" :value="category">
              {{ CATEGORY[category] }}
            </option>
          </select>
        </div>
        <div class="image">
          <span for="image">{{ PRODUCT.IMAGE }}</span>
          <div class="image-label">
            <label for="image">
              {{ LABEL.FILE_LABEL }}
            </label>
            <div class="image-length">{{ inputImage.length }}/{{ maxImage }}</div>
          </div>
          <input
            id="image"
            type="file"
            multiple
            accept="image/png, image/jpeg"
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
        <div class="buttons select-buttons">
          <button class="remove-btn" v-if="props.type === 'edit'" @click="remove">
            {{ PRODUCT.DELETE }}
          </button>
          <button v-else @click="save">{{ PRODUCT.TEMPORARY }}</button>
          <button class="submit-btn" @click="submit">
            {{ props.type === 'edit' ? PRODUCT.EDIT : PRODUCT.SUBMIT }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from 'vue';
import { uploadFile, urlToFile } from '@/utils/file';
import { useRoute } from 'vue-router';
import { PRODUCT } from '@/constants/strings/product';
import { useUserInfoStore } from '@/stores/userInfo';
import { CATEGORY_LIST } from '@/constants/category';
import { CATEGORY } from '@/constants/category';
import productAPI from '@/apis/user/product';
import { checkProductValue } from '@/utils/validation';
import { goPageWithReload } from '@/utils/goPage';
import { useLoadingStore } from '@/stores/loading';
import router from '@/router';
import type { IPostProduct } from '@/types/product';
import type { IStringToFunction } from '@/types/dynamic';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { LABEL, PLACEHOLDER, SELECT } from '@/constants/strings/defaultInput';

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
const maxLength = ref(200);
const maxImage = ref(10);

const data = ref<IPostProduct>({
  title: '',
  productCategory: 'ALL',
  description: '',
  sellPrice: 0
});

const inputImage = ref<Array<File>>([]);
const previewImg = ref<Array<string>>([]);
const store = useUserInfoStore();

const uploadImage = async (event: Event) => {
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  console.log(fileList);
  if (!fileList) return;
  await uploadFile('img', fileList, previewImg.value, 0, inputImage.value);
  if (inputImage.value.length > maxImage.value) {
    previewImg.value.splice(maxImage.value);
    inputImage.value.splice(maxImage.value);
    alert(`최대 ${maxImage.value}까지 가능`);
  }
};

const onClickDeleteBtn = (index: number) => {
  previewImg.value.splice(index, 1);
  inputImage.value.splice(index, 1);
};

const submit = async () => {
  // 모든 값들이 존재 하는지 체크
  const checkData = checkProductValue(data.value);
  if (!checkData.isSuccess) {
    if (checkData.type === 'title') alert('제목을 올바르게 입력해주세요');
    if (checkData.type === 'price') alert('가격을 입력해주세요.');
    if (checkData.type === 'description') alert('설명을 10자 이상 입력해주세요');
    if (checkData.type === 'category') alert('상품 카테고리를 선택해주세요.');
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

  const submit: IStringToFunction = {
    edit: async () => {
      return await productAPI.editProduct(
        localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
        id,
        formData
      );
    },
    add: async () => {
      return await productAPI.postProduct(
        localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
        formData
      );
    }
  };

  const res = await submit[props.type]();
  loadingStore.setLoading(false);
  if (!res.isSuccess) {
    alert(res.message);
    if (props.type === 'add' && res.code === 403) {
      router.push('/profile/' + store.id);
      return;
    }
    if (props.type === 'edit' && res.code !== 403 && res.code !== 404) return;
  }
  goPageWithReload();
};

const save = (e: Event) => {
  if (props.type === 'edit') {
    return;
  }

  // TODO: add 관련 API 호출
  console.log('임시저장 버튼 클릭', e);
};

const remove = async () => {
  if (props.type === 'add') return;

  loadingStore.setLoading(true);
  const res = await productAPI.deleteProduct(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    id
  );
  loadingStore.setLoading(false);
  if (res.isSuccess) {
    goPageWithReload('');
    return;
  }
  alert(res.message);
  if (res.code === 403) {
    goPageWithReload('');
    return;
  }
};

watchEffect(() => {
  data.value.description = data.value.description.slice(0, maxLength.value);
});

onMounted(async () => {
  if (props.type === 'add') return;

  loadingStore.setLoading(true);

  const res = await productAPI.getEditProduct(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    id
  );

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
    goPageWithReload('product/' + id);
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
.form-product {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 750px;

  margin: 64px 0;
}
.form > div {
  border-radius: 6px;
}
.form {
  display: flex;
  gap: 12px;
  width: 100%;
  height: 100%;
}

.input-detail {
  flex: 3;
  background-color: white;

  display: flex;
  flex-direction: column;

  padding: 20px;
}

.input-detail input,
.input-detail textarea {
  font-size: 24px;
  border: none;
  border-radius: 6px;
  padding: 12px;
  font-family: 'LINESeedKR-Rg';
}
.input-detail textarea {
  border: 1px solid rgb(200, 200, 200);
  height: 450px;
}
.text-length {
  text-align: end;
}
.input-detail label {
  font-family: 'LINESeedKR-Bd';

  font-size: 18px;
}

.input-detail > .detail {
  padding: 12px;
  display: flex;
  flex-direction: column;
}
.title-price {
  position: relative;
  display: flex;
  padding: 12px;
  gap: 12px;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;

  border-bottom: none;
}
.title-price > div > input {
  width: 100%;
  border: 1px solid rgb(200, 200, 200);
}
.title-price > div > label {
  font-family: 'LINESeedKR-Bd';

  position: absolute;
  top: -1px;
  background-color: white;
}
.title-price > div:first-child {
  flex: 3;
}
.title-price > div:last-child {
  flex: 2;
}

.input-detail > .explain {
  border-top: none;

  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
}
.select-detail {
  flex: 1;

  display: flex;
  flex-direction: column;
  gap: 24px;

  height: 100%;

  box-sizing: border-box;

  /* background-color: white; */

  padding: 30px 20px;
}
.select-detail > div {
  display: flex;
  flex-direction: column;

  padding: 24px 20px;
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
  font-family: 'LINESeedKR-Rg';
  cursor: pointer;
}
.select-detail select {
  width: fit-content;
  padding: 6px 10px;
  border: none;
  border: 1px solid rgb(240, 240, 240);
}
.image {
  width: 100%;
}
.image-label {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.image label {
  margin-top: 16px;
  display: inline-block;

  width: fit-content;
  padding: 10px 15px;

  color: #ffffff;
  background-color: var(--color-blue);
  border: 1px solid #ebebeb;
  border-radius: 0.25em;

  cursor: pointer;

  transition: all 0.3s ease;
  font-family: 'LINESeedKR-Bd';
}

.image label:hover {
  transform: scale(1.03);
}
.disabled {
  background-color: red;
}
.image-length {
  text-align: end;
}
.image-wrap {
  border: 1px solid rgb(240, 240, 240);
  box-shadow: 0px 0px 5px rgba(207, 207, 207, 0.446);

  margin-top: 8px;
  padding: 6px;

  display: flex;
  flex-wrap: wrap;
  gap: 12px;

  overflow-x: auto;

  height: 340px;
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
  border-radius: 6px;

  cursor: pointer;
}

.delete-btn > img {
  width: 100%;
}
.buttons {
  display: flex;
  justify-content: space-around;
  margin-top: 16px;
  gap: 24px;
  padding: 0 32px;
}

.buttons > button {
  flex: 1;
  font-size: 24px;
  padding: 8px 24px;
  border: 1px solid rgb(110, 110, 110);
  border-radius: 6px;
  transition: filter 0.3s ease;
  font-family: 'LINESeedKR-Bd';
}

button:hover {
  filter: brightness(90%);
}

.remove-btn {
  color: #ffffff;
  background-color: var(--color-black);
}

.submit-btn {
  color: #ffffff;
  background-color: var(--color-blue);
}
.select-buttons {
  visibility: hidden;
}
.box {
  box-shadow: 0px 0px 5px rgb(240, 240, 240);
  border: 1px solid rgb(240, 240, 240);
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
  .image-wrap {
    height: 120px;
  }
  .form {
    flex-direction: column;
    min-height: 1000px;
  }
  .form-product {
    margin-top: 0px;
    margin-bottom: 0px;
    min-height: fit-content;
    height: fit-content;
    overflow: auto;
  }
  .input-buttons {
    visibility: hidden;
  }
  .select-buttons {
    visibility: visible;
    display: flex;
  }
}
</style>
