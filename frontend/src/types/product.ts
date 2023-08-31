export interface ICategory {
  id: number;
  name: string;
}

export interface IProductEdit {
  price: number;
  name: string;
  content: string;
  category: number;
  date: Date;
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
