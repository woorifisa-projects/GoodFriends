import { apiInstance, headers } from '..';
import type { IResultType, INoContent } from '@/types/api';

import type { IPhoneAUth, IProfile, IProfileEdit,ISellAndPurchaseList } from '@/types/profile';

import { type AxiosResponse } from 'axios';

const api = apiInstance();
const profileAPI = {
  endPoint: {
    editProfile: `api/profile/me/info/`,
    editProfileImg: `api/profile/me/profile-image/`,
    getProfile: `api/profile/me/`,

    sendPhoneAuth: 'api/sms/user',
    checkPhoneAuth: 'api/sms/user/check/'
    getSellList: `api/profile/me/sell-list`,
    getPurchaseList: `api/profile/me/purchase-list`

  },

  getProfile: (token: string | null): Promise<IResultType<IProfile>> => {
    return api
      .get(profileAPI.endPoint.getProfile, {
        headers: { Authorization: `Bearer ${token}` }
      })
      .then((res: AxiosResponse) => {
        const { data } = res;
        return { isSuccess: true, data, code: res.status };
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
  editProfile: (token: string | null, body: IProfileEdit): Promise<INoContent> => {
    return api
      .patch(profileAPI.endPoint.editProfile, body, {
        headers: { ...headers, Authorization: `Bearer ${token}` }
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
  editProfileImg: (token: string | null, formData: FormData): Promise<INoContent> => {
    return api
      .patch(profileAPI.endPoint.editProfileImg, formData, {
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
        if (error.response) {
          return {
            isSuccess: false,
            message: error.response.data.message,
            code: error.response.status
          };
        }
        return { isSuccess: false, message: error.message, code: error.status };
      });
  },
  getSellList: (
    token: string,
    productStatus: string
  ): Promise<IResultType<Array<ISellAndPurchaseList>>> => {
    return api
      .get(profileAPI.endPoint.getSellList, {
        params: { productStatus },
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
  getPurchaseList: (
    token: string,
    confirmStatus: string
  ): Promise<IResultType<Array<ISellAndPurchaseList>>> => {
    return api
      .get(profileAPI.endPoint.getPurchaseList, {
        params: { confirmStatus },
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
  sendPhoneAuth: (token: string | null, body: IPhoneAUth): Promise<INoContent> => {
    return api
      .post(profileAPI.endPoint.sendPhoneAuth,body
      )
      .then((res: AxiosResponse) => {
        return {
          isSuccess: true,
          message: '인증번호 전송 성공',
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
  },
  checkPhoneAuth:(token: string | null, num : number): Promise<IResultType<IProfile>> =>{
    return api
    .get(profileAPI.endPoint.checkPhoneAuth+num)
    .then((res: AxiosResponse) => {
      return {
        isSuccess: true,
        message: res.data,
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

export default profileAPI;
