<template>
  <DefaultMyPage>
    <div class="profile_main">
      <div class="btn_wrap">
        <button>{{ PROFILE.WITHDRAWAL }}</button>
        <button @click="onClickEdit">{{ PROFILE.EDIT }}</button>
      </div>
      <div class="profile_detail_wrap">
        <div class="profile_detail">
          <div class="item">
            <label>{{ PROFILE.EMAIL }}</label>
            <input v-model="user.email" type="email" disabled />
          </div>

          <div class="item">
            <label>{{ PROFILE.GENDER }}</label>
            <select name="gender" id="gender" v-model="userInputInfo.gender" :disabled="isDisabled">
              <option value="default">default</option>
              <option value="man">man</option>
              <option value="woman">woman</option>
            </select>
          </div>
          <div class="item">
            <label>{{ PROFILE.PHONE_NUMBER }}</label>
            <input
              type="text"
              :value="userInputInfo.phone"
              :disabled="isDisabled"
              @change="onChangePhoneNumber"
              @input="onInputPhoneNumber"
              :placeholder="PLACEHOLDER.PHONE"
            />
          </div>
          <div class="item">
            <label>{{ PROFILE.ADDRESS }}</label>
            <input type="text" v-model="userInputInfo.address" :disabled="isDisabled" />
          </div>
        </div>
      </div>
    </div>
  </DefaultMyPage>
</template>

<script setup lang="ts">
import { ALERT, PLACEHOLDER, PROFILE } from '@/constants/strings/profile';
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import { ref } from 'vue';
import { checkPhoneNumber } from '@/utils/validation';
import { phoneNumberFormat } from '@/utils/format';

const user = ref({
  email: '00@000.com',
  gender: 'woman',
  phone: '010-1234-1234',
  address: '00시00구'
});

const userInputInfo = ref({
  gender: user.value.gender,
  phone: user.value.phone,
  address: user.value.address
});
const isDisabled = ref(true);

const onClickEdit = () => {
  if (isDisabled.value) {
    isDisabled.value = false;
  } else if (!isDisabled.value) {
    if (!checkPhoneNumber(userInputInfo.value.phone)) {
      console.log(userInputInfo.value.phone);
      alert(ALERT.PHONE);
      return;
    }
    // TODO: API

    user.value = { ...userInputInfo.value, ...user.value };
    isDisabled.value = true;
  }
};

const onChangePhoneNumber = (event: Event) => {
  const value = (event.target as HTMLInputElement).value;

  userInputInfo.value.phone = value.replace(/[^0-9-]/, '');
};
const onInputPhoneNumber = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const number = target.value.match(/\d+/g)?.join('');

  if (!number) return;
  target.value = phoneNumberFormat(number);
};
</script>

<style scoped>
.profile_main {
  height: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.btn_wrap {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
  gap: 20px;
}

.btn_wrap > button {
  background-color: var(--profile-btn-1-bg);
  color: var(--profile-btn-1-text);
  padding: 16px 20px;
  border-radius: 8px;
  box-shadow: 1px 1px 6px rgba(0, 0, 0, 0.48);
}

.btn_wrap > button:first-child {
  background-color: var(--profile-btn-2-bg);
  color: var(--profile-btn-2-text);
}

.profile_detail_wrap {
  padding: 0 36px;
  height: 100%;
}

.profile_detail {
  height: 100%;
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.item {
  flex: 1;

  width: 100%;
  border: 1px solid rgb(180, 180, 180);
  border-radius: 8px;
  display: flex;
  overflow: hidden;
  align-items: center;
  gap: 16px;
}

.item > label {
  height: 100%;
  width: 100px;
  padding: 16px;
  background: var(--profile-label-bg);
  color: var(--profile-label-text);
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'LINESeedKR-Bd';
}

.item > input,
.item > select {
  width: 50%;
  display: flex;
  align-items: center;
  border: none;
  border: 1px solid black;
  height: fit-content;
  padding: 12px;
}
.item > input:disabled,
.item > select:disabled {
  background-color: white;
  color: black;
  border: none;
}

@media screen and (max-width: 1023px) {
}

@media screen and (max-width: 767px) {
  .profile_detail_wrap {
    padding: 0 12px;
  }
}
</style>
