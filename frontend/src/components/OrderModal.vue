<template>
  <CommonModalVue :is-visible="props.isVisible" @updateVisible="emits('update:isVisible', $event)">
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
        <textarea name="" id="" cols="30" rows="10"></textarea>
      </div>
      <button @click="onClickOrderSubmit">{{ ORDER_MODAL.SUBMIT }}</button>
    </div>
  </CommonModalVue>
</template>

<script setup lang="ts">
import CommonModalVue from '@/components/CommonModal.vue';
import { ORDER_MODAL } from '@/constants/strings/product';
import { dateFormat } from '@/utils/format';
import { checkTime, compareDate, compareTime } from '@/utils/date';
import { ref } from 'vue';

const props = defineProps({
  productId: {
    type: Number,
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

const onClickOrderSubmit = () => {
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
    console.log(checkTime(wantedTime.value[0]));
    alert(ORDER_MODAL.ALERT_TIME);
    return;
  }
  emits('update:isVisible', false);
  // TODO: 주문서 제출
};

const onChangeDate = (event: Event, index: number) => {
  const date = (event.target as HTMLInputElement).value;
  wantedDate.value[index] = new Date(date);
};

const onChangeTime = (event: Event, index: number) => {
  const time = (event.target as HTMLInputElement).value;

  const hours = time.slice(0, 2);
  const minute = time.slice(2, 4);

  wantedTime.value[index] = hours + ':' + minute;
};
</script>

<style scoped>
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
input[type='text'] {
  background-color: skyblue;
  padding: 13px;
  font-family: 'Roboto Mono', monospace;
  color: #ffffff;
  font-size: 16px;
  border: none;
  outline: none;
  border-radius: 5px;
}
input[type='text'] {
  width: 100px;
}
</style>
