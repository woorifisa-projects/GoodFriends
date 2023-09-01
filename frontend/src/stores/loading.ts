import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useLoadingStore = defineStore('loading', () => {
  const isLoading = ref(false);

  const setLoading = (flag: boolean) => {
    isLoading.value = flag;
  };
  return { isLoading, setLoading };
});
