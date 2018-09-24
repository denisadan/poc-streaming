package com.microfocus.oo.poc.websocketstreaming.controller;

import com.microfocus.oo.poc.websocketstreaming.model.Category;
import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import com.microfocus.oo.poc.websocketstreaming.serviceIF.WorkflowService;
import com.microfocus.oo.poc.websocketstreaming.vos.WorkflowVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @ApiOperation(value = "Get all workflows")
    @GetMapping(value = {"/workflows"})
    public ResponseEntity<List<Workflow>> getAllWorkflows() {
        List<Workflow> workflows = workflowService.getAllWorkflows();
        return new ResponseEntity<>(workflows, OK);

    }

    @ApiOperation(value = "Create a new workflow")
    @PostMapping(value = {"/workflow"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createWorkflow(@RequestBody Workflow workflow) {
        if (workflowService.createWorkflow(workflow)) {
            return new ResponseEntity<>(CREATED);
        } else {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Get all  workflows using webflux")
    @GetMapping(value = {"/workflow-webflux"})
    public Flux<Workflow> getAllWorkflowsUsingWebflux() {
        return workflowService.getAllWorkflowsWithWebflux();
    }

    @ApiOperation(value = "Get all workflows using pagination")
    @GetMapping(value = {"/workflow-pagination"})
    public ResponseEntity<List<WorkflowVO>> getAllWorkflowsUsingPagination(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
        final List<WorkflowVO> workflowList = workflowService.getAllWorkflowsWithPagination(pageNum, pageSize);
        return new ResponseEntity<>(workflowList, OK);
    }

    @ApiOperation(value = "Create a new category")
    @PostMapping(value = {"/category"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCategories(@RequestBody Category category) {
        if (workflowService.createCategory(category)) {
            return new ResponseEntity<>(CREATED);
        } else {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Get all categories ")
    @GetMapping(value = {"/categories"})
    public List<Category> getCategories() {
        return workflowService.getAllCategories();
    }
}