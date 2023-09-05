import { AxiosError, type AxiosResponse } from 'axios';
import type { IResultType } from '@/types/api';
import { ApiType } from '@/constants/apiType';
import { apiInstance } from '..';
import type { IAdminLoginRequest, IAdminLoginResponse } from '@/types/login';

const api = apiInstance();

const adminLoginAPI = {
  endPoint: {
    login: `api/admin/login`
  },
  headers: {},
  login: (body: IAdminLoginRequest): Promise<IResultType<IAdminLoginResponse>> => {
    return api
      .post(adminLoginAPI.endPoint.login, body)
      .then((res: AxiosResponse) => {
        const { data } = res;

        return {
          isSuccess: true,
          data: { ...body, token: data.accessToken },
          type: ApiType.ADMIN_LOGIN
        };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message, type: ApiType.ADMIN_LOGIN };
      });
  }
};

export default adminLoginAPI;
