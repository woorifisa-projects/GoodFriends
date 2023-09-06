export interface IProduct {
  title: string;
  productCategory: string;
  sellPrice: number;
}
// 상품 등록시 및 수정 사용할 interface
export interface IPostProduct extends IProduct {
  description: string;
}
// 전체 상품 조회시 사용할 interface
export interface IAllProduct extends IProduct {
  id: number;
  status: string;
  imageUrl: string;
  address: string;
}
// 상품 상세 조회시 사용할 Interface
export interface IDetailProduct extends IProduct {
  id: number;
  userId: number | null;
  adminId: number | null;
  description: string;
  createdDate: string;
  status: string;
  lastModifiedDate: string;
  imageUrls: Array<string>;
  profileImageUrl: string;
  nickName: string;
}
// 수정할 상품 조회할 시 사용할 interface
export interface IDetailEditProduct extends IPostProduct {
  id: number;
  status: string;
  imageUrls: Array<string>;
}
// 상품 상태(판매중, 예약중, 거래완료)
export interface IFilter {
  id: number;
  name: string;
  value: string;
}

/* 관리자 */
// 전체 상품 조회시 사용할 interface
export interface IAllProductAdmin {
  id: number;
  imageUrl: string;
  title: string;
  address: string;
  sellPrice: number;
  status: string;
}
