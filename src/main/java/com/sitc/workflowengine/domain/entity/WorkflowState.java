package com.sitc.workflowengine.domain.entity;

import com.sitc.workflowengine.application.constant.StateType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "workflow_state",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_state_code_per_workflow",
            columnNames = {"workflow_id", "code"}
        )
    }
)
public class WorkflowState extends BaseAuditableEntity {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id", nullable = false)
    private WorkflowDefinition workflow;

    @Column(nullable = false, length = 100)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StateType type;

    @Column(name = "initial_state", nullable = false)
    private Boolean initialState = false;

    @Column(name = "terminal_state", nullable = false)
    private Boolean terminalState = false;

    @Column(name = "display_order")
    private Integer displayOrder;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
