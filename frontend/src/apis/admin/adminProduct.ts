import { apiInstance, headers } from '..';
import type { INoContent, IResultType } from '@/types/api';
import type { AxiosResponse } from 'axios';
import type { IAllProduct, IDetailEditProduct } from '@/types/product';

const api = apiInstance();

const adminProductAPI = {
  endPoint: {
    postProduct: `api/admin/products/`,
    getSearchTitleProduct: `api/admin/products/search/`,
    getAll: `api/admin/products/`,
    getEditProduct: `api/admin/products/edit/`,
    editProduct: `api/admin/products/edit/`,
    deleteProduct: `api/admin/products/remove/`
  },
  headers: {},
  postProduct: (token: string, formData: FormData): Promise<INoContent> => {
    // 상품 등록
    return api
      .post(adminProductAPI.endPoint.postProduct, formData, {
        headers: {
          ...headers,
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        return { isSuccess: true, message: '등록되었습니다.', code: res.status };
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
  getSearchTitleProduct: (
    token: string,
    keyword: string
  ): Promise<IResultType<Array<IAllProduct>>> => {
    // 제목으로 상품 검색
    return api
      .get(adminProductAPI.endPoint.getSearchTitleProduct, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        },
        params: { keyword }
      })
      .then((res: AxiosResponse) => {
        const { data } = res;
        return { isSuccess: true, data: data.responses, code: res.status };
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
  getAll: (token: string): Promise<IResultType<Array<IAllProduct>>> => {
    // 전체 조회
    return api
      .get(adminProductAPI.endPoint.getAll, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        const { data } = res;
        return { isSuccess: true, data: data.responses, code: res.status };
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
  getEditProduct: (token: string, productId: string): Promise<IResultType<IDetailEditProduct>> => {
    // 수정할 상품 조회
    return api
      .get(adminProductAPI.endPoint.getEditProduct + productId, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, data: res.data, code: res.status };
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
  editProduct: (token: string, productId: string, formData: FormData): Promise<INoContent> => {
    // 상품 수정
    return api
      .put(adminProductAPI.endPoint.editProduct + productId, formData, {
        headers: {
          ...headers,
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, message: '수정되었습니다.', code: res.status };
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
  deleteProduct: (token: string, productId: string): Promise<INoContent> => {
    // 상품 삭제
    return api
      .delete(adminProductAPI.endPoint.deleteProduct + productId, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, message: '', code: res.status };
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

export default adminProductAPI;
