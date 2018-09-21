package com.microfocus.oo.poc.websocketstreaming.controller;

import com.microfocus.oo.poc.websocketstreaming.model.Workflow;
import com.microfocus.oo.poc.websocketstreaming.serviceIF.WorkflowServiceIF;
import com.microfocus.oo.poc.websocketstreaming.vos.WorkflowVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class WorkflowController {

    @Autowired
    private WorkflowServiceIF workflowService;

    @ApiOperation(value = "Get workflows",
            notes = "Get workflows")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    @GetMapping(value = {"/workflow"})
    public ResponseEntity<String> getAllWorkflows() {
        workflowService.getAllWorkflows();
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ApiOperation(value = "Create new workflow",
            notes = "Create new workflow")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    @PostMapping(value = {"/workflow"})
    public ResponseEntity<String> createWorkflow(@RequestBody Workflow workflow) {
        if (workflowService.createWorkflow(workflow)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Get workflows with webflux",
            notes = "Get workflows")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    @GetMapping(value = {"/workflow-webflux"})
    public Flux<Workflow> getAllWorkflowsUsingWebflux() {
        return workflowService.getAllWorkflowsWithWebflux();
    }


    @ApiOperation(value = "Get workflows with pagination",
            notes = "Get workflows")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict")
    })
    @GetMapping(value = {"/workflow-pagination"})
    public ResponseEntity<List<WorkflowVO>> getAllWorkflowsUsingPagination(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {
        final List<WorkflowVO> workflowList = workflowService.getAllWorkflowsWithPagination(pageNum, pageSize);
        return new ResponseEntity<>(workflowList, HttpStatus.OK);

    }
}