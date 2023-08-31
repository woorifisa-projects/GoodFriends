import { apiInstance } from '..';
import { ApiType } from '@/constants/apiType';
import type { IResultType, IGetOAuthURI } from '@/types/api';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

const oauthProvier = 'google';
const redirectUri = import.meta.env.VITE_APP_REDIRECT_URI;

const loginAPI = {
  endPoint: {
    urlLogin: `api/auth/google/oauth-uri?oauthProvier=${oauthProvier}&redirectUri=${redirectUri}`,
    getAccessToken: `api/auth/google/token`
  },
  headers: {},
  getUrl: (): Promise<IResultType<string>> => {
    return api
      .get(loginAPI.endPoint.urlLogin)
      .then((res: AxiosResponse) => {
        const data = res.data as IGetOAuthURI;
        return { isSuccess: true, data: data.oAuthUri, type: ApiType.LOGIN };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message, type: ApiType.LOGIN };
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
        return { isSuccess: true, data: accessToken, type: ApiType.LOGIN };
      })
      .catch((e: AxiosError) => {
        return { isSuccess: false, message: e.message, type: ApiType.LOGIN };
      });
  }
};

export default loginAPI;
