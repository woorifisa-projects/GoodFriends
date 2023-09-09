<template>
  <CommonModalVue :is-visible="props.isVisible" @updateVisible="emits('update:isVisible', $event)">
    <div class="modal" :class="isClickSubmit ? `modal-hidden` : `view`">
      <div class="modal-title">{{ ORDER_MODAL.TITLE }}</div>
      <div class="close-btn">
        <button @click="emits('update:isVisible', false)">
          <span class="material-icons-outlined"> cancel </span>
        </button>
      </div>
      <div class="modal-date">
        <div class="modal-text">
          <p v-for="(text, index) in ORDER_MODAL.CONTENTS" :key="index">
            {{ text }}
          </p>
        </div>
        <div class="modal-input">
          <span>{{ ORDER_MODAL.DATE }} </span>
          <input type="date" :value="dateFormat(wantedDate[0])" @change="onChangeDate($event, 0)" />
          ~
          <input type="date" :value="dateFormat(wantedDate[1])" @change="onChangeDate($event, 1)" />
        </div>
        <div class="modal-input">
          <span>{{ ORDER_MODAL.TIME }} </span>
          <input
            type="text"
            :value="wantedTime[0]"
            @change="onChangeTime($event, 0)"
            placeholder="00:00"
          />
          ~
          <input
            type="text"
            :value="wantedTime[1]"
            @change="onChangeTime($event, 1)"
            placeholder="00:00"
          />
        </div>
      </div>
      <div class="modal-requirement">
        <label for="requirement"> {{ ORDER_MODAL.REQUIREMENT }}</label>
        <textarea v-model="requirement" name="" id="" cols="30" rows="10"></textarea>
        <div class="max-length">{{ requirement.length }} / {{ maxLength }}</div>
      </div>
      <button @click="checkInput">{{ ORDER_MODAL.SUBMIT }}</button>
    </div>
    <div class="confirm-window" :class="isClickSubmit ? `view` : `hidden`">
      <p>정말 제출하시겠습니까?</p>
      <p>주문서는 수정이 불가능합니다.</p>
      <div class="confirm-btn-wrap">
        <button @click="isClickSubmit = false">아니요</button>
        <button @click="onClickSubmit">네</button>
      </div>
    </div>
  </CommonModalVue>
</template>

<script setup lang="ts">
import CommonModalVue from '@/components/CommonModal.vue';
import { ORDER_MODAL } from '@/constants/strings/product';
import { dateFormat } from '@/utils/format';
import { checkTime, compareDate, compareTime } from '@/utils/date';
import { ref, watchEffect } from 'vue';
import type { IPostOrder } from '@/types/order';
import orderAPI from '@/apis/user/order';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { useLoadingStore } from '@/stores/loading';

const props = defineProps({
  productId: {
    type: String,
    required: true
  },
  isVisible: {
    type: Boolean,
    required: true
  }
});

const emits = defineEmits(['update:isVisible']);

const wantedDate = ref([new Date(), new Date()]);
const wantedTime = ref(['', '']);
const requirement = ref('');
const maxLength = ref(200);
const isClickSubmit = ref(false);
const loadingStore = useLoadingStore();
const isSubmit = ref(true);

const checkInput = () => {
  const currentDate = new Date();
  if (
    compareDate(currentDate, wantedDate.value[0]) < 0 ||
    compareDate(wantedDate.value[0], wantedDate.value[1]) < 0
  ) {
    alert(ORDER_MODAL.ALERT_DATE);
    return;
  }
  if (
    !checkTime(wantedTime.value[0]) ||
    !checkTime(wantedTime.value[1]) ||
    compareTime(wantedTime.value[0], wantedTime.value[1]) < 0
  ) {
    alert(ORDER_MODAL.ALERT_TIME);
    return;
  }
  isClickSubmit.value = true;
};

