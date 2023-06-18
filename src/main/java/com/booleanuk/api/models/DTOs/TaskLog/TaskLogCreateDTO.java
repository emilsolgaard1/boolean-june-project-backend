package com.booleanuk.api.models.DTOs.TaskLog;

import com.booleanuk.api.models.Developer;
import com.booleanuk.api.models.ProductOwner;
import com.booleanuk.api.models.Task;
import com.booleanuk.api.models.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.ZonedDateTime;

public class TaskLogCreateDTO {
    //region // FIELDS //
    @JsonProperty("developerId")
    private Developer developer;
    @JsonProperty("taskId")
    private Task task;
    @JsonProperty("taskStatusId")
    private TaskStatus taskStatus;
    private ZonedDateTime loggedAt;
    private String title;
    private String description;
    //endregion

    //region // PROPERTIES //
    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public ZonedDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(ZonedDateTime loggedAt) {
        this.loggedAt = loggedAt;
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

    public TaskLogCreateDTO() {
        this.loggedAt = ZonedDateTime.now();
    }
}
