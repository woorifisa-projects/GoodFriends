import { AxiosError, type AxiosResponse } from 'axios';
import type { IResultType } from '@/types/api';
import { ApiType } from '@/constants/apiType';
import { apiInstance } from '..';
import type { IGetUserLog } from '@/types/log';

const api = apiInstance();

const manageLogAPI = {
  endPoint: {
    userLog: 'api/admin/user-log/record'
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
          type: ApiType.ADMIN_LOG,
          code: res.status
        };
      })
      .catch((error: AxiosError) => {
        return {
          isSuccess: false,
          message: error.message,
          type: ApiType.ADMIN_LOG,
          code: error.status
        };
      });
  }
};

export default manageLogAPI;
