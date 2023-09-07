package woorifisa.goodfriends.backend.report.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByUserIdAndProductId(Long userId, Long productId);
}
