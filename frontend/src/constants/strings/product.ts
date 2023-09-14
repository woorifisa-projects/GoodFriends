import type { IStringToString } from '@/types/dynamic';

export const PRODUCT = {
  PRICE: '가격',
  DESCRIPTION: '자세한 설명',
  TEMPORARY: '임시 저장',
  DELETE: '삭제하기',
  EDIT: '수정하기',
  SUBMIT: '등록하기',
  IMAGE: '이미지',
  CATEGORY: '카테고리',
  CREATE_AT: '등록일',
  REPORT: '신고하기',
  ORDER: '주문하기',
  VIEW_ORDER: '주문서 보기',
  DEAL: '거래하기',
  CONFIRM: '구매자 정보 보기',
  NOTHING: '찾으시는 상품이 없어요 ㅠ.ㅠ',
  SELL_PRODUCT: '해당 조건에 맞는 판매 상품이 없어요',
  PURCHASE_PRODUCT: '해당 조건에 맞는 구매 상품이 없어요'
};

export const ORDER_MODAL = {
  TITLE: '주문하기',
  CONTENTS: [`원하는 날짜와 시간을 입력해주세요`, `추가적인 내용은 아래 요청사항에 적어주세요`],
  DATE: '날짜: ',
  TIME: '시간: ',
  REQUIREMENT: '요청사항',
  SUBMIT: '제출하기',
  ALERT_TIME: '시간을 정확히 입력해주세요(mm:dd)',
  ALERT_DATE: '날짜를 정확히 입력해주세요',
  CONFIRM: ['정말 제출하시겠습니까?', '주문서는 수정이 불가능합니다.']
};

export const PRODUCT_STATUS: IStringToString = {
  ALL: '전체',
  SELL: '판매중',
  RESERVATION: '예약중',
  COMPLETED: '거래완료'
};

export const CONFIRM_STATUS: IStringToString = {
  ALL: '전체',
  ORDERING: '주문중',
  RESERVATION: '예약중',
  COMPLETED: '주문완료'
};
