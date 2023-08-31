export interface IItem {
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
export interface IAddressDaum {
  userSelectedType: string;
  roadAddress: string;
  jibunAddress: string;
  bname: string;
  buildingName: string;
  apartment: string;
  zonecode: string;
}
