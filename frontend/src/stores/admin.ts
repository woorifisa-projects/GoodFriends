import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useAdminStore = defineStore('admin', () => {
  const adminId = ref('');
  const adminPassword = ref('');
  const accessToken = ref('');

  const setAdmin = (id: string, password: string, token: string) => {
    accessToken.value = token;
    adminId.value = id;
    adminPassword.value = password;
  };

  return { accessToken, setAdmin };
});
