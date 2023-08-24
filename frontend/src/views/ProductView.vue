<template>
  <div class="product-page">
    <div class="product-info">
      <div class="imgs">
        <button @click="onClickBannerBtn('prev')" :disabled="viewImage === 0">
          <span class="material-icons-outlined"> arrow_back_ios </span>
        </button>
        <img :src="product.images[viewImage]" alt="" />
        <button
          @click="onClickBannerBtn('next')"
          :disabled="viewImage === product.images.length - 1"
        >
          <span class="material-icons-outlined"> arrow_forward_ios </span>
        </button>
      </div>
      <div class="info">
        <div v-if="isWriter" class="wrap-btn">
          <button @click="onClickEditBtn">{{ PRODUCT.EDIT }}</button>
          <button @click="onClickDelete">{{ PRODUCT.DELETE }}</button>
        </div>
        <div v-else class="wrap-btn">
          <button @click="onClickReport">{{ PRODUCT.REPORT }}</button>
          <button @click="onClickOrder">{{ PRODUCT.ORDER }}</button>
        </div>
        <div class="detail-info">
          <div class="name">{{ product.title }}~</div>
          <div class="price">{{ product.price }}원</div>
          <div class="category">{{ product.category }}</div>
          <div class="date">{{ PRODUCT.CREATE_AT }}: {{ product.createdAt }}</div>
        </div>
      </div>
    </div>
    <div class="product-user">
      <div class="img">
        <img :src="user.image" alt="" />
      </div>
      <div>{{ user.name }}</div>
      <div>{{ user.gender }}</div>
    </div>
    <div class="product-detail">{{ product.description }}</div>
    <CommonModalVue
      :is-visible="isVisible"
      @updateVisible="(status: boolean) => (isVisible = status)"
    >
      <div class="modal">
        <div class="modal-title">{{ ORDER_MODAL.TIME }}</div>
        <div class="modal-date">
          <div class="modal-text">
            <p v-for="(text, index) in ORDER_MODAL.CONTENTS" :key="index">
              {{ text }}
            </p>
          </div>
          <div class="modal-input">
            <span>{{ ORDER_MODAL.DATE }} </span>
            <input
              type="date"
              :value="dateFormat(wantedDate[0])"
              @change="onChangeDate($event, 0)"
            />
            ~
            <input
              type="date"
              :value="dateFormat(wantedDate[1])"
              @change="onChangeDate($event, 1)"
            />
          </div>
          <div class="modal-input">
            <span>{{ ORDER_MODAL.TIME }} </span>
            <input type="time" />
            ~
            <input type="time" />
          </div>
        </div>
        <div class="modal-requirement">
          <label for="requirement"> {{ ORDER_MODAL.REQUIREMENT }}</label>
          <textarea name="" id="" cols="30" rows="10"></textarea>
        </div>
        <button @click="onClickOrderSubmit">{{ ORDER_MODAL.SUBMIT }}</button>
      </div>
    </CommonModalVue>
  </div>
</template>

<script setup lang="ts">
import router from '@/router';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import image from '@/assets/tmp/images/image.png';
import banner1 from '@/assets/tmp/images/banner1.jpeg';
import { ORDER_MODAL, PRODUCT } from '@/constants/strings/product';
import CommonModalVue from '@/components/CommonModal.vue';
import { dateFormat } from '@/utils/format';
import { compareDate } from '@/utils/date';

const route = useRoute();

const id = ref(route.params.id);
// TODO: 작성자 인지 아닌지 -> API 연결 필요
const isWriter = ref(Math.random() < 0.5);
// TODO: API 연결
const product = ref({
  title: '상품 제목',
  description: '상세 내용',
  createdAt: 'yyyy-mm-dd',
  category: 'category',
  price: 1000,
  images: [image, banner1]
});
const viewImage = ref(0);
// TODO: API 연결
const user = ref({
  image: image,
  name: 'name',
  gender: 'gender'
});
const isVisible = ref(true);
const wantedDate = ref([new Date(), new Date()]);

const onClickBannerBtn = (flag: string) => {
  if (flag === 'next') {
    viewImage.value = viewImage.value + 1;
  } else if (flag === 'prev') {
    viewImage.value -= 1;
  }
};
const onClickEditBtn = () => {
  router.push('/product/edit/' + id.value);
};

const onClickDelete = () => {
  // TODO: delete
};

const onClickReport = () => {
  // TODO: 신고하기
};

const onClickOrder = () => {
  isVisible.value = true;
};

