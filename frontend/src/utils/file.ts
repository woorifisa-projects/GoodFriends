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
            // 이미지가 중복되면
            if (
              !e.target?.result ||
              savedFiles.includes(file) ||
              previewFile.includes(e.target.result?.toString())
            )
              return;
            // TODO: 이미지 유효성 검사
            if (file.size > 5000000) return;
            savedFiles.push(file);
            resolve(e.target?.result?.toString());
          });
          reader.addEventListener('error', reject);
          reader.readAsDataURL(file);
        });
        previewFile.push(res);
        return [previewFile, savedFiles];
      } catch (err) {
        // TODO: error 처리
        console.log('err', err);
      }
    })
  );
};

export const urlToFile = async (urls: Array<string>) => {
  const imageUrls = urls;

  const imageFetch = async (url: string) => {
    return await fetch(url, {
      method: 'GET',
      cache: 'no-cache'
    });
  };

  const prArray = imageUrls.map((url) => imageFetch(url));

  const res = Promise.all(prArray)
    .then((results) => {
      return results.map(async (imageData, index) => {
        const data = await imageData.blob();
        const extension = imageUrls[index].split('.').pop();
        const fileName = index.toString() + '.' + extension;
        const metaData = { type: `image/${extension}` };
        return new File([data], fileName, metaData);
      });
    })
    .catch((err) => {
      return null;
    });

  return res;
};
