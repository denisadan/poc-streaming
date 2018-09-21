package com.microfocus.oo.poc.websocketstreaming.vos;

public class WorkflowVO {

    private Long id;

    private String flowPath;

    private String name;

    private String description;


    private String persistenceLevel;

    private boolean isEmptyValues;

    private int timeoutValue;

    public WorkflowVO() {
    }

    public WorkflowVO(Long id, String flowPath, String name, String description, String persistenceLevel, boolean isEmptyValues, int timeoutValue) {
        this.id = id;
        this.flowPath = flowPath;
        this.name = name;
        this.description = description;
        this.persistenceLevel = persistenceLevel;
        this.isEmptyValues = isEmptyValues;
        this.timeoutValue = timeoutValue;
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
