<template>
  <div class="header">
    <div class="title" @click="goPage(HEADER.LOGO.path)">
      <!-- <span class="material-icons-outlined"> sentiment_satisfied </span> -->
      <!-- {{ HEADER.LOGO.view }} -->
      <img src="@/assets/images/logo.png" alt="" />
    </div>
    <div class="wrap">
      <div>
        <button @click="goPage(HEADER.SELL.path)">{{ HEADER.SELL.title }}</button>
      </div>
      <div id="login">
        <div v-if="!isLogin">
          <button @click="onClickLoginBtn">
            <span class="material-icons-outlined"> login </span>
          </button>
        </div>
        <div v-else>
          <button class="profile" ref="popoverBtn" @click="onClickProfileBtn">
            <img :src="user.imageUrl" alt="" />
          </button>
          <div v-if="isPopoverOpen" class="popover" ref="popover">
            <div class="img">
              <img :src="user.imageUrl" alt="프로필 이미지" />
            </div>
            <div class="content">
              <div>{{ user.nickName }}</div>
              <div @click="onClickMyProfile">{{ HEADER.POPOVER.MY_PAGE }}</div>
              <div @click="onClickLogoutBtn">{{ HEADER.POPOVER.LOGOUT }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import loginAPI from '@/apis/user/login';
import profileAPI from '@/apis/user/profile';
import { LOCAL_STORAGE } from '@/constants/localStorage';
import HEADER from '@/constants/strings/header';
import router from '@/router';
import { useUserInfoStore } from '@/stores/userInfo';
import { goErrorWithReload, goOtherPage, goPageWithReload } from '@/utils/goPage';
import { onMounted, ref, watchEffect } from 'vue';

const store = useUserInfoStore();

const user = ref({
  id: 0,
  nickName: '',
  imageUrl: ''
});

const isLogin = ref(false);
const isPopoverOpen = ref(false);
const popover = ref<Element>();
const popoverBtn = ref<Element>();

const goPage = (path: string) => {
  router.push(path);
};

const onClickLoginBtn = async () => {
  const res = await loginAPI.getUrl();
  if (res.isSuccess && res.data) {
    goOtherPage(res.data);
  } else {
    goErrorWithReload('login');
  }
};

const onClickLogoutBtn = async () => {
  const res = await loginAPI.logout(
    store.id,
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || ''
  );
  if (res.isSuccess) {
    localStorage.removeItem(LOCAL_STORAGE.ACCESS_TOKEN);
    goPageWithReload();
  } else {
    alert('로그아웃 오류');
  }
};

const onClickProfileBtn = (event: MouseEvent) => {
  console.log('!');
  if (isPopoverOpen.value) {
    isPopoverOpen.value = false;
    closePopover(event);
  } else {
    openPopover();
  }
};

const onClickMyProfile = () => {
  isPopoverOpen.value = false;
  router.push('/profile/' + user.value.id);
};

const closePopover = (event: MouseEvent) => {
  const target: Element = event.target as Element;
  if (event.target === popoverBtn.value || target.parentElement === popover.value?.parentElement) {
    return;
  }
  if (popover.value === undefined) return;
  for (let i = 0; i < popover.value.childNodes.length; i++) {
    if (target.parentElement === popover.value?.childNodes[i]) return;
  }

  isPopoverOpen.value = false;
};

const openPopover = () => {
  isPopoverOpen.value = true;
};

const saveInfo = async (token: string) => {
  const res = await profileAPI.getProfile(token);

  if (res.isSuccess && res.data) {
    user.value = {
      id: res.data.id,
      nickName: res.data.nickName,
      imageUrl: res.data.imageUrl
    };
    store.setUserInfo(res.data);
    return true;
  }

  alert(res.message);
  localStorage.removeItem(LOCAL_STORAGE.ACCESS_TOKEN);
  return false;
};

watchEffect(() => {
  if (isPopoverOpen.value) {
    window.addEventListener('click', closePopover);
  } else {
    window.removeEventListener('click', closePopover);
  }
});

watchEffect(() => {
  user.value.imageUrl = store.imageUrl;
  user.value.nickName = store.nickName;
});

onMounted(async () => {
  const token = localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN);
  if (token) {
    isLogin.value = await saveInfo(token);
    return;
  }
  const res = await loginAPI.getAccessTokenWithRefresh();
  if (res.isSuccess && res.data) {
    localStorage.setItem(LOCAL_STORAGE.ACCESS_TOKEN, res.data.accessToken);
    goPageWithReload();
  }
  isLogin.value = false;
});
</script>

<style scoped>
.header {
  width: 100%;
  height: 80px;
  padding: 0 250px;

  position: sticky;
  top: 0;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  background-color: #fff;
  box-shadow: 2px 2px 10px rgba(202, 202, 202, 0.389);
  z-index: 99;
  user-select: none;
}

.title {
  cursor: pointer;
  width: 200px;
  overflow: hidden;
  font-size: 32px;
  font-family: 'LINESeedKR-Bd';
  overflow: visible;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 12px;
}
.title > span {
  transform: scale(1.8);
}
.title img {
  width: 100%;
  /* object-fit: cover; */
}
.wrap {
  display: flex;
  align-items: center;
  gap: 20px;
  font-weight: 300;
}

.wrap > div > button {
  background-color: transparent;
  font-size: 20px;
  font-family: 'LINESeedKR-Bd';
  color: var(--color-yellow);
  transition: color 0.3s ease;
}
.wrap > div > button:hover {
  color: var(--color-blue);
}

#login {
  min-width: 60px;
}
.profile {
  width: fit-content;
  white-space: nowrap;
  width: 60px;
  height: 60px;
  overflow: hidden;
  border-radius: 100%;
  border: 1px solid rgb(240, 240, 240);

  display: flex;
  justify-content: center;
  align-items: center;

  z-index: 2;
}
.profile > img {
  width: 120%;
  object-fit: cover;
  pointer-events: none;
}
.popover {
  position: absolute;
  top: 100px;
  right: 250px;

  background-color: #fefefe;
  box-shadow: 1px 1px 10px rgba(183, 183, 183, 0.779);
  border-radius: 20px;

  padding: 20px;

  width: 200px;
  height: 280px;

  display: flex;
  flex-direction: column;
  align-items: center;

  font-family: 'LINESeedKR-Rg';
}

.popover::after {
  border-bottom: 10px solid #fefefe;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  border-top: 0px solid transparent;

  content: '';
  position: absolute;
  bottom: 280px;
  right: 20px;
}

.popover > .img {
  box-sizing: border-box;
  width: 150px;
  height: 150px;

  display: flex;
  justify-content: center;
  align-items: center;

  border-radius: 100%;
  /* border: 1px solid rgb(240, 240, 240); */
  background-color: white;

  overflow: hidden;
}

.img > img {
  height: 100%;
}
.content {
  display: flex;
  flex-direction: column;
  gap: 4px;
  width: 100%;
}
.content > div {
  width: 100%;
  /* border: 1px solid rgb(240, 240, 240); */
  border-radius: 10px;
  font-size: 18px;
  text-align: center;
  background: white;
  cursor: pointer;
  transition: all 0.3s ease;
}

.content > div:first-child {
  border: none;
  background: transparent;
  cursor: default;
  font-family: 'LINESeedKR-Bd';
}
.content > div:nth-child(2n):hover {
  transform: scale(1.04);
}
@media screen and (max-width: 1023px) {
  .header {
    padding: 0;
  }
  .popover {
    right: 10px;
  }
}

@media screen and (max-width: 767px) {
}
</style>
