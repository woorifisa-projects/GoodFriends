import { apiInstance } from '@/apis/index';
import { ApiType } from '@/constants/apiType';
import type { IResultType } from '@/types/api';
import type { IProduct } from '@/types/product';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

export const getAllProduct = (): Promise<IResultType<Array<IProduct>>> => {
  return api
    .get(`api/products/view`)
    .then((res: AxiosResponse) => {
      const { data } = res;

      return { isSuccess: true, data: data, type: ApiType.PRODUCT };
    })
    .catch((err: AxiosError) => {
      return { isSuccess: false, message: err.message, type: ApiType.PRODUCT };
    });
};
