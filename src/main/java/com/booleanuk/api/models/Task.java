package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties("tasks")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "product_owner_id", nullable = false)
    @JsonIgnoreProperties("tasks")
    private ProductOwner productOwner;
    @ManyToOne
    @JoinColumn(name = "developer_id", unique = true)
    @JsonIgnoreProperties("tasks")
    private Developer developer;
    @Column(name = "estimate_minutes", nullable = false)
    private int estimateMinutes;
    @Column(name = "completed", nullable = false)
    private boolean completed;
    @Column(name = "spent_minutes", nullable = false)
    private int spentMinutes;
    @Column(name = "started_at")
    private ZonedDateTime startedAt;
    @Column(name = "completed_at")
    private ZonedDateTime completedAt;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProductOwner getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(ProductOwner productOwner) {
        this.productOwner = productOwner;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public int getEstimateMinutes() {
        return estimateMinutes;
    }

    public void setEstimateMinutes(int estimateMinutes) {
        this.estimateMinutes = estimateMinutes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getSpentMinutes() {
        return spentMinutes;
    }

    public void setSpentMinutes(int spentMinutes) {
        this.spentMinutes = spentMinutes;
    }

    public ZonedDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(ZonedDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public ZonedDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(ZonedDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    public Task() {

    }
    public Task(int id) {
        this.id = id;
    }
    public Task(Project project, ProductOwner productOwner, int estimateMinutes, String title, String description) {
        this.project = project;
        this.productOwner = productOwner;
        this.estimateMinutes = estimateMinutes;
        this.completed = false;
        this.spentMinutes = 0;
        this.title = title;
        this.description = description;
    }
}
