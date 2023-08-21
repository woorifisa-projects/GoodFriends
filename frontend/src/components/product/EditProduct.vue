<template>
  <div class="form-product">
    <div class="form">
      <div class="input-detail">
        <div class="title detail">
          <input type="text" id="title" placeholder="제목을 입력해주세요" v-model="inputName" />
        </div>
        <div class="price detail">
          <label for="price">가격</label>
          <input type="number" id="price" v-model="inputPrice" />
        </div>
        <div class="explain detail">
          <label for="explain">상세 설명</label>
          <textarea name="" id="explain" cols="30" rows="10" v-model="inputContent"></textarea>
        </div>
        <div class="buttons">
          <button @click="save">임시저장</button>
          <button @click="submit">등록하기</button>
        </div>
      </div>
      <div class="select-detail">
        <div class="image">
          <span for="image">이미지</span>
          <label for="image">업로드</label>
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
          <span for="category">카테고리</span>
          <select name="" id="" v-model="selectedCategory">
            <option disabled value="0">선택해주세요</option>
            <option v-for="category in categories" :key="category.id" :value="category.id">
              {{ category.name }}
            </option>
          </select>
        </div>
        <div class="date">
          <span for="date">등록 날짜</span>
          <input type="date" :value="dateFormat(registerDate)" @change="onChangeDate" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { category } from '@/types/product';
import { ref } from 'vue';
import { dateFormat } from '@/utils/format';
import { uploadFile } from '@/utils/File';
import { useRoute } from 'vue-router';

const props = defineProps({
  type: {
    type: String,
    validator(value: string) {
      return ['edit', 'add'].includes(value);
    },
    required: true
  }
});

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

const inputPrice = ref(0);
const inputName = ref('');
const inputContent = ref('');
const selectedCategory = ref('0');
const inputImage = ref<Array<File>>([]);
const previewImg = ref<Array<string>>([]);
const registerDate = ref(new Date());

if (props.type === 'edit') {
  const route = useRoute();
  const id = route.params.id;

  // TODO: API 요청 -> price, name, content, category, image, date 가져오기
}
const onChangeDate = (event: Event) => {
  const date = (event.target as HTMLInputElement).value;
  registerDate.value = new Date(date);
};

const uploadImage = (event: Event) => {
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  if (!fileList) return;
  uploadFile('img', fileList, previewImg.value, 0, inputImage.value);
};

const onClickDeleteBtn = (index: number) => {
  previewImg.value.splice(index, 1);
};

const submit = () => {
  if (props.type === 'edit') {
    // TODO: edit 관련 API 호출
    console.log('저장 버튼 클릭(EDIT)');
  } else if (props.type === 'add') {
    // TODO: add 관련 API 호출
    console.log('저장 버튼 클릭(ADD)');
  }
};

const save = (e: Event) => {
  if (props.type === 'edit') {
    // TODO: edit 관련 API 호출
    console.log('임시저장 버튼 클릭(EDIT)');
  } else if (props.type === 'add') {
    // TODO: add 관련 API 호출
    console.log('임시저장 버튼 클릭(ADD)');
  }
};
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
  /* border: 1px solid black; */
  padding: 12px;
  display: flex;
  flex-direction: column;
}

.input-detail > .title {
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;

  background-color: lightblue;

  border: 1px solid lightgray;
  border-bottom: none;
}

.input-detail > .price {
  background-color: lightblue;
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
  background-color: #5467d4;
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
</style>
