import router from '@/router';

export const goErrorWithReload = (url: string) => {
  window.location.href = import.meta.env.BASE_URL + 'err/' + url;
};

export const goError = (url: string) => {
  router.push(`/err/${url}`);
};

export const goPageWithReload = (url = '') => {
  window.location.href = import.meta.env.BASE_URL + url;
};

export const goOtherPage = (url: string) => {
  window.location.href = url;
};
