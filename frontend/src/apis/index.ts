import axios from 'axios';

export const headers = {
  'Content-Type': 'application/json;charset=utf-8'
};

export const apiInstance = () => {
  const instance = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_URL,
    headers: { ...headers },
    withCredentials: true
  });
  return instance;
};
