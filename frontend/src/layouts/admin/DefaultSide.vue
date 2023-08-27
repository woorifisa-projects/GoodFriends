<template>
  <div id="wrap">
    <div class="header">
      <button @click="onClikeSidebarIcon">
        <span class="material-icons-outlined"> density_medium </span>
      </button>
      <button @click="onClickLogout">로그아웃</button>
    </div>
    <div class="total-body">
      <div class="sidebar">
        <div class="sidebar-inner">
          <div class="logo">
            <div class="logo-name">
              <button class="logo-name-btn" @click="onClickMangeLog">
                {{ ADMIN_SIDEBAR.LOGO }}
              </button>
            </div>
            <div class="logo-admin">{{ ADMIN_SIDEBAR.MANAGER }}</div>
          </div>
          <div class="admin-menu">
            <ul class="admin-menu-ul">
              <li>
                <button class="side-button" @click="onClickMangeLog">
                  {{ ADMIN_SIDEBAR.MANAGE_LOG }}
                </button>
              </li>
              <li>
                <button class="side-button" @click="onClickManegeUser">
                  {{ ADMIN_SIDEBAR.MANAGE_USER }}
                </button>
              </li>
              <li>
                <button class="side-button" @click="onClickManegeProduct">
                  {{ ADMIN_SIDEBAR.MANAGE_PRODUCT }}
                </button>
              </li>
              <li>
                <button class="side-button" @click="onClickManegeDeclaration">
                  {{ ADMIN_SIDEBAR.MANAGE_DECLAREATION }}
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <RouterView id="main" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ADMIN, ADMIN_SIDEBAR } from '@/constants/strings/admin';
import router from '@/router';

const onClikeSidebarIcon = () => {
  const con = document.getElementsByClassName('sidebar')[0] as HTMLElement;

  if (con.style.display === 'none') {
    con.style.display = 'block';
  } else {
    con.style.display = 'none';
  }
};

//TODO: 페이지 이동
const onClickLogout = () => {
  router.push('/admin');
};
const onClickMangeLog = () => {
  router.push('/admin/log');
};

const onClickManegeUser = () => {};

const onClickManegeProduct = () => {};

const onClickManegeDeclaration = () => {};
</script>

<style scoped>
#wrap {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  padding: 5px;
}
.header {
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding-left: 5px;
  padding-right: 5px;
}
.material-symbols-outlined {
  font-variation-settings:
    'FILL' 0,
    'wght' 400,
    'GRAD' 0,
    'opsz' 48;
}
.total-body {
  display: flex;
  height: calc(100vh - 30px);
  gap: 5px;
}

#main {
  width: 100%;
  height: 100%;
  padding: 10px;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
}
.sidebar {
  /* position: relative; */
  display: flex;
  flex-direction: column;
  width: 200px;
  height: 100%;
  background-color: dodgerblue;
  box-shadow: 1px 1px 10px rgba(168, 168, 168, 0.596);
  border: 1px solid rgb(173, 173, 173);
  border-radius: 16px;
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
  background-color: dodgerblue;
}

.logo-admin {
  padding-top: 30px;
  padding-left: 10px;
}

.admin-menu-ul {
  padding-top: 50px;
  text-align: center;
}
ul {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.side-button {
  height: 30px;
  font-size: 18px;
  font-weight: bold;
  background-color: dodgerblue;
}
</style>
