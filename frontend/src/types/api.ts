export interface IResultType<T> {
  isSuccess: boolean;
  type: string;
  message?: string;
  data?: T;
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

export interface IApiSuccess {
  isSuccess: boolean;
  message: string;
}
