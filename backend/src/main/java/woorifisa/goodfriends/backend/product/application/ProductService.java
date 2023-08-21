package woorifisa.goodfriends.backend.product.application;

import org.springframework.stereotype.Service;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductCategory;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.product.dto.request.ProductSaveRequest;
import woorifisa.goodfriends.backend.product.dto.response.ProductSaveResponse;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
                .status(request.getStatus())
                .productCategories(request.getProductCategories())
                .description(request.getDescription())
                .sellPrice(request.getSellPrice())
                .build());
    }
}
