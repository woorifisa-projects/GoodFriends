export const dateFormat = (date: Date) => {
  const year = date.getFullYear();
  const month = date.getMonth();
  const day = date.getDate();

  return `${year}-${`0${month + 1}`.slice(-2)}-${`0${day}`.slice(-2)}`;
};
