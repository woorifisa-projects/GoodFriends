import { apiInstance, headers } from '..';
import type { IResultType, INoContent } from '@/types/api';
import type { IOrder, IOrderResponse, IPostOrder, IPurchaser } from '@/types/order';
import { type AxiosResponse } from 'axios';

const api = apiInstance();

const orderAPI = {
  endPoint: {
    getOrder: `api/orders/`,
    postOrder: `api/orders/`,
    dealOrder: `api/orders/deal/`
  },
  headers: {},
  getOrder: (token: string, productId: string): Promise<IResultType<IOrder>> => {
    return api
      .get(orderAPI.endPoint.getOrder + productId, {
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
  },
  dealOrder: (token: string, orderId: string): Promise<IResultType<IPurchaser>> => {
    return api
      .post(orderAPI.endPoint.dealOrder + orderId, '', {
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
  }
};

export default orderAPI;
