import { ref } from 'vue';
import { defineStore } from 'pinia';
import { tmpImage } from '@/utils/image';

export const useUserInfoStore = defineStore('userInfo', () => {
  const userId = ref(0);
  const userName = ref('임시닉네임');
  const profileImg = ref(tmpImage);

  function setUserInfo(id: number, name: string, img: string) {
    userId.value = id;
    userName.value = name;
    profileImg.value = img;
  }

  return { userId, userName, profileImg, setUserInfo };
});
