package com.microfocus.oo.poc.websocketstreaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "workflows")
public class Workflow {

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(name = "question_generator", sequenceName = "question_sequence", initialValue = 1000)
    private Long id;

    @Column
    private String flowPath;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String persistenceLevel;

    @Column
    private boolean isEmptyValues;

    @Column
    private int timeoutValue;

//    @Column
//    private Set<Category> categories;
//
//    @Column
//    private Set<String> entitlements;
//
//    @Column
//    private Set<String> inputs;
//
//    @Column
//    private Set<String> outputs;

//    public Workflow(String flowPath, String name, String description, String persistenceLevel, boolean isEmptyValues, int timeoutValue, Set<Category> categories, Set<String> entitlements, Set<String> inputs, Set<String> outputs) {
//        this.flowPath = flowPath;
//        this.name = name;
//        this.description = description;
//        this.persistenceLevel = persistenceLevel;
//        this.isEmptyValues = isEmptyValues;
//        this.timeoutValue = timeoutValue;
//        this.categories = categories;
//        this.entitlements = entitlements;
//        this.inputs = inputs;
//        this.outputs = outputs;
//    }


    public Workflow(String flowPath, String name, String description, String persistenceLevel, boolean isEmptyValues, int timeoutValue) {
        this.flowPath = flowPath;
        this.name = name;
        this.description = description;
        this.persistenceLevel = persistenceLevel;
        this.isEmptyValues = isEmptyValues;
        this.timeoutValue = timeoutValue;
    }

    public Workflow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowPath() {
        return flowPath;
    }

    public void setFlowPath(String flowPath) {
        this.flowPath = flowPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersistenceLevel() {
        return persistenceLevel;
    }

    public void setPersistenceLevel(String persistenceLevel) {
        this.persistenceLevel = persistenceLevel;
    }

    public boolean isEmptyValues() {
        return isEmptyValues;
    }

    public void setEmptyValues(boolean emptyValues) {
        isEmptyValues = emptyValues;
    }

    public int getTimeoutValue() {
        return timeoutValue;
    }

    public void setTimeoutValue(int timeoutValue) {
        this.timeoutValue = timeoutValue;
    }

}
