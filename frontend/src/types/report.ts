// 신고 type
export interface IReport {
  id: number;
}

// 게시글 신고 등록시 사용할 interface
export interface IPostReport {
  reportCategory: string;
  reportDetail: string;
}
