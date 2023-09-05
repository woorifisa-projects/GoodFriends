export interface IItem {
  id: number;
  name: string;
  date: string;
  path: string;
}

// 프로필 정보 수정시 사용할 interface
export interface IProfileEdit {
  nickName: string;
  address: string;
  email: string;
  mobileNumber: string;
}

// 프로필 정보 조회시 사용할 interface
export interface IProfile extends IProfileEdit {
  id: number;
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
