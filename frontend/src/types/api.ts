// no content를 제외한 모든 api 리턴 타입
// message, data 는 필수 요소가 아님
// type의 경우 constant에 선언되어 있는 apiType에서 사용
export interface IResultType<T> {
  isSuccess: boolean;
  code: number | undefined;
  message?: string;
  data?: T;
}
// api가 아무런 데이터도 주지 않을 경우 (204일 경우)
export interface INoContent {
  isSuccess: boolean;
  message: string;
  code: number | undefined;
}
