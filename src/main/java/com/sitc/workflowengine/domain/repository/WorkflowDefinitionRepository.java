package com.sitc.workflowengine.domain.repository;

import com.sitc.workflowengine.domain.entity.WorkflowDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowDefinitionRepository extends JpaRepository<WorkflowDefinition, String> {

}
