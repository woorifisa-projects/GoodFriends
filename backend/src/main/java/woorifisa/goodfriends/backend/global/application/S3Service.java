package woorifisa.goodfriends.backend.global.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String saveFile(MultipartFile multipartFile, String uniqueFileName) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, uniqueFileName, multipartFile.getInputStream(), metadata);
        // 저장된 파일의 s3 url 반환
        return amazonS3.getUrl(bucket, uniqueFileName).toString();
    }

    public void deleteFile(String imageUrl) throws MalformedURLException {
        URL s3Url = new URL(imageUrl);
        String bucket = s3Url.getHost().split("\\.")[0];
        String objectFileName = s3Url.getPath().substring(1);

        amazonS3.deleteObject(bucket, objectFileName);
    }

}
