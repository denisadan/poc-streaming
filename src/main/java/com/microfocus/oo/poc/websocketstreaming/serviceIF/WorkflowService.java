package com.microfocus.oo.poc.websocketstreaming.serviceIF;

import com.microfocus.oo.poc.websocketstreaming.model.Category;
import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import com.microfocus.oo.poc.websocketstreaming.vos.WorkflowVO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface WorkflowService {

    boolean createWorkflow(Workflow workflow);

    boolean createCategory(Category category);

    List<Workflow> getAllWorkflows();

    Flux<Workflow> getAllWorkflowsWithWebflux();

    List<WorkflowVO> getAllWorkflowsWithPagination(int pageNumber, int elementsPerPage);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();
}
