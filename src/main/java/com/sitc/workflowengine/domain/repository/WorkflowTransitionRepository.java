package com.sitc.workflowengine.domain.repository;

import com.sitc.workflowengine.domain.entity.WorkflowTransition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowTransitionRepository extends JpaRepository<WorkflowTransition, String> {

}
