package woorifisa.goodfriends.backend.declaration.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.declaration.domain.Declaration;
import woorifisa.goodfriends.backend.declaration.domain.DeclarationRepository;
import woorifisa.goodfriends.backend.declaration.dto.request.DeclarationSaveRequest;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import static woorifisa.goodfriends.backend.declaration.domain.ReportStatus.PROCESSING;

@Transactional(readOnly = true)
@Service
public class DeclarationService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DeclarationRepository declarationRepository;

    public DeclarationService(UserRepository userRepository,
                              ProductRepository productRepository,
                              DeclarationRepository declarationRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.declarationRepository = declarationRepository;
    }

    @Transactional
    public Long saveReport(LoginUser loginUser, Long productId, DeclarationSaveRequest request) {

        User foundUser = userRepository.getById(loginUser.getId());
        Product foundProduct = productRepository.getById(productId);

        Declaration newDeclaration = createDeclaration(foundUser, foundProduct, request);
        declarationRepository.save(newDeclaration);

        return newDeclaration.getId();
    }
    private Declaration createDeclaration(User user, Product product ,DeclarationSaveRequest request) {
        Declaration newDeclaration = Declaration.builder()
                .declarationCategory(request.getDeclarationCategory())
                .content(request.getContent())
                .reportStatus(PROCESSING)
                .user(user)
                .product(product)
                .build();
        return newDeclaration;
    }
}
