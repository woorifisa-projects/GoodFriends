import axios, { AxiosError } from "axios";
const BASE_URL = "http://localhost:8080/admin";
  
export const login = async (userId: string, userPw: string): Promise<boolean> => {
    try {
      const response = await axios.post(
        BASE_URL+"/login",
        {
          // 로그인 창에서 클라이언트가 입력하는 데이터
          "adminId" : userId,
          "password" : userPw
        },
        {
          headers : {
            // 클라이언트가 서버에게 요청하는 타입
            "Content-Type" : "application/json;charset=UTF-8",
            // 클라이언트가 서버에게 보내는 타입
            'Accept' : 'application/json'
          }
        }
      );

      // 로그인 성공 시 토큰을 LocalStorage에 저장
      localStorage.setItem('accessToken', response.data);

      // 요청 헤더에 토큰 추가
      axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem('accessToken');

      return true;
    } 
    catch(error) {
      // 에러를 AxiosError 타입으로 캐스팅
      const e = error as AxiosError;
      console.log(e.response?.data);
  
      return false;
    }
  };