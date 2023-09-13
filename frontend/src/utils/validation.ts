import { CATEGORY } from '@/constants/category';
import type { IPostProduct } from '@/types/product';
import type { IProfileEdit } from '@/types/profile';
import type { IPostReport } from '@/types/report';

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
  const result = { isSuccess: true, type: <Array<string>>[] };
  if (product.title.length < 2) {
    // 제목 글자 수
    // return { isSuccess: false, type: 'title' };
    result.isSuccess = false;
    result.type.push('title');
  }
  if (product.sellPrice < 1 || product.sellPrice > 99999999) {
    // 가격 범위
    // return { isSuccess: false, type: 'price' };
    result.isSuccess = false;
    result.type.push('price');
  }
  if (product.description.length < 10) {
    // 상품 상세설명 글자 수
    // return { isSuccess: false, type: 'description' };
    result.isSuccess = false;
    result.type.push('description');
  }
  if (
    product.productCategory === 'ALL' ||
    !product.productCategory.length ||
    !CATEGORY[product.productCategory]
  ) {
    // 카테고리(ALL 제외)
    // return { isSuccess: false, type: 'category' };
    result.isSuccess = false;
    result.type.push('category');
  }
  return result;
};

export const checkReportCategory = (report: IPostReport) => {
  if (!report.reportCategory.length) {
    return { isSuccess: false, type: 'reportCategory' };
  }
  return { isSuccess: true };
};

export const checkReporDetail = (report: IPostReport) => {
  if (!report.content.length) {
    return { isSuccess: false, type: 'content' };
  }
  return { isSuccess: true };
};

export const checkEditProfile = (profile: IProfileEdit) => {
  const res = [];
  if (profile.nickName.length < 2) res.push('nickName');
  if (!checkPhoneNumber(profile.mobileNumber)) res.push('phone');
  if (profile.accountType === 'DEFAULT') res.push('account');
  if (profile.accountNumber.length < 7) res.push('account');
  return res;
};
