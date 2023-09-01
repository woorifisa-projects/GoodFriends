import axios from 'axios';

export const apiInstance = () => {
  const instance = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    },
    withCredentials: true
  });
  return instance;
};
