export interface category {
  id: number;
  name: string;
}

export interface productEdit {
  price: number;
  name: string;
  content: string;
  category: number;
  date: Date;
}
export interface product {
  id: number;
  image: string;
  title: string;
  address: string;
  price: number;
}
export interface filter {
  id: number;
  name: string;
  value: string;
}

export interface editProductAdmin{
  inputProductTitle: string;
  inputProductPrice: number;
  inputProductDate: string;
  inputProductDes: string;
  selectedCategory: string;
  previewImg: string;
  inputImage: string;
}