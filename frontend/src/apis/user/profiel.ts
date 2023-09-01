import { apiInstance, headers } from '..';
import { ApiType } from '@/constants/apiType';
import type { IResultType, IProfile } from '@/types/api';
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
        headers: { ...headers, Authorization: `Bearer ${token}` }
      })
      .then((res: AxiosResponse) => {
        const { data } = res;
        return { isSuccess: true, data, type: ApiType.PROFILE };
      })
      .catch((error: AxiosError) => {
        return { isSuccess: false, message: error.message, type: ApiType.PROFILE };
      });
  },
  editProfile: () => {
    return api
      .patch(profileAPI.endPoint.editProfile)
      .then((res: AxiosResponse) => {})
      .catch((error: AxiosError) => {});
  },
  editProfileImg: () => {
    return api
      .patch(profileAPI.endPoint.editProfileImg)
      .then((res: AxiosResponse) => {})
      .catch((error: AxiosError) => {});
  }
};

export default profileAPI;
