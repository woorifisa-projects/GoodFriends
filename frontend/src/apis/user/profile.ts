import { apiInstance, headers } from '..';
import { ApiType } from '@/constants/apiType';
import type { IResultType, IProfile, IProfileEdit, INoContent } from '@/types/api';
import { AxiosError, type AxiosResponse } from 'axios';

const api = apiInstance();
const profileAPI = {
  endPoint: {
    editProfile: `api/profiles/me/info`,
    editProfileImg: `api/profiles/me/profile-image`,
    getProfile: `api/profiles/me`
  },

  getProfile: (token: string): Promise<IResultType<IProfile>> => {
    return api
      .get(profileAPI.endPoint.getProfile, {
        headers: { Authorization: `Bearer ${token}` }
      })
      .then((res: AxiosResponse) => {
        const { data } = res;
        console.log(data);
        return { isSuccess: true, data, type: ApiType.PROFILE };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message, type: ApiType.PROFILE };
      });
  },
  editProfile: (token: string, body: IProfileEdit): Promise<INoContent> => {
    return api
      .patch(profileAPI.endPoint.editProfile, body, {
        headers: { ...headers, Authorization: `Bearer ${token}` }
      })
      .then((res: AxiosResponse) => {
        return { isSuccess: true, message: '' };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message };
      });
  },
  editProfileImg: (token: string) => {
    return api
      .patch(profileAPI.endPoint.editProfileImg, {
        headers: { Authorization: `Bearer ${token}` }
      })
      .then((res: AxiosResponse) => {})
      .catch((error: AxiosError) => {});
  }
};

export default profileAPI;
