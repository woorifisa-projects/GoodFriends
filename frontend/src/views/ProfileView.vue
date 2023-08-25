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
            <input v-model="user.email" type="email" :disabled="isDisabled" />
          </div>
          <div class="item">
            <label>{{ PROFILE.BIRTHDAY }}</label>
            <input type="text" v-model="user.birthday" :disabled="isDisabled" />
          </div>
          <div class="item">
            <label>{{ PROFILE.GENDER }}</label>
            <select name="gender" id="gender" v-model="user.gender" :disabled="isDisabled">
              <option value="default">default</option>
              <option value="man">man</option>
              <option value="woman">woman</option>
            </select>
          </div>
          <div class="item">
            <label>{{ PROFILE.PHONE_NUMBER }}</label>
            <input type="text" v-model="user.phone" :disabled="isDisabled" />
          </div>
          <div class="item">
            <label>{{ PROFILE.ADDRESS }}</label>
            <input type="text" v-model="user.address" :disabled="isDisabled" />
          </div>
        </div>
      </div>
    </div>
  </DefaultMyPage>
</template>

<script setup lang="ts">
import { PROFILE } from '@/constants/strings/profile';
import DefaultMyPage from '../components/profile/DefaultMyPage.vue';
import { ref } from 'vue';

const user = ref({
  email: '00@000.com',
  gender: 'man',
  birthday: 'yyyy-mm-dd',
  phone: '000-0000-0000',
  address: '00시00구'
});
const isDisabled = ref(true);
const onClickEdit = () => {
  if (isDisabled.value) {
    isDisabled.value = false;
  } else if (!isDisabled.value) {
    // TODO: 유효성 검사
    // TODO: API

    isDisabled.value = true;
  }
};
</script>

<style scoped>
.profile_main {
  /* border: 1px solid red; */
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
  background: rgb(240, 240, 240);
  padding: 16px 20px;
  border-radius: 8px;
  box-shadow: 1px 1px 6px rgba(0, 0, 0, 0.48);
}

.btn_wrap > button:first-child {
  background: lightblue;
}

.profile_detail_wrap {
  padding: 0 36px;
}

.profile_detail {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.item {
  width: 100%;
  border: 1px solid rgb(180, 180, 180);
  border-radius: 24px;
  display: flex;
  overflow: hidden;
  align-items: center;
  gap: 16px;
}

.item > label {
  height: 100%;
  width: 100px;
  padding: 16px;
  background: lightblue;
  text-align: center;
}

.item > input,
.item > select {
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
/* 
.item > input:active,
.item > select:active {
  border: 1px solid black;
} */

@media screen and (max-width: 1023px) {
}

@media screen and (max-width: 767px) {
  .profile_detail_wrap {
    padding: 0 12px;
  }
}
</style>
