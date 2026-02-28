CREATE TABLE workflow_definition (
    id                  VARCHAR(36)    NOT NULL,
    name                VARCHAR(150)   NOT NULL,
    description         VARCHAR(500),
    version             INT            NOT NULL,
    active              BOOLEAN        NOT NULL DEFAULT TRUE,

    -- lifecycle
    status              VARCHAR(30)    NOT NULL DEFAULT 'DRAFT',

    -- optimistic locking
    lock_version        INT            NOT NULL DEFAULT 0,

    -- audit fields
    created_at          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by          VARCHAR(100)   NOT NULL,
    updated_at          TIMESTAMP,
    updated_by          VARCHAR(100),

    -- soft delete
    deleted             BOOLEAN        NOT NULL DEFAULT FALSE,
    deleted_at          TIMESTAMP,
    deleted_by          VARCHAR(100),

    CONSTRAINT pk_workflow_definition
        PRIMARY KEY (id),

    CONSTRAINT uq_workflow_name_version
        UNIQUE (name, version),

    CONSTRAINT chk_workflow_status
        CHECK (status IN ('DRAFT','PUBLISHED','DEPRECATED'))
);


CREATE TABLE workflow_state (
    id                      VARCHAR(36)    NOT NULL,
    workflow_id             VARCHAR(36)    NOT NULL,
    code                    VARCHAR(100)   NOT NULL,
    name                    VARCHAR(150)   NOT NULL,
    description             VARCHAR(500),
    type                    VARCHAR(30)    NOT NULL,

    -- flags
    initial_state           BOOLEAN        NOT NULL DEFAULT FALSE,
    terminal_state          BOOLEAN        NOT NULL DEFAULT FALSE,

    -- ordering / visualization
    display_order           INT,

    -- audit
    created_at              TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by              VARCHAR(100)   NOT NULL,
    updated_at              TIMESTAMP,
    updated_by              VARCHAR(100),

    deleted                 BOOLEAN        NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_workflow_state
        PRIMARY KEY (id),

    CONSTRAINT uq_state_code_per_workflow
        UNIQUE (workflow_id, code),

    CONSTRAINT chk_state_type
        CHECK (type IN ('START','INTERMEDIATE','END'))
);

