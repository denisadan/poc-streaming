package com.microfocus.oo.poc.websocketstreaming.serviceImpl;

import com.microfocus.oo.poc.websocketstreaming.model.Category;
import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import com.microfocus.oo.poc.websocketstreaming.repository.CategoryRepository;
import com.microfocus.oo.poc.websocketstreaming.repository.NormalWorkflowRepository;
import com.microfocus.oo.poc.websocketstreaming.repository.WebfluxWorkflowRepository;
import com.microfocus.oo.poc.websocketstreaming.serviceIF.WorkflowService;
import com.microfocus.oo.poc.websocketstreaming.vos.WorkflowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "workflowService")
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WebfluxWorkflowRepository webfluxWorkflowRepository;

    @Autowired
    private NormalWorkflowRepository normalWorkflowRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public boolean createWorkflow(Workflow workflow) {
        workflow.setCategories(getCategoryForWorkflow(workflow));
        return webfluxWorkflowRepository.save(workflow) != null;

    }

    @Override
    @Transactional
    public Flux<Workflow> getAllWorkflowsWithWebflux() {
        return webfluxWorkflowRepository.findAll();
    }

    @Override
    @Transactional
    public List<Workflow> getAllWorkflows() {
        return normalWorkflowRepository.findAll();
    }

    @Override
    @Transactional
    public List<WorkflowVO> getAllWorkflowsWithPagination(int pageNumber, int elementsPerPage) {

//        List<Workflow> workflows = webfluxWorkflowRepository.findAll(PageRequest.of(pageNumber - 1,
//                1000,
//                Sort.by("id").descending()))
//                .getContent();
//
//        return workflows.stream()
//                .map(this::convertWorkflowToVo)
//                .collect(Collectors.toList());
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public boolean createCategory(Category category) {
        return categoryRepository.save(category) != null;
    }


    private Set<Category> getCategoryForWorkflow(Workflow workflow) {
        Set<Category> workflowCategories = workflow.getCategories();
        Set<Category> allCategories = new HashSet<>();

        if (workflowCategories.size() != 0) {
            for (Category category : workflowCategories) {
                Category existingCategory = categoryRepository.findByName(category.getName());
                if (existingCategory != null) {
                    allCategories.add(existingCategory);
                }
            }
        } else {
            List<Category> all = categoryRepository.findAll();
            Collections.shuffle(all);
            allCategories.add(all.get(2));
            allCategories.add(all.get(3));
            allCategories.add(all.get(5));
            allCategories.add(all.get(1));
        }
        return allCategories;
    }

    private WorkflowVO convertWorkflowToVo(Workflow workflow) {
        return new WorkflowVO(workflow.getId(), workflow.getFlowPath(), workflow.getDescription(), workflow.getName(), workflow.getPersistenceLevel(), workflow.isEmptyValues(), workflow.getTimeoutValue());
    }

    @Override
    @Transactional
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
