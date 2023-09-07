import { ref } from 'vue';
import { defineStore } from 'pinia';
import { tmpImage } from '@/utils/image';
import type { IProfile } from '@/types/profile';

export const useAdminInfoStore = defineStore('adminInfo', () => {
  const id = ref(0);
  const accessToken = ref('');
  const imageUrl = ref(tmpImage);

  const setAdminInfo = (data: IProfile) => {
    id.value = data.id;
  };

  const setAllInfo = (data: IProfile, token: string) => {
    setAdminInfo(data);
    accessToken.value = token;
    
  };
  const getInfo = () => {
    return {
      id: id.value,
      accessToken: accessToken.value,
      imageUrl: imageUrl.value,
    };
  };

  return {
    id,
    accessToken,
    imageUrl,
    setAllInfo,
    getInfo,

  };
});
