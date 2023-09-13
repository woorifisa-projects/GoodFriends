<template>
  <div class="banner">
    <button @click="onClickBannerBtn('prev')">
      <span class="material-icons-outlined"> arrow_back_ios </span>
    </button>
    <div class="banner-img">
      <img
        :class="index === viewBanner ? `view` : `none`"
        v-for="(img, index) in bannerList"
        :src="img"
        alt="banner-image"
        :key="index"
      />
    </div>
    <button @click="onClickBannerBtn('next')">
      <span class="material-icons-outlined"> arrow_forward_ios </span>
    </button>
  </div>
</template>

<script setup lang="ts">
import { getBannerList } from '@/utils/image';
import { ref } from 'vue';

const bannerList = ref<Array<string>>([]);

const viewBanner = ref(0);
bannerList.value = getBannerList();

const onClickBannerBtn = (flag: string) => {
  if (flag === 'next') {
    viewBanner.value = (viewBanner.value + 1) % bannerList.value.length;
  } else if (flag === 'prev') {
    viewBanner.value -= 1;
    if (viewBanner.value < 0) {
      viewBanner.value = bannerList.value.length - 1;
    }
  }
};

setInterval(() => {
  onClickBannerBtn('next');
}, 3000);
</script>

<style scoped>
.banner {
  box-sizing: border-box;
  height: 500px;
  position: relative;
  display: flex;
  align-items: center;
  border: 1px solid rgba(109, 109, 109, 0.155);
  max-width: 100vw;
  overflow: hidden;
}
.banner > button {
  position: absolute;
  background-color: transparent;
}
.banner > button:first-child {
  left: 0;
}
.banner > button:last-child {
  right: 0;
}
.banner-img {
  width: 100%;
  height: 100%;
  text-align: center;
  background-color: white;
}
.banner-img > img {
  height: 100%;
  object-fit: cover;
}
.banner-img > .none {
  display: none;
}
</style>
