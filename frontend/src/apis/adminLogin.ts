import { AxiosError, type AxiosResponse } from 'axios';
import { apiInstance } from '.';
import { useAdminStore } from '@/stores/admin';

const api = apiInstance();

export const AdminLogin = async (id: string, password: string) => {
  return await api
    .post('api/admin/login', {
      root: id,
      password: password
    })
    .then((res: AxiosResponse) => {
      const { data } = res;
      const store = useAdminStore();
      store.setAdmin(id, password, data.accessToken);
      return { isSuccess: true, message: 'success' };
    })
    .catch((error: AxiosError) => {
      return { isSuccess: false, message: error.message };
    });
};
