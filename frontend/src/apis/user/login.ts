import { apiInstance } from '..';
import type { IResultType, INoContent } from '@/types/api';
import type { IGetAccessToken, IGetOAuthURI } from '@/types/login';
import { type AxiosResponse } from 'axios';

const api = apiInstance();

const oauthProvier = 'google';
const redirectUri = import.meta.env.VITE_APP_REDIRECT_URI;

const loginAPI = {
  endPoint: {
    urlLogin: `api/auth/google/oauth-uri?oauthProvier=${oauthProvier}&redirectUri=${redirectUri}/`,
    getAccessToken: `api/auth/google/token/`,
    logout: `api/auth/logout/`,
    getAccessTokenWithRefresh: 'api/auth/token/access/'
  },
  headers: {},
  getUrl: (): Promise<IResultType<string>> => {
    return api
      .get(loginAPI.endPoint.urlLogin)
      .then((res: AxiosResponse) => {
        const data = res.data as IGetOAuthURI;
        return { isSuccess: true, data: data.oAuthUri, code: res.status };
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
  },
  getAccessToken: (code: string): Promise<IResultType<string>> => {
    return api
      .post(`api/auth/google/token`, {
        code: code,
        redirectUri: redirectUri
      })
      .then((res: AxiosResponse) => {
        const { accessToken } = res.data;
        return { isSuccess: true, data: accessToken, code: res.status };
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
  },
  logout: (id: number, token: string): Promise<INoContent> => {
    return api
      .get(loginAPI.endPoint.logout, {
        params: { id },
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        return { isSuccess: true, message: '로그아웃 성공', code: res.status };
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
  },
  getAccessTokenWithRefresh: (): Promise<IResultType<IGetAccessToken>> => {
    return api
      .post(loginAPI.endPoint.getAccessTokenWithRefresh)
      .then((res: AxiosResponse) => {
        const data = res.data as IGetAccessToken;
        return { isSuccess: true, data, code: res.status };
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

export default loginAPI;
