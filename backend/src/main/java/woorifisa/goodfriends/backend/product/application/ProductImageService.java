package woorifisa.goodfriends.backend.product.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.global.application.S3Service;
import woorifisa.goodfriends.backend.global.config.utils.FileUtils;
import woorifisa.goodfriends.backend.product.domain.ProductImage;
import woorifisa.goodfriends.backend.product.domain.ProductImageRepository;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

    private final S3Service s3Service;

    private final ProductRepository productRepository;

    public ProductImageService(ProductImageRepository productImageRepository, S3Service s3Service, ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.s3Service = s3Service;
        this.productRepository = productRepository;
    }

    public String saveImage(Long productId, MultipartFile multipartFile) throws IOException {
        String uniqueFileName = FileUtils.generateUniqueFileName(multipartFile.getOriginalFilename());
        String savedImageUrl = s3Service.saveFile(multipartFile, uniqueFileName);

        productImageRepository.save(new ProductImage(productRepository.findById(productId).orElseThrow(), savedImageUrl));

        return savedImageUrl;
    }

}
