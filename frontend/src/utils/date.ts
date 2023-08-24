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

export const checkTime = (time: string) => {
  const [mm, ss] = time.split(':');

  if (mm === '' || ss === '') return false;

  if (Number(mm) >= 24 || Number(mm) < 0) return false;
  if (Number(ss) > 60 || Number(ss) < 0) return false;

  return true;
};

export const compareTime = (time1: string, time2: string) => {
  const [time1H, time1M] = time1.split(':').map(Number);
  const [time2H, time2M] = time2.split(':').map(Number);

  if (time1H < time2H) return 1;
  if (time1H > time2H) return -1;

  if (time1M < time2M) return 1;
  if (time1M > time2M) return -1;

  return 0;
};
