package woorifisa.goodfriends.backend.global.common;

import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 3, 4
public abstract class BaseCreateTimeEntity {

    @CreatedDate // 1
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public BaseCreateTimeEntity() {
    }

    public LocalDateTime getCreatedDate() {
        return createdAt;
    }
}
