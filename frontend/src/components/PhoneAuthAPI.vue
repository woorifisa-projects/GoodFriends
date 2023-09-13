<template>
  <div class="total">
    <div v-show="!countdownVisible">
      <input class="phone-btn phoneAuth-btn" type="button" @click="sendPhone" :value="props.text" />
    </div>
    <div class="countdown-box" v-show="countdownVisible">
      <p>재시도 : {{ countdown }}</p>
    </div>
    <div class="check_total" v-show="isButtonVisible">
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
  if (formatAfter === undefined || formatAfter === '') {
    alert('휴대폰 번호를 입력하세요.');
    return;
  }
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
  // 인증번호 전송, 인증번호 전송 클릭하면 입력한 번호로 인증번호가 전송되는 API
  const sendPhoneAuth = await profileAPI.sendPhoneAuth(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN || ''),
    {
      recipientPhoneNumber: formatAfter?.toString() || ''
    }
  );
};

const checkPhone = async () => {
  // 인증번호 확인
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
