import { CATEGORY } from '@/constants/category';
import type { IPostProduct } from '@/types/product';

export const checkBirthday = (birthday: string) => {
  const [year, month, day, tmp] = birthday.split('-').map(Number);
  const date = new Date(`${year}-${month}-${day}`);
  if (tmp || !year || !month || !day) {
    return false;
  }
  if (date.getFullYear() !== year || date.getMonth() !== month - 1 || date.getDate() !== day) {
    return false;
  }

  return true;
};

export const checkPhoneNumber = (phoneNumber: string) => {
  const regex = /^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$/;
  return regex.test(phoneNumber);
};

export const checkProductValue = (product: IPostProduct) => {
  if (product.title.length < 2) {
    return { isSuccess: false, type: 'title' };
  }
  if (product.sellPrice > 0 || product.sellPrice < 99999999) {
    return { isSuccess: false, type: 'price' };
  }
  if (product.description.length < 10) {
    return { isSuccess: false, type: 'description' };
  }
  if (
    product.productCategory === 'ALL' ||
    !product.productCategory.length ||
    !CATEGORY[product.productCategory]
  ) {
    return { isSuccess: false, type: 'category' };
  }
  return { isSuccess: true };
};
