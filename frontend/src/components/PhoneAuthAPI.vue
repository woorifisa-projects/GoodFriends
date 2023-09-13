<template>
  <div class="total">
    <div v-show="!countdownVisible">
      <input
        :disabled="isAuth"
        class="phone-btn phoneAuth-btn"
        type="button"
        @click="sendPhone"
        :value="text"
      />
    </div>
    <div class="countdown-box" v-show="countdownVisible">
      <p>재시도 : {{ countdown }}</p>
    </div>
    <div class="check_total" v-show="countdownVisible">
      <input class="phoneAuthNum" v-model="input_phoneAuthNum" />
      <input
        class="phone-btn check_phoneAuthNum"
        type="button"
        @click="checkPhone"
        value="인증확인"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import profileAPI from '@/apis/user/profile';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { PROFILE } from '@/constants/strings/profile';
import { ref } from 'vue';

const props = defineProps({
  phoneNum: {
    type: String
  }
});

const emits = defineEmits(['click']);
const text = ref(PROFILE.SEND_PHONE_AUTH);
const countdownVisible = ref(false);
const isAuth = ref(false);
let countdown = ref(120);

const input_phoneAuthNum = ref();
const timer = ref(0);
const sendPhone = async () => {
  const formatBefore = props.phoneNum;
  const formatAfter = formatBefore?.replace(/-/g, '');
  if (formatAfter === undefined || formatAfter === '') {
    alert('휴대폰 번호를 입력하세요.');
    return;
  }
  countdownVisible.value = true;

  timer.value = setInterval(() => {
    countdown.value--;
    if (countdown.value <= 0) {
      clearInterval(timer.value);
      countdownVisible.value = false;
      countdown.value = 60;
    }
  }, 1000);
  // 인증번호 전송, 인증번호 전송 클릭하면 입력한 번호로 인증번호가 전송되는 API
  // await profileAPI.sendPhoneAuth(localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN || ''), {
  //   recipientPhoneNumber: formatAfter?.toString() || ''
  // });
};

const checkPhone = async () => {
  // 인증번호 확인
  // const checkPhoneAuth = await profileAPI.checkPhoneAuth(
  //   localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN || ''),
  //   input_phoneAuthNum.value
  // );
  const checkPhoneAuth = { message: '인증완료' };
  if (checkPhoneAuth.message === '인증완료') {
    alert('인증 성공하였습니다.');
    clearInterval(timer.value);
    countdownVisible.value = false;
    text.value = '인증완료';
    isAuth.value = true;
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
  justify-content: left;
  align-items: center;
  gap: 30px;
}
.check_total {
  width: 100%;
  height: 100%;
  display: flex;
  gap: 10px;
}
.phoneAuthNum {
  width: 80px;
  font-size: 16px;
  text-align: center;
}

.check_phoneAuthNum {
  width: 100px;
  min-height: 40px;
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
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}

.phone-btn {
  width: fit-content;
  padding: 12px 24px;
  border: none;
  background-color: white;
  border: 1px solid #888;
  border-radius: 6px;

  cursor: pointer;
}
</style>
