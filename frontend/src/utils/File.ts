export const uploadFile = (
  type: string,
  fileList: FileList,
  previewFile: Array<string>,
  maxSize: number,
  savedFiles: Array<File>
) => {
  Promise.all(
    Array.from(fileList).map((file) => {
      return new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.addEventListener('load', (e) => {
          if (
            !e.target?.result ||
            savedFiles.includes(file) ||
            previewFile.includes(e.target.result?.toString())
          )
            return;
          // TODO: 이미지 유효성 검사

          savedFiles.push(file);
          resolve(e.target?.result?.toString());
        });
        reader.addEventListener('error', reject);
        reader.readAsDataURL(file);
      })
        .then((res) => {
          previewFile.push(res);
          return [previewFile, savedFiles];
        })
        .catch((err) => {
          // TODO: error 처리
          console.log('err', err);
        });
    })
  );
};
