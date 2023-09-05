import { apiInstance, headers } from '..';
import { ApiType } from '@/constants/apiType';
import type { INoContent, IResultType } from '@/types/api';
import type { IAllProduct, IDetailEditProduct, IDetailProduct, IProduct } from '@/types/product';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();

const productAPI = {
  endPoint: {
    getAll: `api/products/view`,
    postProduct: `api/products`,
    getProduct: `api/products/view/`,
    getEditProduct: `api/products/edit/`,
    editProduct: `api/products/edit/`,
    deleteProduct: `api/products/delete/`
  },
  headers: {},
  getAll: (): Promise<IResultType<Array<IAllProduct>>> => {
    // 전체 조회
    return api
      .get(productAPI.endPoint.getAll)
      .then((res: AxiosResponse) => {
        const { data } = res;
        return { isSuccess: true, data: data, type: ApiType.PRODUCT, code: res.status };
      })
      .catch((error: AxiosError) => {
        return {
          isSuccess: false,
          message: error.message,
          type: ApiType.PRODUCT,
          code: error.status
        };
      });
  },
  postProduct: (token: string, formData: FormData): Promise<INoContent> => {
    // 상품 등록
    return api
      .post(productAPI.endPoint.postProduct, formData, {
        headers: {
          ...headers,
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        return { isSuccess: true, message: '', code: res.status };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message, code: error.status };
      });
  },
  getProduct: (productId: string): Promise<IResultType<IDetailProduct>> => {
    // 상품 상세 조회
    return api
      .get(productAPI.endPoint.getProduct + productId)
      .then((res: AxiosResponse) => {
        return { isSuccess: true, data: res.data, type: ApiType.PRODUCT, code: res.status };
      })
      .catch((error: AxiosError) => {
        return {
          isSuccess: false,
          message: error.message,
          type: ApiType.PRODUCT,
          code: error.status
        };
      });
  },
  getEditProduct: (token: string, productId: string): Promise<IResultType<IDetailEditProduct>> => {
    // 수정할 상품 조회
    return api
      .get(productAPI.endPoint.getEditProduct + productId, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, data: res.data, type: ApiType.PRODUCT, code: res.status };
      })
      .catch((error: AxiosError) => {
        console.log(error);
        return {
          isSuccess: false,
          message: error.message,
          type: ApiType.PRODUCT,
          code: error.status
        };
      });
  },
  editProduct: (token: string, productId: string, formData: FormData): Promise<INoContent> => {
    // 상품 수정
    return api
      .put(productAPI.endPoint.editProduct + productId, formData, {
        headers: {
          ...headers,
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, message: '', code: res.status };
      })
      .catch((error: AxiosError) => {
        console.log(error);
        return { isSuccess: false, message: error.message, code: error.status };
      });
  },
  deleteProduct: (token: string, productId: string): Promise<INoContent> => {
    // 상품 삭제
    return api
      .delete(productAPI.endPoint.deleteProduct + productId, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, message: '', code: res.status };
      })
      .catch((error: AxiosError) => {
        console.log(error);
        return { isSuccess: false, message: error.message, code: error.status };
      });
  }
};

export default productAPI;
