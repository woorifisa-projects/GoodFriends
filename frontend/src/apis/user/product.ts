import { apiInstance, headers } from '..';
import { ApiType } from '@/constants/apiType';
import type { INoContent, IResultType } from '@/types/api';
import type { IProduct } from '@/types/product';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

const productAPI = {
  endPoint: {
    getAll: `api/products/view`,
    postProduct: `api/products`
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
  },
  postProduct: (token: string, formData: FormData): Promise<INoContent> => {
    return api
      .post(productAPI.endPoint.postProduct, formData, {
        headers: {
          ...headers,
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        return { isSuccess: true, message: '' };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message };
      });
  }
};

export default productAPI;
