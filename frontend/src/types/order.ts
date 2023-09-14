export interface IOrderResponse {
  id: number;
  userId: number;
  profileImageUrl: string;
  nickName: string;
  possibleDate: string;
  possibleTime: string;
  requirements: string;
}

export interface IOrder {
  responses: Array<IOrderResponse>;
  dealStatus: boolean;
}

export interface IPostOrder {
  productId: number;
  possibleDateStart: string;
  possibleDateEnd: string;
  possibleTimeStart: string;
  possibleTimeEnd: string;
  requirements: string;
}

export interface IPurchaser {
  nickName: string;
  profileImageUrl: string;
  email: string;
}
