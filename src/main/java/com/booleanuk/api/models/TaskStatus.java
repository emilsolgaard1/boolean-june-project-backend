package com.booleanuk.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "task_statuses")
public class TaskStatus {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type", nullable = false)
    private String type;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //endregion

    public TaskStatus() {

    }
    public TaskStatus(int id) {
        this.id = id;
    }
}
