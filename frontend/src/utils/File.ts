export const uploadFile = async (
  type: string,
  fileList: FileList,
  previewFile: Array<string>,
  maxSize: number,
  savedFiles: Array<File>
) => {
  await Promise.all(
    Array.from(fileList).map(async (file) => {
      try {
        const res = await new Promise<string>((resolve, reject) => {
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
        });
        previewFile.push(res);
        console.log('!@#');
        return [previewFile, savedFiles];
      } catch (err) {
        // TODO: error 처리
        console.log('err', err);
      }
    })
  );
};
