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
            <label>{{ PROFILE.ACCOUNT }}</label>
            <select
              name="bank"
              id="bank"
              v-model="userInputInfo.accountType"
              :disabled="isDisabled"
            >
              <option value="default" disabled>{{ SELECT.ACCOUNT_SELECT }}</option>
              <option :value="account" v-for="(account, index) in accountList" :key="index">
                {{ ACCOUNT[account] }}
              </option>
            </select>
            <input
              v-model="userInputInfo.accountNumber"
              type="text"
              :placeholder="PLACEHOLDER.ACCOUNT_INPUT"
              :disabled="isDisabled"
            />
          </div>

          <div class="item">
            <label>{{ PROFILE.NICKNAME }}</label>
            <input
              type="text"
              v-model="userInputInfo.nickName"
              :placeholder="PLACEHOLDER.NICKNAME_INPUT"
              :disabled="isDisabled"
            />
          </div>
          <div class="item">
            <label>{{ PROFILE.PHONE_NUMBER }}</label>
            <input
              type="text"
              :value="userInputInfo.mobileNumber"
              :disabled="isDisabled"
              @change="onChangePhoneNumber"
              @input="onInputPhoneNumber"
              :placeholder="PLACEHOLDER.PHONE_INPUT"
            />
          </div>
          <div class="item">
            <label>{{ PROFILE.ADDRESS }}</label>
            <input
              type="text"
              v-model="userInputInfo.address"
              :placeholder="PLACEHOLDER.ADDRESS_INPUT"
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
import { ALERT, PROFILE } from '@/constants/strings/profile';
import DefaultMyPage from '@/components/profile/DefaultMyPage.vue';
import { onMounted, ref } from 'vue';
import { checkPhoneNumber } from '@/utils/validation';
import { phoneNumberFormat } from '@/utils/format';
import AddressAPI from '@/components/AddressAPI.vue';
import { useRoute } from 'vue-router';
import { useUserInfoStore } from '@/stores/userInfo';
import profileAPI from '@/apis/user/profile';
import { useLoadingStore } from '@/stores/loading';
import type { IProfileEdit } from '@/types/profile';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import { ACCOUNT, ACCOUNT_LIST } from '@/constants/account';
import { goPageWithReload } from '@/utils/goPage';
import { ERROR_MSG } from '@/constants/strings/error';
import { PLACEHOLDER, SELECT } from '@/constants/strings/defaultInput';

const router = useRoute();
const store = useUserInfoStore();
const loadingStore = useLoadingStore();

const userInputInfo = ref<IProfileEdit>({
  nickName: '',
  address: '',
  mobileNumber: '',
  accountType: 'default',
  accountNumber: ''
});
const accountList = ref(ACCOUNT_LIST);
const isDisabled = ref(true);

const searchAddress = (data: string) => {
  userInputInfo.value.address = data;
};
const onClickEdit = async () => {
  if (isDisabled.value) {
    isDisabled.value = false;
    return;
  }
  if (userInputInfo.value.accountNumber.length < 7) {
    alert('계좌번호');
    return;
  }
  console.log(userInputInfo.value.accountType);
  if (userInputInfo.value.accountType === 'default') {
    alert('은행');
    return;
  }
  if (!checkPhoneNumber(userInputInfo.value.mobileNumber)) {
    alert(ALERT.PHONE);
    return;
  }
  if (!checkUserName(userInputInfo.value.nickName)) {
    alert(ALERT.NAME);
    return;
  }

  loadingStore.setLoading(true);
  const res = await profileAPI.editProfile(localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '', {
    ...userInputInfo.value
  });
  loadingStore.setLoading(false);
  if (res.isSuccess) {
    isDisabled.value = true;
    store.setUserNickName(userInputInfo.value.nickName);
  } else {
    alert(res.message);
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
  if (nickName.length < 2) return false;
  return true;
};

onMounted(async () => {
  const res = await profileAPI.getProfile(localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN));
  if (!res.isSuccess || !res.data) {
    alert(ERROR_MSG.LOGIN);
    goPageWithReload();
    return;
  }
  userInputInfo.value = { ...res.data };
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
  padding-right: 48px;
}

.btn_wrap > button {
  cursor: pointer;
  font-family: 'LINESeedKR-Bd';
  font-size: 15px;
  border: 1px solid rgb(240, 240, 240);
  height: 40px;
  width: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 16px 20px;
  border-radius: 12px;
}

.btn_wrap > button:last-child {
  background-color: var(--color-yellow);
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

  width: fit-content;
  border-radius: 8px;
  display: flex;
  overflow: hidden;
  align-items: center;
  gap: 16px;
}

.item > label {
  height: 100%;
  width: 100px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: 'LINESeedKR-Bd';
  margin-right: 24px;
  font-size: 20px;
}

.item > input,
.item > select {
  display: flex;
  align-items: center;
  border: none;
  height: fit-content;
  font-size: 20px;
}
.item > input {
  max-width: fit-content;
  min-width: 100px;
  text-decoration: underline;
  text-underline-offset: 3px;
  padding: 12px;
}
.item > select {
  width: 85px;

  padding: 12px;
  width: fit-content;
  text-align: left;
  border-radius: 6px;
}

.item > input:disabled,
.item > select:disabled {
  background-color: white;
  color: black;
  border: none;
  text-decoration: none;
}
.item > select:disabled {
  width: 85px;
  border-radius: 6px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

@media screen and (max-width: 1023px) {
}

@media screen and (max-width: 767px) {
  .profile_detail_wrap {
    padding: 0 12px;
  }
}
</style>
