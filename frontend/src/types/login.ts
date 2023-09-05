/* user */
export interface IGetOAuthURI {
  oAuthUri: string;
}

export interface IGetAccessToken {
  id: string;
  accessToken: string;
}

/* admin */
export interface IAdminLoginResponse {
  token: string;
  root: string;
  password: string;
}

export interface IAdminLoginRequest {
  root: string;
  password: string;
}
