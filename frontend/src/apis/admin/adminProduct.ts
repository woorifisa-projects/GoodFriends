import { useAdminStore } from '@/stores/admin';
import { apiInstance, headers } from '..';
import type { INoContent } from '@/types/api';
import { ADMIN_PRODUCT } from '@/constants/strings/admin';
import type { AxiosResponse } from 'axios';

const api = apiInstance();

const productAPI = {
    endPoint: {
        postProduct: `api/admin/products`,
        getSearchTitleProduct: `api/admin/products/search`,
        getAll: `api/admin/products`,
        getProduct: `api/admin/products/`,
        getEditProduct: `api/admin/products/edit/`,
        editProduct: `api/admin/products/edit/`,
        deleteProduct: `api/admin/products/remove/`
    },
    headers: {},
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
        .catch((error) => {
            if(error.response) {
                return {
                    isSuccess: false,
                    message: error.response.data.message,
                    code: error.response.status
                };
            }
            return { isSuccess: false, message: error.message, code: error.response.status };
        });
    }
}