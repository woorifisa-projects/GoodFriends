package woorifisa.goodfriends.backend.report.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.report.domain.Report;
import woorifisa.goodfriends.backend.report.domain.ReportRepository;
import woorifisa.goodfriends.backend.report.dto.request.ReportSaveRequest;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

import static woorifisa.goodfriends.backend.report.domain.ReportStatus.PROCESSING;

@Transactional(readOnly = true)
@Service
public class ReportService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReportRepository reportRepository;

    public ReportService(UserRepository userRepository,
                         ProductRepository productRepository,
                         ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.reportRepository = reportRepository;
    }

    @Transactional
    public Long saveReport(LoginUser loginUser, Long productId, ReportSaveRequest request) {

        User foundUser = userRepository.getById(loginUser.getId());
        Product foundProduct = productRepository.getById(productId);

        Report newReport = createDeclaration(foundUser, foundProduct, request);
        reportRepository.save(newReport);

        return newReport.getId();
    }
    private Report createDeclaration(User user, Product product , ReportSaveRequest request) {
        Report newReport = Report.builder()
                .reportCategory(request.getReportCategory())
                .content(request.getContent())
                .reportStatus(PROCESSING)
                .user(user)
                .product(product)
                .build();
        return newReport;
    }
}
