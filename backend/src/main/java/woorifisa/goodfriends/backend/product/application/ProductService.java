package woorifisa.goodfriends.backend.product.application;

import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.domain.ProductStatus;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.request.ProductUpdateRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductSearchResponse;
import woorifisa.goodfriends.backend.product.dto.response.ProductUpdateResponse;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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

    public List<ProductSearchResponse> viewAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductSearchResponse> productSearchResponses = products.stream()
                .map(product -> {
                    ProductSearchResponse productSearchResponse = new ProductSearchResponse(
                            product.getId(), product.getProductCategories().getId(), product.getTitle(), product.getStatus(), product.getSellPrice());
                    return productSearchResponse;
                })
                .collect(Collectors.toList());
        return productSearchResponses;
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
