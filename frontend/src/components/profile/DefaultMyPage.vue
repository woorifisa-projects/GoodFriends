<template>
  <div class="profile">
    <div class="wrap">
      <div class="side">
        <div class="img">
          <img src="@/assets/tmp/images/image.png" alt="profile img" />
        </div>
      </div>
      <div class="main">
        <!-- 추후 추가 내용 -->
      </div>
    </div>
    <div class="wrap">
      <div class="side">
        <div class="list">
          <span>{{ PROFILE_SIDEBAR.MY_INFO }}</span>
          <ul>
            <li :style="setListItemColor('/profile')">
              <router-link to="/profile"> {{ PROFILE_SIDEBAR.PROFILE }} </router-link>
            </li>
          </ul>
          <span>{{ PROFILE_SIDEBAR.DEAL }}</span>
          <ul>
            <li
              v-for="(item, index) in navList"
              v-bind:key="index"
              :style="setListItemColor(item.path)"
            >
              <router-link :to="item.path">{{ item.name }}</router-link>
            </li>
          </ul>
        </div>
      </div>
      <div class="main second-main">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PROFILE_SIDEBAR } from '@/constants/strings/profile';
import { useRoute } from 'vue-router';
const route = useRoute();

// TODO: 페이지 생성시 path 수정
const navList = [
  {
    name: '구매목록',
    path: '/profile/purchase'
  },
  {
    name: '판매목록',
    path: '/profile/sell'
  },
  {
    name: '거래후기',
    path: '/'
  }
];

const setListItemColor = (path: String) => {
  if (route.path === path) return 'background: #7f7f7fd5';
  return '';
};
</script>

<style scoped>
.profile {
  display: flex;
  flex-direction: column;
  gap: 32px;

  margin-bottom: 36px;
}

.wrap {
  display: flex;
}

.wrap > div {
  margin: 20px;
}

.side {
  /* TODO: 반응형 추가할시 수정 */
  width: 350px;
  display: flex;
  justify-content: center;
}

.main {
  width: 100%;
}

.second-main {
  box-shadow: 2px 2px 17px rgb(186, 186, 186);
  min-height: 500px;
  border-radius: 16px;
}

.img {
  width: 180px;
  height: 180px;

  border: 1px solid black;
  border-radius: 100%;

  overflow: hidden;
}

.img > img {
  height: 100%;
}

.list {
  width: 100%;
  margin: 10px;
  padding: 10px;

  height: 480px;

  background: white;
  box-shadow: 2px 2px 17px rgb(186, 186, 186);
  border-radius: 16px;
}

.list > span {
  font-size: 24px;
}

.list > ul {
  display: flex;
  flex-direction: column;
  gap: 16px;

  margin: 20px 0;
}

.list > ul > li {
  padding: 8px 16px;

  background: lightgray;
  border-radius: 8px;
}

a {
  display: block;
  width: 100%;
  height: 100%;
}
</style>
