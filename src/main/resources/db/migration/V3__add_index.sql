CREATE INDEX idx_workflow_definition_name
    ON workflow_definition (name);

CREATE INDEX idx_workflow_definition_status
    ON workflow_definition (status);

CREATE INDEX idx_workflow_definition_active
    ON workflow_definition (active);

CREATE INDEX idx_workflow_definition_deleted
    ON workflow_definition (deleted);


CREATE INDEX idx_state_workflow
    ON workflow_state (workflow_id);

CREATE INDEX idx_state_type
    ON workflow_state (type);

CREATE INDEX idx_state_deleted
    ON workflow_state (deleted);


CREATE INDEX idx_transition_workflow
    ON workflow_transition (workflow_id);

CREATE INDEX idx_transition_from_state
    ON workflow_transition (from_state_id);

CREATE INDEX idx_transition_action
    ON workflow_transition (action_name);

CREATE INDEX idx_transition_auto
    ON workflow_transition (auto_execute);

CREATE INDEX idx_transition_active
    ON workflow_transition (active);