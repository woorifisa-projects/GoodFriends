<template>
  <div class="editUserInfo-Page">
    <div class="editUserInfo-Grid">
      <div class="page-logo">
        <span class="page-logo-in-1">{{ nickname }}</span
        ><span class="page-logo-in-2">{{ ADMIN.EDIT_USER_TITLE }}</span>
      </div>
      <div class="totlaUserInfo">
        <div class="userInfo1">
          <div class="user-img"><img src="@/assets/tmp/images/image.png" alt="profile img" /></div>
          <div class="userInfo1-1">
            <!-- TODO: ban, amark, average -->
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.BAN }}</span
              ><span class="userInfo-in-2">{{ banCount }}</span>
            </div>
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.MARK }}</span
              ><span class="userInfo-in-2">👊</span>
            </div>
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.AVERRAGE }}</span
              ><span class="userInfo-in-2">1.2</span>
            </div>
          </div>
        </div>
        <div class="userInfo2">
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.EMAIL }}&nbsp;</div>
            <input class="input-user" v-model="email" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.NICKNAME }}&nbsp;</div>
            <input class="input-user" v-model="nickname" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.SEX }}&nbsp;</div>
            <input class="input-user" v-model="sex" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.PHONE }}&nbsp;</div>
            <input class="input-user" v-model="phone" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.ADDRESS }}&nbsp;</div>
            <input class="input-user" v-model="address" />
          </div>
        </div>
      </div>
      <div class="work-buttons">
        <div class="work-buttons-1">
          <button class="work-btn" @click="clickSuspend">{{ ADMIN.SUSPEND }}</button>
          <button class="work-btn" @click="clickEdit">{{ ADMIN.EDIT }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ADMIN } from '@/constants/strings/admin';
import { ref } from 'vue';

const { item } = history.state;
// console.log(item); // 전달 받은 데이터 확인용

//TODO: 향후 데이터 받을 시 수정필요!
//신고당한 횟수, 인증마크, 평점
const banCount = ref(item.count);
const authMark = ref();
const average = ref();

//이메일, 닉네임, 성별, 연락처, 주소
const email = ref(item.email);
const nickname = ref(item.nickname);
const sex = ref();
const phone = ref();
const address = ref();

//TODO: 활동정지/수정완료 기능
const clickSuspend = () => {};

const clickEdit = () => {};
</script>
<style scoped>
.editUserInfo-Page {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  flex-direction: column;
}
.editUserInfo-Grid {
  width: 600px;
  height: 570px;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}

.page-logo {
  width: 600px;
  height: 80px;
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.totlaUserInfo {
  display: flex;
  justify-content: center;
  height: 380px;
}
.userInfo1 {
  width: 300px;
  text-align: center;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
.userInfo2 {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
}
.userInfo2-info {
  display: flex;
}
.userInfo2-detailInfo {
  width: 60px;
  height: 30px;
}
.input-user::placeholder {
  text-align: center;
}
.user-img {
  width: 200px;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.userInfo1-1 {
  padding-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
}

.userInfo1-1-detail {
  width: 220px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: aquamarine;
}
.page-logo-in-2,
.userInfo-in-2 {
  padding-left: 5px;
}
.work-buttons {
  width: 600px;
  height: 100px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
.work-buttons-1 {
  width: 200px;
  height: 100px;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 20px;
}

.work-btn {
  width: 80px;
  height: 40px;
  background-color: #6db1ff;
  font-size: 15px;
  border-radius: 16px;
}
</style>
