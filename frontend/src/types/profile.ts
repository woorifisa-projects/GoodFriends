export interface Item {
  id: number;
  name: string;
  date: string;
  path: string;
}

declare global {
  interface Window {
    daum: any;
  }
}
export interface AddressDaum {
  userSelectedType: string;
  roadAddress: string;
  jibunAddress: string;
  bname: string;
  buildingName: string;
  apartment: string;
  zonecode: string;
}
