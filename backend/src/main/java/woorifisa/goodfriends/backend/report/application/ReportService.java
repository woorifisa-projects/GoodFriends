package woorifisa.goodfriends.backend.report.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woorifisa.goodfriends.backend.auth.dto.LoginUser;
import woorifisa.goodfriends.backend.offender.domain.Offender;
import woorifisa.goodfriends.backend.offender.domain.OffenderRepository;
import woorifisa.goodfriends.backend.profile.domain.Profile;
import woorifisa.goodfriends.backend.profile.domain.ProfileRepository;
import woorifisa.goodfriends.backend.profile.exception.NotFoundProfile;
import woorifisa.goodfriends.backend.report.domain.Report;
import woorifisa.goodfriends.backend.report.domain.ReportRepository;
import woorifisa.goodfriends.backend.report.dto.request.BoardReportCreateServiceRequest;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.product.domain.ProductRepository;
import woorifisa.goodfriends.backend.report.exception.AlreadyReportedException;
import woorifisa.goodfriends.backend.user.domain.User;
import woorifisa.goodfriends.backend.user.domain.UserRepository;

@Transactional(readOnly = true)
@Service
public class ReportService {

    private static final int MAX_REPORT_BAN_COUNT = 3;
    private static final int REPORT_INCREMENT_COUNT = 1;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReportRepository reportRepository;
    private final OffenderRepository offenderRepository;
    private final ProfileRepository profileRepository;

    public ReportService(final UserRepository userRepository,
                         final ProductRepository productRepository,
                         final ReportRepository reportRepository,
                         final OffenderRepository offenderRepository,
                         final ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.reportRepository = reportRepository;
        this.offenderRepository = offenderRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Long saveReport(final LoginUser loginUser, final Long productId, final BoardReportCreateServiceRequest request) {
        if(!existProfile(loginUser.getId()))
            throw new NotFoundProfile();
        if(duplicateReport(productId, loginUser.getId()))
            throw new AlreadyReportedException();

        User foundUser = userRepository.getById(loginUser.getId());
        Product productCreator = productRepository.getByProductIdAndUserId(productId);
        Report newReport = createReport(foundUser, productCreator, request);
        reportRepository.save(newReport);

        createOffenderForViolation(productCreator);
        return newReport.getId();
    }

    public boolean existProfile(final Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return profile != null;
    }

    public boolean duplicateReport(final Long productId, final Long userId) {
        Report report = reportRepository.findByProductIdAndUserId(productId, userId);
        return report != null;
    }
    public Report createReport(final User user, final Product product , final BoardReportCreateServiceRequest request) {
        Report newReport = request.toEntity(user, product);
        return newReport;
    }

    public void createOffenderForViolation(final Product productCreator) {
        int incrementedReportCount = productCreator.getUser().getBan() + REPORT_INCREMENT_COUNT;
        productCreator.getUser().updateBan(incrementedReportCount);

        if(incrementedReportCount >= MAX_REPORT_BAN_COUNT) {
            boolean notActivated = false;
            productCreator.getUser().updateActivated(notActivated);
            Offender offenderUser = createOffender(productCreator.getUser());
            offenderRepository.save(offenderUser);
        }
        userRepository.save(productCreator.getUser());
    }

    public Offender createOffender(final User offenderUser) {
        Offender existingOffender = findOffender(offenderUser);
        if(existingOffender != null) {
            return existingOffender;
        }

        Offender newOffender =  Offender.builder()
                .user(offenderUser)
                .build();
        return newOffender;
    }

    private Offender findOffender(final User offenderUser) {
        return offenderRepository.findByUserId(offenderUser.getId());
    }
}
