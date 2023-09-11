import { apiInstance, headers } from '..';
import type { INoContent } from '@/types/api';
import type { IPostReport } from '@/types/report';
import { type AxiosResponse } from 'axios';

const api = apiInstance();

const reportAPI = {
  endPoint: {
    postReport: `api/report`
  },
  headers: {},
  postReport: (token: string, productId: string, body: IPostReport): Promise<INoContent> => {
    // 게시글 신고 등록
    return api
      .post(reportAPI.endPoint.postReport, body, {
        params: { productId },
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

export default reportAPI;
