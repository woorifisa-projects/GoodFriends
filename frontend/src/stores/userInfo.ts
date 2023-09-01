import { ref } from 'vue';
import { defineStore } from 'pinia';
import { tmpImage } from '@/utils/image';
import type { IProfile } from '@/types/api';

export const useUserInfoStore = defineStore('userInfo', () => {
  const id = ref(0);
  const accessToken = ref('');
  const nickname = ref('임시닉네임');
  const imageUrl = ref(tmpImage);
  const email = ref('');
  const phoneNumber = ref('');

  const setUserInfo = (data: IProfile) => {
    id.value = data.id;
    nickname.value = data.nickname;
    imageUrl.value = data.imageUrl;
    email.value = data.email;
    phoneNumber.value = data.phoneNumber;
  };

  const setAllInfo = (data: IProfile, token: string) => {
    setUserInfo(data);
    accessToken.value = token;
  };
  const getInfo = () => {
    return {
      id,
      accessToken,
      nickname,
      imageUrl,
      email,
      phoneNumber
    };
  };
  return { id, nickname, imageUrl, accessToken, setUserInfo, setAllInfo, getInfo };
});
