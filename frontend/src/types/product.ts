// TODO: 삭제 필요
export interface ICategory {
  id: number;
  name: string;
}

export interface IProduct {
  id: number;
  imageUrl: string;
  title: string;
  address: string | null;
  sellPrice: number;
  status: string;
}
export interface IFilter {
  id: number;
  name: string;
  value: string;
}

export interface IEditProductAdmin {
  inputProductTitle: string;
  inputProductPrice: number;
  inputProductDate: string;
  inputProductDes: string;
  selectedCategory: string;
  previewImg: string;
  inputImage: string;
}

export interface IEditProduct {
  title: string;
  productCategory: string;
  description: string;
  sellPrice: string;
}

export interface IDetailProduct {
  adminId: string;
  id: string;
  imageUrls: Array<string>;
  nickName: string;
  productCategory: string;
  profileImageUrl: string;
  description: string;
  sellPrice: string;
  status: string;
  title: string;
  userId: number;
  createdDate: string;
}
