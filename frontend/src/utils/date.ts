export const compareDate = (date1: Date, date2: Date) => {
  const [date1Year, date1Month, date1Date] = [
    date1.getFullYear(),
    date1.getMonth(),
    date1.getDate()
  ];
  const [date2Year, date2Month, date2Date] = [
    date2.getFullYear(),
    date2.getMonth(),
    date2.getDate()
  ];

  if (date1Year < date2Year) return 1;
  if (date1Year > date2Year) return -1;

  if (date1Month < date2Month) return 1;
  if (date1Month > date2Month) return -1;

  if (date1Date < date2Date) return 1;
  if (date1Date > date2Date) return -1;

  return 0;
};
