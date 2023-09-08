package woorifisa.goodfriends.backend.report.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.offender.domain.Offender;
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

        // 신고 등록하면 신고 테이블 (신고한 유저, 신고 카테고리, 신고 내용) 생성
        User foundUser = userRepository.getById(loginUser.getId());
        Product foundProduct = productRepository.getByProductIdAndUserId(productId);

        Report newReport = createDeclaration(foundUser, foundProduct, request);
        reportRepository.save(newReport);

        // 신고 당한 유저는 횟수 + 1 증가
        int updatedBan = foundProduct.getUser().getBan() + 1;
        foundProduct.getUser().updateBan(updatedBan);

        // 신고 당한 유저의 ban 횟수가 3이면 비활성화 상태로 변경, 부정행위자에 추가
        if(updatedBan >= 3) {
            boolean notActivated = false;
            foundProduct.getUser().updateActivated(notActivated);

            createOffender(foundProduct.getUser());
        }

        // 신고당한 유저의 신고 당한 횟수 업데이트
        userRepository.save(foundProduct.getUser());
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

    private void createOffender(User offenderUser) {
        Offender.builder()
                .user(offenderUser)
                .limitedDate(offenderUser.getLastModifiedAt())
                .build();
    }
}
