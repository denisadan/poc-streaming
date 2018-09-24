package com.microfocus.oo.poc.websocketstreaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workflows")
public class Workflow {

    @Id
    @GeneratedValue
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

    @Column
    @ManyToMany(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinTable(name = "join_categories_workflows", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "workflow_id"))
    private Set<Category> categories = new HashSet<>();

    public Workflow(String flowPath,
                    String name,
                    String description,
                    String persistenceLevel,
                    boolean isEmptyValues,
                    int timeoutValue,
                    Set<Category> categories) {
        this.flowPath = flowPath;
        this.name = name;
        this.description = description;
        this.persistenceLevel = persistenceLevel;
        this.isEmptyValues = isEmptyValues;
        this.timeoutValue = timeoutValue;
        this.categories = categories;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
