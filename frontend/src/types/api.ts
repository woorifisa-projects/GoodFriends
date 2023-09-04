export interface IResultType<T> {
  isSuccess: boolean;
  type: string;
  message?: string;
  data?: T;
}

export interface INoContent {
  isSuccess: boolean;
  message: string;
}

export interface IGetOAuthURI {
  oAuthUri: string;
}

export interface IAdminLoginResponse {
  token: string;
  root: string;
  password: string;
}

export interface IAdminLoginRequest {
  root: string;
  password: string;
}

export interface IGetAccessToken {
  id: string;
  accessToken: string;
}

export interface IProfile {
  id: number;
  email: string;
  imageUrl: string;
  address: string;
  nickName: string;
  mobileNumber: string;
}

export interface IProfileEdit {
  email: string;
  address: string;
  nickName: string;
  mobileNumber: string;
}

export interface IGetUserLog{
  email: string;
  nickName: string;
  lastModified:string;
  banCount: number;
}