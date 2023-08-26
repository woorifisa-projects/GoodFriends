<template>
  <div class="profile">
    <div class="wrap">
      <div class="side">
        <div class="img-wrap">
          <div class="img">
            <img :src="user.image" alt="profile img" />
          </div>
          <div class="profile-img-upload">
            <input type="file" id="profile-image" @change="onClickProfileImageUpload" />
            <label for="profile-image">
              <span class="material-icons-outlined"> photo_camera </span>
            </label>
          </div>
        </div>
        <div class="name">
          <input type="text" v-model="modifiedName" :disabled="isEditButtonDisabled" />
          <button @click="onClickEditName">
            <span class="material-icons-outlined"> edit </span>
          </button>
        </div>
      </div>
      <div class="main">
        <!-- 추후 추가 내용 -->
      </div>
    </div>
    <div class="wrap">
      <div class="side">
        <div class="list">
          <div class="list-item">
            <span>{{ PROFILE_SIDEBAR.MY_INFO }}</span>
            <ul>
              <li :style="setListItemColor('/profile')">
                <router-link to="/profile"> {{ PROFILE_SIDEBAR.PROFILE }} </router-link>
              </li>
            </ul>
          </div>
          <div class="list-item">
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
      </div>
      <div class="main second-main">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PROFILE_SIDEBAR } from '@/constants/strings/profile';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import image from '@/assets/tmp/images/image.png';
import { uploadFile } from '@/utils/file';
const route = useRoute();

const isEditButtonDisabled = ref(true);

const user = ref({
  image,
  name: 'name'
});

const modifiedName = ref(user.value.name);
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

const onClickProfileImageUpload = async (event: Event) => {
  // TODO: 이미지 유효성 검사 및 저장
  const fileList: FileList | null = (event.target as HTMLInputElement).files;
  if (!fileList) return;

  const uploadImageFile: Array<File> = [];
  const previewImg: Array<string> = [];
  await uploadFile('ima', fileList, previewImg, 0, uploadImageFile);

  user.value.image = previewImg[0];
};

const onClickEditName = () => {
  if (isEditButtonDisabled.value) {
    isEditButtonDisabled.value = false;
  } else {
    // TODO: 유효성 검사
    if (!modifiedName.value.length) {
      alert('닉네임은 최소 한 글자 이상 작성하셔야 합니다!');
    }
    user.value.name = modifiedName.value;
    isEditButtonDisabled.value = true;
  }
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
  width: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}
.side > .name {
  font-size: 18px;
  text-align: center;
  margin-top: 10px;

  display: flex;
  position: relative;
  justify-content: center;
}
.name > input {
  border: none;
  border-radius: 12px;
  background-color: white;
  text-align: center;
  font-size: 18px;
  animation: blink 1s ease infinite alternate;
}
.name > input:focus {
  border: none;
}
.list-item > span {
  font-family: 'LINESeedKR-Bd';
}
.list-item li {
  transition: all 0.5s ease;
}
.list-item li:hover {
  transform: scale(1.05);
}
@keyframes blink {
  0% {
    border: 1px solid black;
  }
  100% {
    border: 1px solid transparent;
  }
}
.name > input:disabled {
  animation: none;
}
.name > button {
  position: absolute;
  right: 0;
  bottom: -1px;
  background-color: transparent;
}
.main {
  width: 100%;
}

.second-main {
  box-shadow: 2px 2px 17px rgb(186, 186, 186);
  min-height: 500px;
  border-radius: 16px;
}
.img-wrap {
  width: 220px;
  height: 220px;
  position: relative;
}
.img {
  width: 220px;
  height: 220px;

  border: 1px solid black;
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
  border: 1px solid rgba(138, 138, 138, 0.529);
  border-radius: 50%;
  padding: 2px;
  transform: scale(1.5);
  cursor: pointer;
}
.list {
  width: 100%;
  margin: 10px;
  padding: 10px;

  height: 480px;
  position: sticky;
  top: 120px;
  background: white;
  box-shadow: 2px 2px 17px rgb(186, 186, 186);
  border-radius: 16px;
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

.list-item > ul > li {
  padding: 8px 16px;

  background: lightgray;
  /* background-color: #c8e4b2; */
  border: 1px solid black;

  border-radius: 8px;
}

a {
  display: block;
  width: 100%;
  height: 100%;
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
    box-shadow: 2px 2px 17px rgb(186, 186, 186);
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
}
</style>
