<template>
  <DefaultMyPage>
    <div class="profile_main">
      <div v-show="Number(router.params.id) === store.id" class="btn_wrap">
        <button>{{ PROFILE.WITHDRAWAL }}</button>
        <button @click="onClickEdit">{{ PROFILE.EDIT }}</button>
      </div>
      <div class="profile_detail_wrap">
        <div class="profile_detail">
          <div class="item">
            <label>{{ PROFILE.EMAIL }}</label>
            <input v-model="store.email" type="email" disabled />
          </div>

          <div class="item">
            <label>{{ PROFILE.NICKNAME }}</label>
            <input type="text" v-model="userInputInfo.nickName" :disabled="isDisabled" />
          </div>
          <div class="item">
            <label>{{ PROFILE.PHONE_NUMBER }}</label>
            <input
              type="text"
              :value="userInputInfo.mobileNumber"
              :disabled="isDisabled"
              @change="onChangePhoneNumber"
              @input="onInputPhoneNumber"
              :placeholder="PLACEHOLDER.PHONE"
            />
          </div>
          <div class="item">
            <label>{{ PROFILE.ADDRESS }}</label>
            <input
              type="text"
              v-model="userInputInfo.address"
              placeholder="주소를 입력해주세요"
              disabled
            />
            <AddressAPI v-show="!isDisabled" @click="searchAddress" :text="PROFILE.GET_ADDRESS" />
          </div>
        </div>
      </div>
    </div>
  </DefaultMyPage>
</template>

<script setup lang="ts">
import { ALERT, PLACEHOLDER, PROFILE } from '@/constants/strings/profile';
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import { ref, watchEffect } from 'vue';
import { checkPhoneNumber } from '@/utils/validation';
import { phoneNumberFormat } from '@/utils/format';
import AddressAPI from '@/components/AddressAPI.vue';
import { useRoute } from 'vue-router';
import { useUserInfoStore } from '@/stores/userInfo';
import profileAPI from '@/apis/user/profile';
import { useLoadingStore } from '@/stores/loading';

const router = useRoute();
const store = useUserInfoStore();
const loadingStore = useLoadingStore();
const user = ref({
  ...store.getProfile()
});
const userInputInfo = ref({
  nickName: user.value.nickName,
  mobileNumber: user.value.mobileNumber,
  address: user.value.address
});
const isDisabled = ref(true);
const searchAddress = (data: string) => {
  userInputInfo.value.address = data;
};
const onClickEdit = async () => {
  if (isDisabled.value) {
    isDisabled.value = false;
  } else if (!isDisabled.value) {
    if (!checkPhoneNumber(userInputInfo.value.mobileNumber)) {
      alert(ALERT.PHONE);
      return;
    }
    if (!checkUserName(userInputInfo.value.nickName)) {
      alert(ALERT.NAME);
      // TODO: 중복일 경우 다른 메시지로 알려주기
      return;
    }
    // TODO: API
    loadingStore.setLoading(true);
    const res = await profileAPI.editProfile(store.accessToken, {
      ...userInputInfo.value,
      email: store.email
    });
    if (res.isSuccess) {
      user.value = { ...userInputInfo.value, ...user.value };
      isDisabled.value = true;
    } else {
      alert(res.message);
    }
    loadingStore.setLoading(false);
  }
};

const onChangePhoneNumber = (event: Event) => {
  const value = (event.target as HTMLInputElement).value;

  userInputInfo.value.mobileNumber = value.replace(/[^0-9-]/, '');
};
const onInputPhoneNumber = (event: Event) => {
  const target = event.target as HTMLInputElement;
  const number = target.value.match(/\d+/g)?.join('');

  if (!number) return;
  target.value = phoneNumberFormat(number);
};
const checkUserName = (nickName: string) => {
  // TODO: API 중복검사
  if (nickName.length < 2) return false;
  return true;
};

watchEffect(() => {
  user.value = {
    nickName: store.nickName,
    mobileNumber: store.mobileNumber,
    address: store.address
  };
  userInputInfo.value = {
    nickName: store.nickName,
    mobileNumber: store.mobileNumber,
    address: store.address
  };
});
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
