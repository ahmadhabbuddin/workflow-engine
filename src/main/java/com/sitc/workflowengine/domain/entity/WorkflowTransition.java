package com.sitc.workflowengine.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "workflow_transition")
public class WorkflowTransition extends BaseAuditableEntity {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id", nullable = false)
    private WorkflowDefinition workflow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_state_id", nullable = false)
    private WorkflowState fromState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_state_id", nullable = false)
    private WorkflowState toState;

    @Column(name = "action_name", nullable = false, length = 100)
    private String actionName;

    @Column(length = 500)
    private String description;

    @Column(name = "condition_expression", length = 1000)
    private String conditionExpression;

    @Column(name = "required_role", length = 100)
    private String requiredRole;

    @Column(name = "auto_execute", nullable = false)
    private Boolean autoExecute = false;

    @Column(name = "timeout_hours")
    private Integer timeoutHours;

    @Column(nullable = false)
    private Integer priority = 0;

    @Column(name = "effective_from")
    private Instant effectiveFrom;

    @Column(name = "effective_to")
    private Instant effectiveTo;

    @Column(nullable = false)
    private Boolean active = true;

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