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
            <select name="bank" id="bank" v-model="userInputInfo.bank" :disabled="isDisabled">
              <option value="default" disabled>은행</option>
              <option :value="account.value" v-for="(account, index) in accountList" :key="index">
                {{ account.name }}
              </option>
            </select>
            <input v-model="userInputInfo.account" type="text" :disabled="isDisabled" />
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
  address: user.value.address,
  account: '000',
  bank: 'default'
});
const accountList = ref([
  { value: 'k', name: '국민' },
  { value: 'w', name: '우리' },
  { value: 's', name: '신한' },
  { value: 'h', name: '하나' },
  { value: 'n', name: '농협' }
]);
const isDisabled = ref(true);
const searchAddress = (data: string) => {
  userInputInfo.value.address = data;
};
const onClickEdit = async () => {
  if (isDisabled.value) {
    isDisabled.value = false;
    return;
  }
  if (userInputInfo.value.account.length < 7) {
    alert('계좌번호');
    return;
  }
  console.log(userInputInfo.value.bank);
  if (userInputInfo.value.bank === 'default') {
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
  const res = await profileAPI.editProfile(store.accessToken, {
    ...userInputInfo.value,
    email: store.email
  });
  loadingStore.setLoading(false);
  if (res.isSuccess) {
    user.value = { ...userInputInfo.value, ...user.value };

    // store.setProfile(
    //   user.value.nickName,
    //   user.value.mobileNumber,
    //   user.value.address,
    //   account
    // );
    isDisabled.value = true;
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

watchEffect(() => {
  user.value = {
    nickName: store.nickName,
    mobileNumber: store.mobileNumber,
    address: store.address
  };
  userInputInfo.value = {
    nickName: store.nickName,
    mobileNumber: store.mobileNumber,
    address: store.address,
    account: '000',
    bank: 'default'
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
  cursor: pointer;
  font-family: 'LINESeedKR-Bd';
  font-size: 15px;
  border: 1px solid black;
  height: 40px;
  width: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--product-status-filter-bg);
  color: var(--product-status-filter-base-text);
  padding: 16px 20px;
  border-radius: 12px;
}

.btn_wrap > button:first-child {
  background-color: var(--product-status-filter-point-bg);
  color: var(--product-status-filter-point-text);
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
  border: 1px solid black;
  height: fit-content;
  padding: 12px;
}
.item > select {
  width: fit-content;
  padding: 12px;
  text-align: center;
}
.item > input:disabled,
.item > select:disabled {
  background-color: white;
  color: black;
  border: none;
}
.item > select:disabled {
  border: 1px solid lightgray;
  border-radius: 6px;
}

@media screen and (max-width: 1023px) {
}

@media screen and (max-width: 767px) {
  .profile_detail_wrap {
    padding: 0 12px;
  }
}
</style>
