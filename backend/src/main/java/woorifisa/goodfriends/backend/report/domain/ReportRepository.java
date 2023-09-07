package woorifisa.goodfriends.backend.report.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByUserIdAndProductId(Long userId, Long productId);

    @Query("SELECT r "
            + "FROM Report r "
            + "ORDER BY r.id DESC ")
    List<Report> findAllReportByIdDesc();
}
