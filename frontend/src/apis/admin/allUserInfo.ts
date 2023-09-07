import { AxiosError, type AxiosResponse } from 'axios';
import type { IResultType, INoContent } from '@/types/api';
import { apiInstance } from '..';
import type { IGetAllUsers,IeditUser } from '@/types/allUserInfo';

const api = apiInstance();

const allUserAPI = {
  endPoint: {
    users: 'api/admin/user',
    deleteUser: 'api/admin/user/remove/',
    postUser: 'api/admin/user/edit/'
  },
  headers: {},
  selectLog: (): Promise<IResultType<Array<IGetAllUsers>>> => {
    return api
      .get(allUserAPI.endPoint.users)
      .then((res: AxiosResponse) => {
        const data = res.data;
        return {
          isSuccess: true,
          data: data,
          code: res.status
        };
      })
      .catch((error: AxiosError) => {
        return {
          isSuccess: false,
          message: error.message,
          code: error.status
        };
      });
  },
  deleteUser: (id: string) : Promise<INoContent> =>{
    // 사용자 삭제
    return api
        .delete(allUserAPI.endPoint.deleteUser+id)
        .then((res: AxiosResponse) =>{
        return{
               isSuccess:true,
               message:'',
               code:res.status
              };
              })
              .catch((error: AxiosError)=>{
                console.log(error);
                return{isSuccess: false, message:error.message, code: error.status};
              });    
   },
   postUser: (id:number, body: IeditUser) : Promise<INoContent> => {
    return api
    .put(allUserAPI.endPoint.postUser+id, body)

    .then((res: AxiosResponse) => {
      return {isSuccess: true, message: '업데이트완료', code: res.status};
    })
    .catch((error:AxiosError)=>{
      return {isSuccess:false, message: error.message, code: error.status};
    });
}
};

export default allUserAPI;