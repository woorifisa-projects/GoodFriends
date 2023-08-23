<template>
  <div id="wrap">
    <div class="sidebar">
      <div class="sidebar-inner">
        <div class="logo">
          <div class="logo-name">
            <button class="logo-name-btn" @click="onClickMangeLog">{{ ADMIN_SIDEBAR.LOGO }}</button>
          </div>
          <div class="logo-admin">{{ ADMIN_SIDEBAR.MANAGER }}</div>
        </div>
        <div class="admin-menu">
          <ul class="admin-menu-ul">
            <li>
              <button @click="onClickMangeLog">{{ ADMIN_SIDEBAR.MANAGE_LOG }}</button>
            </li>
            <li>
              <button @click="onClickManegeUser">{{ ADMIN_SIDEBAR.MANAGE_USER }}</button>
            </li>
            <li>
              <button @click="onClickManegeProduct">{{ ADMIN_SIDEBAR.MANAGE_PRODUCT }}</button>
            </li>
            <li>
              <button @click="onClickManegeDeclaration">
                {{ ADMIN_SIDEBAR.MANAGE_DECLAREATION }}
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <RouterView id="main" />
  </div>
</template>

<script setup lang="ts">
import { ADMIN, ADMIN_SIDEBAR } from '@/constants/strings/admin';
import router from '@/router';

//TODO: 페이지 이동
const onClickMangeLog = () => {};

const onClickManegeUser = () => {};

const onClickManegeProduct = () => {};

const onClickManegeDeclaration = () => {};
</script>

<style scoped>
#wrap {
  width: 100%;
  height: 100%;
  display: flex;
}

#main {
  width: 100%;
}
.sidebar {
  /* position: relative; */
  display: flex;
  flex-direction: column;
  width: 170px;
  height: 100%;
  background-color: dodgerblue;
}
.sidebar-inner {
  padding-top: 100px;
}
.logo,
.logo-name-btn {
  color: whitesmoke;
  padding-left: 10px;
  font-size: 21px;
  font-weight: bold;
}

.logo-admin {
  padding-top: 30px;
  padding-left: 10px;
}

.admin-menu-ul {
  padding-top: 50px;
  text-align: center;
}
s ul {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
button {
  height: 30px;
  font-size: 18px;
  font-weight: bold;
  background-color: dodgerblue;
}
</style>
