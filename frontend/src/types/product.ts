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
