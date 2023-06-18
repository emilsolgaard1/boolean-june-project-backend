package com.booleanuk.api.models.DTOs.Task;

import com.booleanuk.api.models.Developer;
import com.booleanuk.api.models.ProductOwner;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class TaskUpdateDTO {
    //region // FIELDS //
    @JsonProperty("productOwnerId")
    private ProductOwner productOwner;
    @JsonProperty("developerId")
    private Developer developer;
    private int estimateMinutes;
    private boolean completed;
    private int spentMinutes;
    private ZonedDateTime startedAt;
    private ZonedDateTime completedAt;
    private String title;
    private String description;
    //endregion

    //region // PROPERTIES //
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

    public TaskUpdateDTO() {}
}
