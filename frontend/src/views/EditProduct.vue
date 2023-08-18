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
          <button>임시저장</button>
          <button>등록하기</button>
        </div>
      </div>
      <div class="select-detail">
        <div class="image">
          <label for="image">이미지</label>
          <input type="file" multiple accept="image/png, image/gif, image/jpeg" @change="test" />
          <div class="image-wrap">
            <div class="img" v-for="(img, index) in previewImg" :key="index">
              <img :src="img.toString()" alt="" />
            </div>
          </div>
        </div>
        <div class="category">
          <label for="category">카테고리</label>
          <select name="" id="" v-model="selectedCategory">
            <option v-for="category in categories" :key="category.id" :value="category.name">
              {{ category.name }}
            </option>
          </select>
        </div>
        <div class="date">
          <label for="date">등록 날짜</label>
          <input type="date" />
        </div>
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
    id: 0,
    name: 'null'
  },
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
const selectedCategory = ref(0);
const inputImage = ref<Array<File>>([]);
const previewImg = ref<Array<string>>([]);

const test = (event: Event) => {
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  if (!fileList) return;
  inputImage.value.push(...fileList);

  Promise.all(
    inputImage.value.map((img) => {
      return new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.addEventListener('load', (e) => {
          if (!e.target?.result) return;
          resolve(e.target?.result?.toString());
        });
        reader.addEventListener('error', reject);
        // TODO: 이미지 유효성 검사
        reader.readAsDataURL(img);
      })
        .then((res) => {
          previewImg.value.push(res);
        })
        .catch((err) => {
          // TODO: error 처리
          console.log('err');
        });
    })
  );
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
.select-detail label {
  border-bottom: 1px solid black;
  font-size: 24px;
}

.select-detail input,
.select-detail select {
  font-size: 18px;
  margin-top: 12px;
}

.buttons {
  background-color: lightgreen;
}

.buttons > button {
  background-color: transparent;
}

input[type='number']::-webkit-outer-spin-button,
input[type='number']::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
