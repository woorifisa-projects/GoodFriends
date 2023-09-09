// 신고 type

// 상품 신고 등록시 사용할 interface
export interface IPostReport {
  productId: number;
  reportCategory: string;
  reportDetail: string;
}
