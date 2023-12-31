export interface IItem {
  id: number;
  name: string;
  date: string;
  path: string;
  status: string;
}

// 프로필 정보 수정시 사용할 interface
export interface IProfileEdit {
  nickName: string;
  address: string;
  mobileNumber: string;
  accountType: string;
  accountNumber: string;
}

export interface IPhoneAUth {
  recipientPhoneNumber: string;
}

export interface IResultPhoneAuth {
  result: string;
}

export interface IProfilePinia {
  id: number;
  nickName: string;
  imageUrl: string;
  email: string;
}

// 프로필 정보 조회시 사용할 interface
export interface IProfile extends IProfileEdit {
  id: number;
  imageUrl: string;
  email: string;
}

export interface IProfileBanner {
  verifiedBadge: boolean;
  dealCount: number;
  banCount: number;
}

export interface ISellAndPurchaseList {
  productId: number;
  title: string;
  status: string;
  sellPrice: number;
  imageUrl: string;
}

declare global {
  interface Window {
    daum: any;
  }
}
export interface IAddressDaum {
  userSelectedType: string;
  roadAddress: string;
  jibunAddress: string;
  bname: string;
  buildingName: string;
  apartment: string;
  zonecode: string;
}
