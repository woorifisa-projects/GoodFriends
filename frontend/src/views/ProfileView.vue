<template>
  <DefaultMyPage>
    <div class="profile_main">
      <div class="btn_wrap">
        <button>{{ PROFILE.WITHDRAWAL }}</button>
        <button>{{ PROFILE.EDIT }}</button>
      </div>
      <div class="profile_detail_wrap">
        <div class="profile_detail">
          <div class="item">
            <div>{{ PROFILE.EMAIL }}</div>
            <div>00@0000.com</div>
          </div>
          <div class="item">
            <div>{{ PROFILE.NICKNAME }}</div>
            <div>00</div>
          </div>
          <div class="item">
            <div>{{ PROFILE.GENDER }}</div>
            <div>00</div>
          </div>
          <div class="item">
            <div>{{ PROFILE.PHONE_NUMBER }}</div>
            <div>000-0000-0000</div>
          </div>
          <div class="item">
            <div>{{ PROFILE.ADDRESS }}</div>
            <div>00시 00구</div>
          </div>
        </div>
      </div>
    </div>
  </DefaultMyPage>
</template>

<script setup lang="ts">
import { PROFILE } from '@/constants/strings/profile';
import DefaultMyPage from '../components/profile/DefaultMyPage.vue';
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
  gap: 16px;
}

.item > div:first-child {
  height: 100%;
  width: 100px;
  padding: 16px;
  background: lightblue;
  text-align: center;
}

.item > div:last-of-type {
  display: flex;
  align-items: center;
}
</style>
