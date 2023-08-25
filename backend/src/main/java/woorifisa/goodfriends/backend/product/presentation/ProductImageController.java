package woorifisa.goodfriends.backend.product.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.product.application.ProductImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/product/images")
@RestController
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<List<String>> saveImage(@PathVariable Long productId,
                                                  @RequestPart List<MultipartFile> multipartFiles) throws IOException {
        List<String> savedImages = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            System.out.println(multipartFile.getOriginalFilename());
            if(!multipartFile.isEmpty()) {
                savedImages.add(productImageService.saveImage(productId, multipartFile));
            }
        }
        return ResponseEntity.ok().body(savedImages);
    }
}
