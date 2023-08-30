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
              <span class="mail-auth-check">{{ email_auth }}</span>
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

const { item } = history.state;
//TODO:  console.log(item); // 전달 받은 데이터 확인용
const banCount = ref(item.count);
const authMark = ref();

//TODO: 이메일, 생년월일, 연락처, 주소
const email = ref(item.email);
const birth = ref();
const phone = ref();
const address = ref();
const Activity = ref();

//TODO: 활성화 여부 관련 로직
const clickActivity = (event: Event, choice: string) => {
  if (choice === 'yes') {
    Activity.value = true;
  } else if (choice === 'no') {
    Activity.value = false;
  }
};

//TODO: 메일인증 관련 함수
const email_auth = ref('미완료');
const admin_authCheck = (item: Boolean) => {
  item = true;
  if (item === true) {
    email_auth.value = '완료';
  } else if (item === false) {
    email_auth.value = '미완료';
  }
};

//TODO: 수정완료/계정최종삭제 기능
const clickEdit = () => {};
const clickDelete = () => {};

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
  background-color: #6db1ff;
}
.activity-no {
  background-color: #6db1ff;
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

.mail-auth-check {
  width: 60px;
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
