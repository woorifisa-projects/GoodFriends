<template>
  <div class="header">
    <div class="title">logo</div>
    <div class="wrap">
      <div>
        <button>판매하기</button>
      </div>
      <div id="login">
        <div v-if="!isLogin">
          <button @click="loginBtnOnClick">login</button>
        </div>
        <div v-else>
          <button class="profile" ref="popoverBtn" @click="onClickProfileBtn">000님</button>
          <div v-if="isPopoverOpen" class="popover" ref="popover">
            <div class="img">
              <img src="@/assets/tmp/images/image.png" alt="예시 이미지" />
            </div>
            <div class="content">
              <div>nickname</div>
              <div>mypage</div>
              <div>logout</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue';

// TODO: login 구현후 수정
const isLogin = ref(true);
const isPopoverOpen = ref(false);
const popover = ref<Element>();
const popoverBtn = ref<Element>();

const loginBtnOnClick = () => {
  // TODO: login 구현후 수정
  isLogin.value = !isLogin.value;
};

const onClickProfileBtn = (event: MouseEvent) => {
  if (isPopoverOpen.value) {
    isPopoverOpen.value = false;
    closePopover(event);
  } else {
    openPopover();
  }
};

const closePopover = (event: MouseEvent) => {
  const target: Element = event.target as Element;
  if (
    target.parentElement === popover.value?.parentElement ||
    target.parentElement === popover.value?.childNodes[0] ||
    target.parentElement === popover.value?.childNodes[1]
  ) {
    return;
  }
  if (event.target !== popoverBtn.value) {
    isPopoverOpen.value = false;
  }
};

const openPopover = () => {
  isPopoverOpen.value = true;
};

watchEffect(() => {
  if (isPopoverOpen.value) {
    window.addEventListener('click', closePopover);
  } else {
    window.removeEventListener('click', closePopover);
  }
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

  background: white;
  box-shadow: 2px 2px 10px rgba(202, 202, 202, 0.389);

  font-family: 'IBM Plex Sans KR', sans-serif;
}

.title {
  font-size: 24px;
  font-weight: 400;
}
.wrap {
  display: flex;
  align-items: center;
  gap: 20px;
  font-weight: 300;
}

.wrap button {
  font-size: 16px;
}

#login {
  width: 60px;
}

.popover {
  position: absolute;
  top: 100px;
  right: 250px;

  background: wheat;
  border-radius: 20px;

  padding: 20px;

  width: 200px;
  height: 300px;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.popover::after {
  border-bottom: 10px solid wheat;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  border-top: 0px solid transparent;
  content: '';
  position: absolute;
  bottom: 300px;
  right: 20px;
}

.popover > .img {
  width: 150px;
  height: 150px;
  border-radius: 100%;
  border: 1px solid black;
  margin-bottom: 10px;
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
  border: 1px solid black;
  border-radius: 10px;
  font-size: 18px;
  text-align: center;
  background: lightblue;
  cursor: pointer;
}

.content > div:first-child {
  border: none;
  background: transparent;
  cursor: text;
}
</style>
