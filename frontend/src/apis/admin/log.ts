import axios, { AxiosError, type AxiosResponse } from 'axios';
import type { IResultType, IGetUserLog } from '@/types/api';
import { ApiType } from '@/constants/apiType';
import { apiInstance } from '..';

const api = apiInstance();

const manageLogAPI = {
  endPoint: {
    userlog: 'api/admin/user-log/record'
  },
  headers: {},
  selectLog: (): Promise<IResultType<Array<IGetUserLog>>> => {
    return api
      .get(manageLogAPI.endPoint.userlog)
      .then((res: AxiosResponse) => {
        const data = res.data;
        return { isSuccess: true, data: data.userLogRecord, type: ApiType.ADMIN_LOG };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message, type: ApiType.ADMIN_LOG };
      });
  }
};

export default manageLogAPI;
