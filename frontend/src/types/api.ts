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

export interface IGetUserLog {
  email: string;
  nickname: string;
  lastModifiedAt: string;
  banCount: number;
}
