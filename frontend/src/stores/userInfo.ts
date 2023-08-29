import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useUserInfoStore = defineStore('userInfo', () => {
  const userId = ref(0);
  const userName = ref('');
  const profileImg = ref('');

  function setUserInfo(id: number, name: string, img: string) {
    userId.value = id;
    userName.value = name;
    profileImg.value = img;
  }

  return { userId, userName, profileImg, setUserInfo };
});
