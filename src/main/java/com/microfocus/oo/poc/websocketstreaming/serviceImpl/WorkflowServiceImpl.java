package com.microfocus.oo.poc.websocketstreaming.serviceImpl;

import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import com.microfocus.oo.poc.websocketstreaming.repository.WorkflowRepository;
import com.microfocus.oo.poc.websocketstreaming.serviceIF.WorkflowServiceIF;
import com.microfocus.oo.poc.websocketstreaming.vos.WorkflowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "workflowService")
public class WorkflowServiceImpl implements WorkflowServiceIF {

    @Autowired
    private WorkflowRepository workflowRepository;

    @Override
    @Transactional
    public boolean createWorkflow(Workflow workflow) {
        return workflowRepository.save(workflow) != null;

    }

    @Override
    @Transactional
    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    @Override
    @Transactional
    public Flux<Workflow> getAllWorkflowsWithWebflux() {
        return Flux.fromIterable(workflowRepository.findAll());
    }

    @Override
    public List<WorkflowVO> getAllWorkflowsWithPagination(int pageNumber, int elementsPerPage) {
        List<Workflow> workflows = workflowRepository.findAll(new PageRequest(pageNumber - 1, Math.min(elementsPerPage, 1000)))
                .getContent();
        return workflows.stream()
                .map(this::convertWorkflowToVo)
                .collect(Collectors.toList());

    }

    private WorkflowVO convertWorkflowToVo(Workflow workflow) {
        return new WorkflowVO(workflow.getId(), workflow.getFlowPath(), workflow.getDescription(), workflow.getName(), workflow.getPersistenceLevel(), workflow.isEmptyValues(), workflow.getTimeoutValue());
    }
}
