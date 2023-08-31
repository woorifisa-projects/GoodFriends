import { AxiosError, type AxiosResponse } from 'axios';
import { apiInstance } from '.';
import type { IResultType, IAdminLogin } from '@/types/api';
import { ApiType } from '@/constants/apiType';

const api = apiInstance();

export const AdminLoginAPI = (id: string, password: string): Promise<IResultType<IAdminLogin>> => {
  return api
    .post('api/admin/login', {
      root: id,
      password: password
    })
    .then((res: AxiosResponse) => {
      const { data } = res;

      return {
        isSuccess: true,
        data: { id, password, token: data.accessToken },
        type: ApiType.ADMIN_LOGIN
      };
    })
    .catch((error: AxiosError) => {
      return { isSuccess: false, message: error.message, type: ApiType.ADMIN_LOGIN };
    });
};
