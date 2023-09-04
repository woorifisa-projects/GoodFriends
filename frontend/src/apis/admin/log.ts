import axios, { AxiosError, type AxiosResponse } from 'axios';
// import type { IResultType, IAdminLoginResponse, IAdminLoginRequest, IGetUserLog } from '@/types/api';
import type { IResultType, IAdminLoginResponse, IAdminLoginRequest, IGetUserLog } from '@/types/api';
import { ApiType } from '@/constants/apiType';
import { apiInstance } from '..';

const api = apiInstance();

// const manageLogAPI = {
//   endPoint: {
//     selectLog: 'api/admin/user-log/record' // API 엔드포인트 경로를 수정합니다.
//   },
//   headers: {}, 
//   selectLog: async () => {
//     try {
//       const response = await api.get(manageLogAPI.endPoint.selectLog);
//       const data = response.data;
//       console.log(data);
//     } catch (error) {
//       console.error('API 호출 중 오류 발생:', error);
//     }
//   }
// };


const manageLogAPI = {
endPoint: {
    userlog: 'api/admin/user-log/record'
},
headers:{},
selectLog:():Promise<IResultType<Array<IGetUserLog>>> => {
        return api.get(manageLogAPI.endPoint.userlog)
        .then((res:AxiosResponse)=>{
        const data = res.data;
        return {isSuccess: true, data:data.userLogRecord, type: ApiType.ADMIN_LOG}
    
    }).catch((error: AxiosError) =>{
        return{isSuccess:false, message: error.message, type: ApiType.ADMIN_LOG}
    })

  }
};

  
export default manageLogAPI;
