<template>
  <div class="admin-login">
    <div class="card">
      <div class="title">Admin</div>
      <div class="form">
        <form @submit.prevent="submit">
          <div>
            <label for="id">ID</label>
            <input
              required
              type="text"
              name="id"
              id="id"
              v-model="userId"
              placeholder="Enter Your ID"
            />
          </div>
          <div>
            <label for="pw">PW</label>
            <input
              required
              type="password"
              name="pw"
              id="pw"
              v-model="userPw"
              placeholder="Enter Your Password"
            />
          </div>
          <button type="submit">login</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

const userId = ref('');
const userPw = ref('');

const submit = () => {
  if (!userId.value || !userPw.value) {
    return;
  }
  // TODO: login api 연결 필요
  const API = "http://localhost:8080/admin/login"

  axios.post(API,{
    // 로그인 창에서 클라이언트가 입력하는 데이터
    "adminId" : userId.value,
    "password" : userPw.value
  },
  {
    headers : {
      // 클라이언트가 서버에게 요청하는 타입
      "Content-Type" : "application/json;charset=UTF-8",
      // 클라이언트가 서버에게 보내는 타입
      'Accept' : 'application/json',
      // CORS 에러 방지 - 프론트 주소 적거나 '*'로 모든 주소 허용
      'Access-Control-Allow-Origin' : 'http://localhost:5173'
    }
  })
  .then((response) => {
    
    if(response.headers['authorization'] === response.data.token){
      window.alert("이미 로그인된 상태입니다.")
      return;
    }

    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${response.data.token}`

    window.alert('로그인 되었습니다');

    return response.data;

  })
  .catch((e) => {
    console.log(e.response.data);

    window.alert("아이디 혹은 비밀번호를 확인하세요");

    return "아이디 혹은 비밀번호를 확인하세요";
  })
  
  return;
};
</script>

<style scoped>
.admin-login {
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #6db1ff;
}

.card {
  box-sizing: content-box;
  width: 350px;
  padding: 40px;

  border: 1px solid rgba(114, 114, 114, 0.502);
  border-radius: 16px;

  background-color: #ffffff81;
  box-shadow: 1px 1px 30px rgba(208, 208, 208, 0.72);
}

.title {
  font-size: 32px;
  font-weight: 800;
  text-align: center;
}

.form {
  margin-top: 36px;
}

.form > form {
  display: flex;
  flex-direction: column;
}

form > div {
  padding: 20px 0px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

input {
  border: none;
  background-color: #e7f2ff;
  border-radius: 8px;
  font-size: 18px;
  padding: 16px;
}

input:focus {
  border: none;
  outline: none;
}

label {
  font-size: 18px;
  font-weight: 600;
  padding-left: 8px;
}

form > button {
  background-color: white;
  font-size: 24px;
  padding: 12px 20px;
  margin-top: 36px;
  border: 1px solid rgba(0, 0, 0, 0.237);
  border-radius: 12px;
  box-shadow: 1px 1px 10px rgba(185, 185, 185, 0.21);
}

form > button:active {
  box-shadow: inset 1px 1px 10px rgba(0, 0, 0, 0.186);
  background-color: #eaeaea;
}
</style>
