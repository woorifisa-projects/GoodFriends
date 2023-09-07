import { type AxiosResponse } from 'axios';
import type { IResultType } from '@/types/api';
import { apiInstance } from '..';
import type { IGetUserLog } from '@/types/log';

const api = apiInstance();

const manageLogAPI = {
  endPoint: {
    userLog: 'api/admin/log'
  },
  headers: {},
  selectLog: (): Promise<IResultType<Array<IGetUserLog>>> => {
    return api
      .get(manageLogAPI.endPoint.userLog)
      .then((res: AxiosResponse) => {
        const data = res.data;
        return {
          isSuccess: true,
          data: data.userLogRecord,
          code: res.status
        };
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

export default manageLogAPI;
