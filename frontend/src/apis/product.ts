import { apiInstance } from '@/apis/index';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

export const getAllProduct = async () => {
  return await api
    .get(`api/products/view`)
    .then((res: AxiosResponse) => {
      const { data } = res;

      return data;
    })
    .catch((err: AxiosError) => {
      window.location.href = import.meta.env.BASE_URL + '404';
    });
};
