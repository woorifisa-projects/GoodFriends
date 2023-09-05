import { ref } from 'vue';
import { defineStore } from 'pinia';
import { tmpImage } from '@/utils/image';
import type { IProfile } from '@/types/profile';

export const useUserInfoStore = defineStore('userInfo', () => {
  const id = ref(0);
  const accessToken = ref('');
  const nickName = ref('임시닉네임');
  const imageUrl = ref(tmpImage);
  const email = ref('');
  const mobileNumber = ref('');
  const address = ref('');

  const setUserInfo = (data: IProfile) => {
    id.value = data.id;
    nickName.value = data.nickName;
    imageUrl.value = data.imageUrl;
    email.value = data.email;
    mobileNumber.value = data.mobileNumber;
    address.value = data.address;
  };

  const setAllInfo = (data: IProfile, token: string) => {
    setUserInfo(data);
    accessToken.value = token;
  };
  const getInfo = () => {
    return {
      id: id.value,
      accessToken: accessToken.value,
      nickName: nickName.value,
      imageUrl: imageUrl.value,
      email: email.value,
      mobileNumber: mobileNumber.value,
      address: address.value
    };
  };
  const getProfile = () => {
    return { nickName: nickName.value, mobileNumber: mobileNumber.value, address: address.value };
  };
  return {
    id,
    nickName,
    imageUrl,
    accessToken,
    email,
    mobileNumber,
    address,
    setUserInfo,
    setAllInfo,
    getInfo,
    getProfile
  };
});
