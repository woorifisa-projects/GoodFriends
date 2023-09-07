import { apiInstance, headers } from '..';
import type { IResultType, INoContent } from '@/types/api';
import type { IProfile, IProfileEdit } from '@/types/profile';
import { type AxiosResponse } from 'axios';

const api = apiInstance();
const profileAPI = {
  endPoint: {
    editProfile: `api/profile/me/info`,
    editProfileImg: `api/profile/me/profile-image`,
    getProfile: `api/profile/me`
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
        return { isSuccess: false, message: error.message, code: error.response.status };
      });
  }
};

export default profileAPI;
