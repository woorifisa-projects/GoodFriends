<template>
  <div class="profile">
    <div class="wrap">
      <div class="side">
        <div class="img-wrap">
          <div class="img">
            <img :src="user.imageUrl" alt="profile img" />
          </div>
          <div class="profile-img-upload">
            <input type="file" id="profile-image" @change="onClickProfileImageUpload" />
            <label for="profile-image">
              <span class="material-icons-outlined"> photo_camera </span>
            </label>
          </div>
        </div>
        <div class="email">
          {{ user.email }}
        </div>
      </div>
      <div class="main first-main">
        <div class="profile-banner">
          <div class="profile-banner-item">
            <div class="banner-img">
              <img src="@/assets/images/verifiedBadge.png" alt="" />
            </div>
            <span>
              {{ userInfo.verifiedBadge ? PROFILE_BANNER.TRUE : PROFILE_BANNER.FALSE }}
            </span>
          </div>
          <div class="profile-banner-item">
            <div class="banner-img">
              <img src="@/assets/images/dealCount.png" alt="" />
            </div>
            <span>
              {{ PROFILE_BANNER.DEAL + userInfo.dealCount }}
            </span>
          </div>
          <div class="profile-banner-item">
            <div class="banner-img">
              <img src="@/assets/images/banCount.png" alt="" />
            </div>
            <span>
              {{ PROFILE_BANNER.BAN + userInfo.banCount }}
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="wrap">
      <div class="side second-side">
        <div class="list box">
          <div class="list-item">
            <span>{{ PROFILE_SIDEBAR.MY_INFO }}</span>
            <ul>
              <li>
                <router-link :to="`/profile/` + id">
                  {{ PROFILE_SIDEBAR.PROFILE }}
                </router-link>
              </li>
            </ul>
          </div>
          <div class="list-item">
            <span>{{ PROFILE_SIDEBAR.DEAL }}</span>
            <ul>
              <li v-for="(item, index) in navList" v-bind:key="index">
                <router-link :to="item.path">{{ item.name }}</router-link>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="main second-main box">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PROFILE_BANNER, PROFILE_SIDEBAR } from '@/constants/strings/profile';
