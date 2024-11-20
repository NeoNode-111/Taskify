package org.example.taskify.backend.task.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 50, nullable = false, unique = true)
    private String title;
    @Column
    private String description;
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @Column(nullable = false)
    private LocalDateTime deadLine;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @PrePersist
    private void setDefaultStatus(){
        if (status == null)
            status = TaskStatus.UNDONE;
    }
}
