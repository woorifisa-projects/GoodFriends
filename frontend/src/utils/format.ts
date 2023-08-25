export const dateFormat = (date: Date) => {
  const year = date.getFullYear();
  const month = date.getMonth();
  const day = date.getDate();

  return `${year}-${`0${month + 1}`.slice(-2)}-${`0${day}`.slice(-2)}`;
};

export const phoneNumberFormat = (number: string): string => {
  if (number.length > 8) {
    return number.slice(0, 3) + '-' + number.slice(3, 7) + '-' + number.slice(7, 11);
  } else if (number.length > 4) {
    return number.slice(0, 3) + '-' + number.slice(3);
  }
  return number;
};
