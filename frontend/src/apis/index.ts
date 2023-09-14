import { API_ERROR } from '@/constants/strings/error';
import axios, { type AxiosResponse } from 'axios';

export const headers = {
  'Content-Type': 'application/json;charset=utf-8',
  'Content-Encoding': 'gzip'
};

export const apiInstance = () => {
  const instance = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_URL,
    headers: { ...headers },
    withCredentials: true
  });
  instance.defaults.timeout = 10;

  instance.interceptors.response.use(
    (res: AxiosResponse) => {
      return res;
    },
    (err) => {
      if (err.code === 'ECONNABORTED') {
        err.message = API_ERROR.TIMEOUT;
        return Promise.reject(err);
      } else {
        return Promise.reject(err);
      }
    }
  );

  return instance;
};
