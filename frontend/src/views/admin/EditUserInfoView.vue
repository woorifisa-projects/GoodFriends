<template>
  <div class="editUserInfo-Page">
    <div class="editUserInfo-Grid">
      <div class="page-logo">
        <span class="page-logo-in-1"></span>
        <span class="page-logo-in-2">{{ title }}{{ ADMIN.EDIT_USER_TITLE }}</span>
      </div>
      <div class="totlaUserInfo">
        <div class="userInfo1">
          <div class="user-img">
            <img :src="profileImg" alt="profile img" />
          </div>

          <div class="userInfo1-1">
            <!-- TODO: ban, amark, average -->
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.PHONE_AUTH }}</span
              ><span class="userInfo-in-2">{{ phone_auth }}</span>
            </div>
            <div class="userInfo1-1-detail">
              <span class="userInfo-in-1">{{ ADMIN.BAN }}</span
              ><span class="userInfo-in-2"
                ><input class="input-bancount" v-model="banCount"
              /></span>
            </div>
          </div>
        </div>
        <div class="userInfo2">
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.NICKNAME }}&nbsp;</div>
            <input class="input-user" v-model="nickname" />
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
            <div class="userInfo2-detailInfo">{{ ADMIN.BANK }}&nbsp;</div>
            <div class="detail-buttons">
              <span class="bank-check">{{ bank }}</span>
            </div>
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.ACCOUNT }}&nbsp;</div>
            <div class="detail-buttons">
              <span class="bank-check">{{ bankAccount }}</span>
            </div>
          </div>
          <div class="userInfo2-info">
            <div class="userInfo2-detailInfo">{{ ADMIN.CHECK_ACTIVITY }}&nbsp;</div>
            <div class="detail-buttons">
              <button
                class="detail-button"
                @click="clickActivity($event, 'yes')"
                :class="Activity === true ? `activity-yes` : ``"
              >
                {{ ADMIN.YES }}
              </button>
              <span></span>
              <button
                class="detail-button"
                @click="clickActivity($event, 'no')"
                :class="Activity === false ? `activity-no` : ``"
              >
                {{ ADMIN.NO }}
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="work-buttons">
        <div class="work-buttons-1">
          <button class="work-btn" @click="clickEdit">{{ ADMIN.EDIT }}</button>
          <button class="work-btn" @click="openModal">{{ ADMIN.DELETE }}</button>
          <div v-if="showModal" class="modal">
            <div class="modal-content">
              <p>{{ ADMIN.ASK_ACCOUNT_DELETE }}</p>
              <div class="modal-buttons">
                <button class="modal-button" @click="clickDelete">{{ ADMIN.YES }}</button>
                <button class="modal-button" @click="closeModal">{{ ADMIN.NO }}</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ADMIN } from '@/constants/strings/admin';
import { ref } from 'vue';
import allUserAPI from '@/apis/admin/allUserInfo';
import router from '@/router';

const { item, data } = history.state;
const resultData = data[item.id - 1];
const nickname = ref(resultData.nickname);
const title = resultData.nickname + '님의 ';
const profileImg = resultData.profileImageUrl;
const banCount = ref(resultData.banCount);
const phone = ref(resultData.phone);
const address = ref(resultData.address);
const bank = ref(resultData.accountType);
const bankAccount = ref(resultData.accountNumber);

console.log(resultData);

let phone_auth_temp = '미완료';
let authMark_temp = 'X';
if (resultData.phone !== null && resultData.phone !== undefined) {
  phone_auth_temp = '완료';
  authMark_temp = '🌟';
}
const authMark = ref(authMark_temp);
const phone_auth = ref(phone_auth_temp);
const Activity = ref(resultData.activated);
if (resultData.activated === '활성화') {
  Activity.value = true;
} else if (resultData.activated === '비활성화') {
  Activity.value = false;
}

const clickActivity = (event: Event, choice: string) => {
  if (choice === 'yes') {
    Activity.value = true;
  } else if (choice === 'no') {
    Activity.value = false;
  }
};

let clickEdit = async () => {
  let userInputInfo = ref({
    nickname: nickname.value,
    mobilePhone: phone.value,
    address: address.value,
    activated: Activity.value,
    banCount: banCount.value
  });

  const editApi = await allUserAPI.postUser(resultData.userid, {
    ...userInputInfo.value
  });
  router.push('/admin/manage/user');
};
const clickDelete = async () => {
  const deleteApi = await allUserAPI.deleteUser(resultData.userid);
  router.push('/admin/manage/user');
};
//계정 삭제시 모달창 관련
const showModal = ref(false);
const openModal = () => {
  showModal.value = true;
};
const closeModal = () => {
  showModal.value = false;
};
</script>

<style scoped>
.activity-yes {
  background-color: #e7ff6d;
}
.activity-no {
  background-color: #e7ff6d;
}
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
  gap: 7px;
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
.editImg {
  padding: 4px;
  width: 119px;
  border: 1px solid rgb(173, 173, 173);
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
.input-bancount {
  width: 20px;
}
.mail-auth-check {
  width: 60px;
}
.bank-check {
}
.detail-button {
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
.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}
.modal-content {
  background-color: #fefefe;
  margin: 20% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 400px;
  box-shadow: 0px 4px 8px 0px rgba(0, 0, 0, 0.2);
}
.modal-buttons {
  width: 100%;
  padding-top: 15px;
  gap: 10px;
  display: flex;
  gap: 10px;
  justify-content: center;
}
.modal-button {
  width: 50px;
  border: 1px solid rgb(173, 173, 173);
}
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
