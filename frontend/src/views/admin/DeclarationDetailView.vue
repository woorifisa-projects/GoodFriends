<template>
  <div class="declarationInfo-Page">
    <div class="declarationInfo-Grid">
      <div class="page-logo">
        <span class="page-logo-in-1"></span
        ><span class="page-logo-in-2">{{ ADMIN_DECLARATION.DEC_TITLE }}</span>
      </div>
      <div class="total-DeclarationInfo">
        <div class="decInfo1">
          <div class="product-img">
            <img src="@/assets/tmp/images/image.png" alt="profile img" />
          </div>
          <div class="decInfo1-1">
            <div class="decInfo1-1-detail">
              <span class="decInfo-in-1">{{ ADMIN_DECLARATION.DEC_PRODUCT_CATEGORY }}</span
              ><span class="decInfo-in-2">{{ pr_category }}</span>
            </div>
          </div>
        </div>
        <div class="decInfo2">
          <div class="decInfo2-info">
            <div class="decInfo2-detailInfo">{{ ADMIN_DECLARATION.DEC_DO_ID }}&nbsp;</div>
            <input class="input-user" v-model="email" />
          </div>
          <div class="decInfo2-info">
            <div class="decInfo2-detailInfo">{{ ADMIN_DECLARATION.DEC_NICKNAME }}&nbsp;</div>
            <input class="input-user" v-model="nickname" />
          </div>

          <div class="decInfo2-info">
            <div class="decInfo2-detailInfo">{{ ADMIN_DECLARATION.DEC_PHONE }}&nbsp;</div>
            <input class="input-user" v-model="phone" />
          </div>
          <div class="decInfo2-info">
            <div class="decInfo2-detailInfo">{{ ADMIN_DECLARATION.DEC_CATEGORY }}&nbsp;</div>
            <input class="input-user" v-model="dec_category" />
          </div>
          <div class="decInfo2-info">
            <div class="decInfo2-detailInfo">{{ ADMIN_DECLARATION.DEC_DAMAGE_AMOUNT }}&nbsp;</div>
            <input class="input-user" v-model="pr_category" />
          </div>
        </div>
      </div>
      <div class="total-DeclarationInfo2">
        <div class="damageInfo1">
          <div class="damage-date">{{ ADMIN_DECLARATION.DEC_DAMAGE_DATE }}</div>
          <div class="damage-date-value">{{ damage_date }}</div>
        </div>
        <div class="damageInfo2">
          <div class="damage-des">{{ ADMIN_DECLARATION.DEC_DES }}</div>
          <div class="damage-des-value">
            <textarea class="ta-damage-des-value" v-model="damage_des"></textarea>
          </div>
        </div>
      </div>
      <div class="work-buttons">
        <div class="work-buttons-1">
          <button class="work-btn1" @click="clickBack">
            {{ ADMIN_DECLARATION.DEC_CLICK_BACK }}
          </button>
          <button class="work-btn2" @click="clickDelete">
            {{ ADMIN_DECLARATION.DEC_CLICK_DELETE }}
          </button>
          <button class="work-btn3" @click="clickSuccess">
            {{ ADMIN_DECLARATION.DEC_CLICK_SUCCESS }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ADMIN_DECLARATION } from '@/constants/strings/admin';
import router from '@/router';
import { ref } from 'vue';

//TODO: 이메일, 닉네임, 연락처, 신고카테고리, 상품카테고리, 피해날짜, 피해내용
const email = ref('');
const nickname = ref('');
const phone = ref();
const dec_category = ref('');
const pr_category = ref('');
const damage_date = ref('');
const damage_des = ref('');

//TODO: 뒤로가기/삭제하기/처리완료 기능
const clickBack = () => {
  router.push('/admin/declaration');
};
const clickDelete = () => {};
const clickSuccess = () => {};
</script>

<style scoped>
.activity-yes {
  background-color: #6db1ff;
}
.activity-no {
  background-color: #6db1ff;
}
.declarationInfo-Page {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  flex-direction: column;
}
.declarationInfo-Grid {
  width: 600px;
  height: 600px;
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

.total-DeclarationInfo {
  display: flex;
  justify-content: center;
  height: 225px;
}
.decInfo1 {
  width: 300px;
  text-align: center;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
.decInfo2 {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
}
.decInfo2-info {
  display: flex;
}
.decInfo2-detailInfo {
  width: 90px;
  height: 30px;
}
.input-user::placeholder {
  text-align: center;
}
.product-img {
  width: 200px;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.decInfo1-1 {
  padding-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
}

.decInfo1-1-detail {
  width: 220px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #dbfff6;
  border-radius: 15px;
}
.page-logo-in-2,
.decInfo-in-2 {
  padding-left: 5px;
}
.detail-buttons {
  width: 147px;
  display: flex;
  justify-content: center;
  gap: 3px;
}
.total-DeclarationInfo2 {
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding-top: 10px;
  gap: 10px;
}
.damage-date {
  padding-right: 444px;
}
.damage-date-value {
  padding-right: 410px;
}
.damageInfo1 {
  width: 600px;
  height: 50px;
}
.damageInfo2 {
  width: 600px;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.damage-des {
  padding-right: 444px;
}
.damage-des-value {
  width: 500px;
}
.ta-damage-des-value {
  width: 100%;
  height: 100px;
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

.work-btn1 {
  width: 80px;
  height: 40px;
  background-color: rgb(168, 167, 167);
  font-size: 15px;
  border-radius: 16px;
}
.work-btn2 {
  width: 80px;
  height: 40px;
  background-color: rgb(239, 71, 71);
  font-size: 15px;
  border-radius: 16px;
}
.work-btn3 {
  width: 80px;
  height: 40px;
  background-color: #6db1ff;
  font-size: 15px;
  border-radius: 16px;
}
</style>
