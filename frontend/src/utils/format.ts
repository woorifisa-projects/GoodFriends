export const dateFormat = (date: Date) => {
  const year = date.getFullYear();
  const month = date.getMonth();
  const day = date.getDate();
  console.log(year, month, day);
  return `${year}-${`0${month}`.slice(-2)}-${`0${day}`.slice(-2)}`;
};
