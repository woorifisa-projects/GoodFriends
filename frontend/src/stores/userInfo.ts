import { ref } from 'vue';
import { defineStore } from 'pinia';
import { tmpImage } from '@/utils/image';

export const useUserInfoStore = defineStore('userInfo', () => {
  const userId = ref(0);
  const accessToken = ref('');
  const userName = ref('임시닉네임');
  const profileImg = ref(tmpImage);

  function setUserInfo(id: number, name: string, img: string) {
    userId.value = id;
    userName.value = name;
    profileImg.value = img;
  }

  const setUserToken = (token: string) => {
    accessToken.value = token;
  };

  const resetInfo = () => {
    userId.value = 0;
    accessToken.value = '';
    userName.value = '';
    profileImg.value = '';
  };

  return { userId, userName, profileImg, accessToken, setUserInfo, setUserToken, resetInfo };
});
