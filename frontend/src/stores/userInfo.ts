import { ref } from 'vue';
import { defineStore } from 'pinia';
import { tmpImage } from '@/utils/image';
import type { IProfilePinia } from '@/types/profile';

export const useUserInfoStore = defineStore('userInfo', () => {
  const id = ref(0);
  const nickName = ref('임시닉네임');
  const imageUrl = ref(tmpImage);
  const email = ref('');

  const setUserInfo = (data: IProfilePinia) => {
    id.value = data.id;
    nickName.value = data.nickName;
    imageUrl.value = data.imageUrl;
    email.value = data.email;
  };

  const setUserNickName = (newNickName: string) => {
    nickName.value = newNickName;
  };

  const setUserProfileImage = (profileImage: string) => {
    imageUrl.value = profileImage;
  };

  const getInfo = () => {
    return {
      id: id.value,
      nickName: nickName.value,
      imageUrl: imageUrl.value,
      email: email.value
    };
  };

  return {
    id,
    nickName,
    imageUrl,
    email,
    getInfo,
    setUserInfo,
    setUserNickName,
    setUserProfileImage
  };
});