const onClickSubmit = async () => {
  const body: IPostOrder = {
    productId: +props.productId,
    possibleDateStart: dateFormat(wantedDate.value[0]),
    possibleDateEnd: dateFormat(wantedDate.value[1]),
    possibleTimeStart: wantedTime.value[0],
    possibleTimeEnd: wantedTime.value[1],
    requirements: requirement.value
  };
  loadingStore.setLoading(true);
  const res = await orderAPI.postOrder(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    body
  );
  loadingStore.setLoading(false);

  if (!res.isSuccess) {
    alert(res.message);
    return;
  }

  isSubmit.value = true;
  isClickSubmit.value = false;
  emits('update:isVisible', false);
};
const onChangeDate = (event: Event, index: number) => {
  const date = (event.target as HTMLInputElement).value;
  wantedDate.value[index] = new Date(date);
};

const onChangeTime = (event: Event, index: number) => {
  const time = (event.target as HTMLInputElement).value.replace(/\D/g, '');
  console.log(time);
  if (time.length === 0) return '';

  const hours = time.slice(0, 2);
  const minute = time.slice(2, 4);

  wantedTime.value[index] = `0${hours}`.slice(-2) + ':' + `${minute}00`.slice(0, 2);
};

watchEffect(() => {
  requirement.value = requirement.value.slice(0, maxLength.value);
});
</script>

<style scoped>
.modal {
  width: 50vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  gap: 12px;

  font-size: 24px;
  overflow: hidden;
  position: relative;
  padding: 32px;
}
.modal-hidden {
  /* filter: brightness(90%); */
  filter: blur(4px);
}
.view {
  visibility: visible;
}
.hidden {
  visibility: hidden;
}
.modal > .modal-title {
  width: 100%;
  border-bottom: 1px solid black;
  font-size: 36px;
  font-family: 'LINESeedKR-Bd';
}
.close-btn {
  position: absolute;
  right: 20px;
  top: 20px;
}
.close-btn > button > span {
  font-size: 32px;
}

.modal-date {
  width: 60%;
  display: flex;
  flex-direction: column;
}
.modal-date > .modal-text {
  margin: 12px 0;
  font-size: 24px;
}
.modal-input {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 12px;
  margin-top: 12px;
}
.modal-input > span {
  font-family: 'LINESeedKR-Rg';
  width: 60px;
  font-size: 24px;
}
.modal > .modal-requirement {
  width: 60%;
  display: flex;
  flex-direction: column;
}
.modal-requirement > label {
  font-size: 18px;
  margin-bottom: 6px;
  font-family: 'LINESeedKR-Bd';
}
.modal-requirement > textarea {
  padding: 12px;
  font-size: 18px;
  font-family: 'LINESeedKR-Rg';
  height: 200px;
}
.modal > button {
  padding: 12px 18px;
  font-family: 'LINESeedKR-Bd';

  border: 1px solid rgba(130, 130, 130, 0.732);
  border-radius: 6px;
  transition: all 0.3s ease;

  font-size: 18px;

  margin-bottom: 12px;
  background-color: #fcc61f;
}
.modal > button:active {
  background-color: rgb(219, 219, 219);
}
.modal > button:hover {
  transform: scale(1.05);
}
.max-length {
  font-size: 12px;
  text-align: end;
}
.confirm-window {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  padding: 10px 20px;
  font-size: 24px;
  background-color: white;
  border: 1px solid #88888831;
  border-radius: 7px;
}
.confirm-btn-wrap {
  margin-top: 12px;
  display: flex;
  gap: 24px;
  justify-content: space-around;
}
.confirm-btn-wrap > button {
  border: 1px solid rgb(240, 240, 240);
  padding: 8px 18px;
  font-size: 18px;
  border-radius: 12px;
  flex: 1;
  transition: transform 0.3s ease;
}
.confirm-btn-wrap > button:hover {
  transform: scale(1.03);
}
.confirm-btn-wrap > button:last-child {
  background-color: var(--category-item-point-bg);
}

input[type='date'],
input[type='text'] {
  /* background-color: #fcc61f; */
  padding: 13px;
  color: #000;
  font-size: 16px;
  font-family: 'LINESeedKR-Rg';
  border: 1px solid black;
  outline: none;
  border-radius: 5px;
}
input[type='text'] {
  width: 100px;
}

@media screen and (max-width: 1200px) {
  .modal {
    width: 90vw;
  }
}

@media screen and (max-width: 767px) {
  .modal {
    width: 100vw;
    height: 90vh;
  }
}
</style>
