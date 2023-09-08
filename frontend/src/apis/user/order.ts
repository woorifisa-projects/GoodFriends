import { apiInstance, headers } from '..';
import type { IResultType, INoContent } from '@/types/api';
import type { IOrderResponse, IPostOrder } from '@/types/order';
import { type AxiosResponse } from 'axios';

const api = apiInstance();

const orderAPI = {
  endPoint: {
    getOrder: `api/order/`,
    postOrder: `api/orders/`
  },
  headers: {},
  getOrder: (token: string, productId: string): Promise<IResultType<IOrderResponse>> => {
    return api
      .get(orderAPI.endPoint.getOrder + productId, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
        console.log(res);
        return { isSuccess: true, data: res.data.response, code: res.status };
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
  postOrder: (token: string, body: IPostOrder): Promise<INoContent> => {
    return api
      .post(orderAPI.endPoint.postOrder, body, {
        headers: {
          ...headers,
          Authorization: `Bearer ${token}`
        }
      })
      .then((res: AxiosResponse) => {
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

export default orderAPI;
