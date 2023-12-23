package woorifisa.goodfriends.backend.report.domain;

import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.common.BaseCreateTimeEntity;
import woorifisa.goodfriends.backend.product.domain.Product;
import woorifisa.goodfriends.backend.user.domain.User;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Embedded;

@Table(name = "reports")
@Entity
public class Report extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "report_category")
    @Enumerated(EnumType.STRING)
    private ReportCategory reportCategory;

    @Column(name = "content")
    @Embedded
    private Content content;

    @Column(name = "report_status")
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    protected Report() {
    }
    @Builder
    public Report(final Product product, final User user,
                  final ReportCategory reportCategory, final Content content,
                  final ReportStatus reportStatus) {
        this.product = product;
        this.user = user;
        this.reportCategory = reportCategory;
        this.content = content;
        this.reportStatus = reportStatus;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public ReportCategory getReportCategory() {
        return reportCategory;
    }

    public Content getContent() {
        return content;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }
}