const onChangeDate = (event: Event, index: number) => {
  const date = (event.target as HTMLInputElement).value;
  wantedDate.value[index] = new Date(date);
};
const onClickOrderSubmit = () => {
  console.log(wantedDate.value, new Date());
  const currentDate = new Date();
  if (
    compareDate(currentDate, wantedDate.value[0]) < 0 ||
    compareDate(wantedDate.value[0], wantedDate.value[1]) < 0
  ) {
    alert('날짜를 정확히 입력해주세요');
    return;
  }
  isVisible.value = false;
  // TODO: 주문서 제출
};
</script>

<style scoped>
.product-page {
  width: 100%;
  height: 199%;
}
.product-info {
  display: flex;
  gap: 12px;
  margin-top: 32px;
}
.imgs {
  flex: 1;

  width: 450px;
  height: 450px;

  display: flex;
  align-items: center;
  position: relative;

  overflow: hidden;
  text-align: center;

  margin: auto;

  overflow: hidden;

  border: 1px solid rgba(0, 0, 0, 0.278);
  border-radius: 8px;
}
.imgs > button {
  position: absolute;
  background-color: transparent;
}
.imgs > button:first-child {
  left: 0;
}
.imgs > button:last-child {
  right: 0;
}

.imgs > img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}
.info {
  flex: 2;

  display: flex;
  flex-direction: column;

  border: 1px solid rgba(159, 159, 159, 0.38);

  box-shadow: 1px 1px 10px rgba(150, 150, 150, 0.247);
}
.wrap-btn {
  display: flex;
  justify-content: end;
  gap: 24px;
  padding: 8px;
}
.wrap-btn > button {
  padding: 12px 24px;

  border-radius: 8px;
  border: 1px solid rgb(159, 159, 159);
  box-shadow: 1px 1px 10px rgba(150, 150, 150, 0.247);
}
.detail-info {
  padding: 24px;

  display: flex;
  flex-direction: column;

  gap: 18px;
  font-size: 24px;
  height: 100%;
}
.name {
  font-size: 48px;
  font-weight: 700;
  text-align: center;
  margin-bottom: 24px;
}
.price {
  text-align: end;
  padding-right: 42px;
  font-size: 24px;
  font-weight: 600;
}
.date {
  font-size: 14px;
  text-align: end;
  padding-right: 42px;
}
.category {
  text-align: end;
  padding-right: 42px;
}
.product-user {
  display: flex;
  align-items: center;
  gap: 12px;

  margin-top: 12px;
}
.product-user > .img {
  width: 48px;
  height: 48px;

  border: 1px solid rgba(0, 0, 0, 0.358);
  border-radius: 50%;

  overflow: hidden;
}
.product-user > .img > img {
  width: 100%;
}
.product-detail {
  min-height: 320px;

  border: 1px solid rgba(0, 0, 0, 0.339);

  margin-top: 32px;
  margin-bottom: 42px;
  padding: 24px;
}
.modal {
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  gap: 12px;

  font-size: 24px;
  overflow: auto;
}
.modal > .modal-title {
  width: 100%;
  border-bottom: 1px solid black;
  font-size: 48px;
  font-weight: 700;
}
.modal-date {
  display: flex;
  flex-direction: column;
}
.modal-date > .modal-text {
  margin: 12px 0;
}
.modal-input {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 12px;
  margin-top: 12px;
}
.modal-input > span {
  width: 60px;
}
.modal > .modal-requirement {
  width: 60%;
  display: flex;
  flex-direction: column;
}
.modal-requirement > label {
  font-size: 16px;
  margin-bottom: 6px;
}
.modal-requirement > textarea {
  padding: 12px;
  font-size: 18px;
}
.modal > button {
  margin-top: 12px;
  padding: 12px 18px;

  border: 1px solid rgba(130, 130, 130, 0.732);
  box-shadow: 1px 1px 10px rgba(145, 145, 145, 0.524);
}
.modal > button:active {
  background-color: rgb(219, 219, 219);
}

input[type='date'],
input[type='time'] {
  background-color: skyblue;
  padding: 13px;
  font-family: 'Roboto Mono', monospace;
  color: #ffffff;
  font-size: 16px;
  border: none;
  outline: none;
  border-radius: 5px;
}

@media screen and (max-width: 1023px) {
  .info {
    flex: 1;
  }
}

@media screen and (max-width: 767px) {
  .product-info {
    flex-direction: column;
    align-items: center;
  }
  .imgs {
    width: 350px;
    max-height: 350px;
    overflow: hidden;
  }
  .info {
    width: 100%;
    border: none;
    border-bottom: 1px solid rgba(0, 0, 0, 0.339);
    box-shadow: none;
  }
  .detail-info {
    font-size: 16px;
    gap: 12px;
  }
  .name {
    font-size: 32px;
  }
  .price {
    font-size: 18px;
  }
  .product-user {
    padding-left: 10%;
  }
  .product-detail {
    padding: 0 10%;
    border: none;
    border-top: 1px solid rgba(0, 0, 0, 0.339);
  }
}
</style>
