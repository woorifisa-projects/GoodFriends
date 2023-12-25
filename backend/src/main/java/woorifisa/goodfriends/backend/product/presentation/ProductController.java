package woorifisa.goodfriends.backend.product.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.auth.presentation.AuthenticationPrincipal;
import woorifisa.goodfriends.backend.product.application.ProductService;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.dto.request.ProductCreateRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductDetailResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductsResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    private static final int MAX_PAGE_SIZE = 12;
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록
    @PostMapping
    public ResponseEntity<Void> saveProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                            @RequestPart final ProductCreateRequest request,
                                            @RequestPart final List<MultipartFile> multipartFiles) throws IOException {
        Long productId = productService.saveProduct(loginUser.getId(), request, multipartFiles);
        return ResponseEntity.created(URI.create("/products/" + productId)).build(); // 201
    }

    // 상품 검색
    @GetMapping("/search")
    public ResponseEntity<ProductsResponse> findSearchProduct(@PageableDefault(size = MAX_PAGE_SIZE) Pageable pageable,
                                                              @RequestParam final String productCategory,
                                                              @RequestParam final String keyword) {
        ProductsResponse responses = productService.findSearchProduct(pageable, productCategory, keyword);
        return ResponseEntity.ok().body(responses); // 200
    }

    // 상품 카테고리별 조회
    @GetMapping("/category")
    public ResponseEntity<ProductsResponse> findProductByCategory(@PageableDefault(size = MAX_PAGE_SIZE) Pageable pageable,
                                                                  @RequestParam final String productCategory) {
        ProductCategory category = ProductCategory.valueOf(productCategory);
        ProductsResponse responses = productService.findProductByCategory(pageable, category);
        return ResponseEntity.ok().body(responses);
    }

    // 상품 전체 조회
    @GetMapping
    public ResponseEntity<ProductsResponse> findAllProducts(@PageableDefault(size = MAX_PAGE_SIZE) Pageable pageable) {
        ProductsResponse responses = productService.findAllProducts(pageable);
        return ResponseEntity.ok().body(responses);
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> findProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                                             @PathVariable final Long productId) {
        ProductDetailResponse response = productService.findProduct(loginUser.getId(), productId);
        return ResponseEntity.ok().body(response);
    }

    // 수정할 상품 상세 조회
    @GetMapping("/edit/{productId}")
    public ResponseEntity<ProductUpdateResponse> findEditProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                                                 @PathVariable final Long productId) {
        ProductUpdateResponse response = productService.findEditProduct(loginUser.getId(), productId);
        return ResponseEntity.ok().body(response);
    }

    // 상품 수정
    @PutMapping("/edit/{productId}")
    public ResponseEntity<Void> updateProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                              @PathVariable final Long productId,
                                              @RequestPart final ProductUpdateRequest request,
                                              @RequestPart final List<MultipartFile> multipartFiles) throws IOException {
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(request.getTitle(), request.getProductCategory(), request.getDescription(), request.getSellPrice(), multipartFiles);
        productService.updateProduct(productUpdateRequest, loginUser.getId(), productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 상품 삭제
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal final LoginUser loginUser,
                                              @PathVariable final Long productId) throws MalformedURLException {
        productService.deleteById(loginUser.getId(), productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
