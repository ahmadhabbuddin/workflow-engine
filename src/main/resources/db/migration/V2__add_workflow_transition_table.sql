CREATE TABLE workflow_transition (
    id                          VARCHAR(36)    NOT NULL,
    workflow_id                 VARCHAR(36)    NOT NULL,
    from_state_id               VARCHAR(36)    NOT NULL,
    to_state_id                 VARCHAR(36)    NOT NULL,
    action_name                 VARCHAR(100)   NOT NULL,
    description                 VARCHAR(500),

    -- branching condition
    condition_expression        VARCHAR(1000),

    -- role restriction
    required_role               VARCHAR(100),

    -- automation
    auto_execute                BOOLEAN        NOT NULL DEFAULT FALSE,
    timeout_hours               INT,

    -- priority (if multiple condition match)
    priority                    INT            NOT NULL DEFAULT 0,

    -- validity window
    effective_from              TIMESTAMP,
    effective_to                TIMESTAMP,

    -- flags
    active                      BOOLEAN        NOT NULL DEFAULT TRUE,
    deleted                     BOOLEAN        NOT NULL DEFAULT FALSE,

    -- audit
    created_at                  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by                  VARCHAR(100)   NOT NULL,
    updated_at                  TIMESTAMP,
    updated_by                  VARCHAR(100),

    CONSTRAINT pk_workflow_transition
        PRIMARY KEY (id),

    CONSTRAINT chk_timeout_positive
        CHECK (timeout_hours IS NULL OR timeout_hours > 0)
);