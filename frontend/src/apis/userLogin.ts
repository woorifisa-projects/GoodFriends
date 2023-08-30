import { apiInstance } from '@/apis/index';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

const oauthProvier = 'google';
const redirectUri = import.meta.env.VITE_APP_REDIRECT_URI;

export const getLoginSiteURL = async () => {
  await api
    .get(`api/auth/google/oauth-uri?oauthProvier=${oauthProvier}&redirectUri=${redirectUri}`)
    .then((res: AxiosResponse) => {
      const { data } = res;
      window.location.href = data.oAuthUri;
    })
    .catch((error: AxiosError) => {
      window.location.href = import.meta.env.BASE_URL + 'err/login';
    });
};

export const getAccessToken = async (code: string) => {
  return await api
    .post(`api/auth/google/token`, {
      code: code,
      redirectUri: redirectUri
    })
    .then((res: AxiosResponse) => {
      const { accessToken } = res.data;

      localStorage.setItem('token', accessToken);

      return { isSuccess: true, data: null };
    })
    .catch((e: AxiosError) => {
      return { isSuccess: false, message: e.message };
    });
};