import { onMounted, ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { uploadFile } from '@/utils/file';
import { useUserInfoStore } from '@/stores/userInfo';
import profileAPI from '@/apis/user/profile';
import { useLoadingStore } from '@/stores/loading';
import { LOCAL_STORAGE } from '@/constants/localStorage';

const route = useRoute();
const store = useUserInfoStore();

const user = ref({
  imageUrl: store.imageUrl,
  nickName: store.nickName,
  email: store.email
});
const id = ref(route.params.id.toString());
const navList = ref([
  {
    name: '구매목록',
    path: `/profile/${id.value}/purchase`
  },
  {
    name: '판매목록',
    path: `/profile/${id.value}/sell`
  }
]);
const userInfo = ref({
  verifiedBadge: false,
  dealCount: 0,
  banCount: 0
});

const onClickProfileImageUpload = async (event: Event) => {
  // TODO: 이미지 유효성 검사
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  if (!fileList) return;

  const uploadImageFile: Array<File> = [];
  const previewImg: Array<string> = [];
  await uploadFile('ima', fileList, previewImg, 0, uploadImageFile);
  const formData = new FormData();
  formData.append('multipartFile', fileList[0]);

  const loadingStore = useLoadingStore();

  loadingStore.setLoading(true);
  const res = await profileAPI.editProfileImg(
    localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '',
    formData
  );
  loadingStore.setLoading(false);
  if (res.isSuccess) {
    user.value.imageUrl = previewImg[0];
    store.setUserProfileImage(previewImg[0]);
    return;
  }
  alert(res.message);
};

watchEffect(() => {
  user.value = store.getInfo();
});

onMounted(async () => {
  user.value = store.getInfo();
  const res = await profileAPI.getBanner(localStorage.getItem(LOCAL_STORAGE.ACCESS_TOKEN) || '');
  if (res.isSuccess && res.data) {
    userInfo.value = { ...res.data };
  }
});
</script>

<style scoped>
.profile {
  display: flex;
  flex-direction: column;
  gap: 32px;

  margin-bottom: 36px;
  overflow: hidden;
}

.wrap {
  display: flex;
}

.wrap > div {
  margin: 20px;
}

.side {
  width: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}
.side > .email {
  width: 100%;
  font-size: 16px;
  text-align: center;
  margin-top: 12px;

  display: flex;
  position: relative;
  justify-content: center;
}

.list-item > span {
  font-family: 'LINESeedKR-Bd';
}
.list-item li {
  transition: transform 0.3s ease;
  border-bottom: 2px solid transparent;
}
.list-item li:hover {
  border-bottom: 2px solid var(--color-yellow);
  transform: scaleX(1.01);
}

.main {
  width: 100%;
}

.second-main {
  min-height: 500px;
}
.img-wrap {
  width: 220px;
  height: 220px;
  position: relative;
}
.img {
  width: 220px;
  height: 220px;

  border: 1px solid rgb(240, 240, 240);
  border-radius: 100%;

  overflow: hidden;
}

.img > img {
  height: 100%;
}
.img-wrap > .profile-img-upload {
  position: absolute;
  bottom: 0;
  right: 0;

  background-color: transparent;
  width: 60px;
  height: 60px;
}
.profile-img-upload > input[type='file'] {
  visibility: hidden;
}
.img-wrap > .profile-img-upload > label > span {
  background-color: white;
  border: 1px solid rgba(240, 240, 240, 1);
  border-radius: 50%;
  padding: 2px;
  transform: scale(1.5);
  cursor: pointer;
  color: #888;
}
.list {
  width: 100%;
  margin: 0 10px;
  padding: 16px;
  /* height: 100%; */
  position: sticky;
  height: 500px;
  top: 120px;
  background: white;
  border-radius: 16px;
}
.second-side {
  background-color: transparent;
  border: none;
}

.list-item > span {
  font-size: 24px;
}

.list-item > ul {
  display: flex;
  flex-direction: column;
  gap: 16px;

  margin: 20px 0;
}

.item {
  border-bottom: 1px solid rgb(240, 240, 240);

  /* border-radius: 8px; */
}

a {
  padding: 8px 16px;

  display: block;
  width: 100%;
  height: 100%;
  border-bottom: 1px solid transparent;
}

[aria-current] {
  padding: 8px 16px;
  border-bottom: 2px solid var(--color-yellow);
}
.first-main {
  border-radius: 16px;
}

.box {
  border-radius: 8px;
  box-shadow: 0px 0px 1.5px rgba(0, 0, 0, 0.446);
}

.profile-banner {
  width: 100%;
  height: 100%;
  border-radius: 16px;

  display: flex;
  justify-content: center;
  align-items: center;
}
.profile-banner-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.profile-banner-item > span {
  font-family: 'LINESeedKR-Bd';
  font-size: 20px;
}
.banner-img {
  width: 130px;
}
.profile-banner-item:hover img {
  animation: rotate 3s ease infinite;
}
@keyframes rotate {
  0% {
    transform: rotate3d(0, 0, 1, 0deg);
  }
  25% {
    transform: rotate3d(0, 0, 1, 30deg);
  }
  75% {
    transform: rotate3d(0, 0, 1, -30deg);
  }
  100% {
    transform: rotate3d(0, 0, 1, 0deg);
  }
}
.banner-img > img {
  pointer-events: none;
  width: 100%;
  object-fit: cover;
  transition: transform 1s ease;
}
@media screen and (max-width: 1023px) {
}

@media screen and (max-width: 767px) {
  .wrap {
    flex-direction: column;
  }
  .wrap > div {
    margin: 12px 0;
  }

  .side {
    width: 100%;
  }
  .list {
    width: 100%;
    margin: 10px;
    padding: 10px;

    height: fit-content;

    background: white;
    border-radius: 16px;
    display: flex;
    gap: 16px;
    justify-content: center;
  }
  .list-item > ul {
    flex-direction: row;
  }
  .list span {
    display: none;
  }
  .banner-img {
    width: 100px;
  }
  .profile-banner-item > span {
    font-size: 16px;
  }
}
</style>
