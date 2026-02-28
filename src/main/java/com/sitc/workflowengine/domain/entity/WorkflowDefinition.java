package com.sitc.workflowengine.domain.entity;

import com.sitc.workflowengine.application.constant.WorkflowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
    name = "workflow_definition",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_workflow_name_version",
            columnNames = {"name", "version"}
        )
    }
)
public class WorkflowDefinition extends BaseAuditableEntity {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false)
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private WorkflowStatus status = WorkflowStatus.DRAFT;

    @Version
    @Column(name = "lock_version", nullable = false)
    private Integer lockVersion;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
