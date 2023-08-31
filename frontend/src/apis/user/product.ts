import { apiInstance } from '..';
import { ApiType } from '@/constants/apiType';
import type { IResultType } from '@/types/api';
import type { IProduct } from '@/types/product';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

const productAPI = {
  endPoint: {
    getAll: `api/products/view`
  },
  headers: {},
  getAll: (): Promise<IResultType<Array<IProduct>>> => {
    return api
      .get(productAPI.endPoint.getAll)
      .then((res: AxiosResponse) => {
        const { data } = res;
        return { isSuccess: true, data: data, type: ApiType.PRODUCT };
      })
      .catch((err: AxiosError) => {
        return { isSuccess: false, message: err.message, type: ApiType.PRODUCT };
      });
  }
};

export default productAPI;
