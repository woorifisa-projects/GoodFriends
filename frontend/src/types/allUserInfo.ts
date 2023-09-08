export interface IGetAllUsers {
    id: number;
    email: string;
    nickname: string;
    profileImageUrl: string;
    createdAt: string;
    lastModifiedAt: string;
    banCount: number;
    phone: number;
    address: string;
    activated: string;
    accountType: number;
    accountNumber: string;
  }
  
  export interface IeditUser {
    nickname: string;
    mobilePhone: string;
    address: string;
    activated: boolean;
    banCount:number;
  }