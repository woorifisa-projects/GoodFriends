package woorifisa.goodfriends.backend.declaration.domain;

import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import woorifisa.goodfriends.backend.global.common.BaseCreateTimeEntity;
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

@SuperBuilder
@Table(name = "declarations")
@Entity
public class Declaration extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "declaration_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Enumerated(EnumType.STRING)
    private DeclarationCategory declarationCategory;

    @Column(name = "content")
    @Embedded
    private Content content;

    @Column(name = "report_status")
    private ReportStatus reportStatus;

    protected Declaration() {
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

    public DeclarationCategory getDeclarationCategory() {
        return declarationCategory;
    }

    public Content getContent() {
        return content;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }
}
