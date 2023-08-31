export interface IResultType<T> {
  isSuccess: boolean;
  type: string;
  message?: string;
  data?: T;
}

export interface IGetOAuthURI {
  oAuthUri: string;
}

export interface IAdminLogin {
  token: string;
  id: string;
  password: string;
}
