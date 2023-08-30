<template>
  <div class="editUserInfo-Page">
    <div class="editUserInfo-Grid">
      <div class="page-logo">
        <span class="page-logo-in-1"></span
        ><span class="page-logo-in-2">{{ ADMIN.EDIT_USER_TITLE }}</span>
      </div>
      <div class="totlaUserInfo">
        <div class="userInfo1">
          <div class="user-img"><img src="@/assets/tmp/images/image.png" alt="profile img" /></div>
          <div class="userInfo1-1">
            <!-- TODO: ban, amark, average -->
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.MARK }}</span
              ><span class="userInfo-in-2">⭐</span>
            </div>
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.BAN }}</span
              ><span class="userInfo-in-2">{{ banCount }}</span>
            </div>
          </div>
        </div>
        <div class="userInfo2">
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.EMAIL }}&nbsp;</div>
            <input class="input-user" v-model="email" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.BIRTH }}&nbsp;</div>
            <input class="input-user" v-model="birth" />
          </div>

          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.PHONE }}&nbsp;</div>
            <input class="input-user" v-model="phone" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.ADDRESS }}&nbsp;</div>
            <input class="input-user" v-model="address" />
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.MAIL_AUTH }}&nbsp;</div>
            <div class="detail-buttons">
              <span class="mail-auth-yes">완료</span>
              <span></span>
              <span class="mail-auth-no">미완료</span>
            </div>
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.CHECK_ACTIVITY }}&nbsp;</div>
            <div class="detail-buttons">
              <button
                class="activity-yes"
                @click="clickActivity($event, 'yes')"
                :style="{ backgroundColor: yesAcButtonColor }"
              >
                예
              </button>
              <span></span>
              <button
                class="activity-no"
                @click="clickActivity($event, 'no')"
                :style="{ backgroundColor: noAcButtonColor }"
              >
                아니오
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="work-buttons">
        <div class="work-buttons-1">
          <button class="work-btn" @click="clickEdit">{{ ADMIN.EDIT }}</button>
          <button class="work-btn" @click="clickDelete">{{ ADMIN.DELETE }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ADMIN } from '@/constants/strings/admin';
import { reduce } from 'node_modules/cypress/types/bluebird';
import { ref } from 'vue';

const { item } = history.state;
// console.log(item); // 전달 받은 데이터 확인용
const banCount = ref(item.count);
const authMark = ref();

//이메일, 생년월일, 연락처, 주소
const email = ref(item.email);
const birth = ref();
const phone = ref();
const address = ref();
const yesAcButtonColor = ref('white');
const noAcButtonColor = ref('white');

const clickActivity = (event: Event, choice: string) => {
  if (choice === 'yes') {
    yesAcButtonColor.value = '#15ea15';
    noAcButtonColor.value = 'white';
  } else if (choice === 'no') {
    noAcButtonColor.value = '#15ea15';
    yesAcButtonColor.value = 'white';
  }
};
//TODO: 활동정지/수정완료/계정삭제 기능
const clickEdit = () => {};
const clickDelete = () => {};
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
  width: 84px;
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
  background-color: #dbfff6;
  border-radius: 15px;
}
.page-logo-in-2,
.userInfo-in-2 {
  padding-left: 5px;
}
.detail-buttons {
  width: 147px;
  display: flex;
  justify-content: center;
  gap: 3px;
}
.mail-auth-yes,
.mail-auth-no,
.activity-yes,
.activity-no {
  width: 60px;
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}
.work-buttons {
  width: 100%;
  height: 100px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
.work-buttons-1 {
  height: 100px;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  padding: 20px;
}

.work-btn {
  width: 80px;
  height: 40px;
  background-color: #6db1ff;
  font-size: 15px;
  border-radius: 16px;
}
</style>
