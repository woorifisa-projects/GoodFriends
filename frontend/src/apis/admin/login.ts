import { type AxiosResponse } from 'axios';
import type { IResultType } from '@/types/api';
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
          code: res.status
        };
      })
      .catch((error) => {
        if (error.response) {
          return {
            isSuccess: false,
            message: error.response.data.message,
            code: error.response.status
          };
        }
        return { isSuccess: false, message: error.message, code: error.response.status };
      });
  }
};

export default adminLoginAPI;
