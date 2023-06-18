package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "task_logs")
public class TaskLog {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @ManyToOne
    @JoinColumn(name = "task_status_id", nullable = false)
    private TaskStatus taskStatus;
    @Column(name = "logged_at", nullable = false)
    private ZonedDateTime loggedAt;
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

    public TaskLog() {

    }
    public TaskLog(int id) {
        this.id = id;
    }
}
