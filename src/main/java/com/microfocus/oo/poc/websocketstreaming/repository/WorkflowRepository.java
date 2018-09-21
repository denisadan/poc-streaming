package com.microfocus.oo.poc.websocketstreaming.repository;

import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {

}

