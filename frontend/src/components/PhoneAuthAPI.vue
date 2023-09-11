<template>
  <div class="total">
    <div v-show="!countdownVisible">
      <input class="phoneAuth-btn" type="button" @click="sendPhone" :value="props.text" />
    </div>
    <div class="countdown-box" v-show="countdownVisible">
      <p>재시도 : {{ countdown }}</p>
    </div>
    <div class="check_total" v-show="isButtonVisible">
      <input class="phoneAuthNum" v-model="input_phoneAuthNum" />
      <input class="check_phoneAuthNum" type="button" @click="checkPhone" value="인증확인" />
    </div>
  </div>
</template>

<script setup lang="ts">
import profileAPI from '@/apis/user/profile';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { ref } from 'vue';

const props = defineProps({
  text: {
    type: String
  },
  phoneNum: {
    type: String
  }
});

const emits = defineEmits(['click']);

const isButtonVisible = ref(false);
const countdownVisible = ref(false);
let countdown = ref(60);

const input_phoneAuthNum = ref();

const sendPhone = async () => {
  const formatBefore = props.phoneNum;
  const formatAfter = formatBefore?.replace(/-/g, '');
  isButtonVisible.value = true;
  countdownVisible.value = true;

  const timer = setInterval(() => {
    countdown.value--;
    if (countdown.value <= 0) {
      clearInterval(timer);
      countdownVisible.value = false;
      countdown.value = 60;
    }
  }, 1000);
  const sendPhoneAuth = await profileAPI.sendPhoneAuth(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN || ''),
    {
      recipientPhoneNumber: formatAfter?.toString() || ''
    }
  );
};

const checkPhone = async () => {
  const checkPhoneAuth = await profileAPI.checkPhoneAuth(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN || ''),
    input_phoneAuthNum.value
  );
  if (checkPhoneAuth.message === '인증완료') {
    alert('인증 성공하였습니다.');
    emits('click', true);
  } else {
    alert('인증 실패하였습니다.');
    emits('click', false);
  }
};
</script>

<style scoped>
.total {
  display: flex;
  width: 70%;
  height: 40%;
  gap: 30px;
}
.address-btn {
  width: 100%;
  padding: 12px 24px;
  border: none;
  background-color: white;
  border: 1px solid #888;
  border-radius: 6px;

  cursor: pointer;
}
.check_total {
  width: 100%;
  height: 100%;
  display: flex;
  gap: 10px;
}
.phoneAuthNum {
  width: 50px;
  height: 40px;
}

.check_phoneAuthNum {
  width: 100px;
  height: 40px;
}

.phoneAuth-btn {
  width: 130px;
  height: 40px;
}
.countdown-box {
  width: 130px;
  height: 40px;
  font-family: 'LINESeedKR-Bd';
  text-align: center;
  background-color: #888;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
