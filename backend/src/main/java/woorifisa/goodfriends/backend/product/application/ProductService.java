package woorifisa.goodfriends.backend.product.application;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductImageRepository;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewAllResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductViewOneResponse;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public ProductSaveResponse saveProduct(Long userId, ProductSaveRequest request) {
        User foundUser = userRepository.getById(userId);

        Product newProduct = createProduct(foundUser, request);

        return new ProductSaveResponse(newProduct);
    }

    private Product createProduct(User user, ProductSaveRequest request) {
        return productRepository.save(Product.builder()
                .user(user)
                .title(request.getTitle())
                .status(ProductStatus.SELL)
                .productCategories(request.getProductCategories())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build());
    }

    public List<ProductViewAllResponse> viewAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    List<String> images = productImageRepository.findOneImageUrlByProductId(product.getId(), PageRequest.of(0,1));
                    String image = "";

                    if(!images.isEmpty()){
                        image = images.get(0);
                    }

                    return new ProductViewAllResponse(
                            product.getId(), product.getProductCategories().getId(), product.getTitle(), product.getStatus(), product.getSellPrice(), image);
                })
                .collect(Collectors.toList());
    }

    public ProductViewOneResponse viewOneProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        List<String> images = productImageRepository.findAllImageUrlByProductId(product.getId());

        return new ProductViewOneResponse(product.getId(), product.getUser(), product.getProductCategories().getId(), product.getTitle(), product.getStatus(), product.getSellPrice(), product.getCreatedDate(), product.getLastModifiedDate(), images);
    }

    public ProductUpdateResponse showSelectedProduct(Long id) {
        Product selectedProduct = productRepository.findById(id).orElseThrow();
        return new ProductUpdateResponse(selectedProduct);
    }

    public ProductUpdateResponse updateProduct(ProductUpdateRequest request, Long id) {
        Product selectedProduct = productRepository.findById(id).orElseThrow();
        Product updatedProduct = productRepository.save(Product.builder()
                        .id(id)
                        .user(selectedProduct.getUser())
                        .title(request.getTitle())
                        .productCategories(request.getProductCategories())
                        .status(request.getStatus())
                        .description(request.getDescription())
                        .sellPrice(request.getSellPrice())
                        .createdDate(selectedProduct.getCreatedDate())
                        .build());
        return new ProductUpdateResponse(updatedProduct);
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}
