package com.booleanuk.api.models.DTOs.User;

public class UserReadDTO {
    //region // FIELDS
    private int id;
    private Integer projectId;
    private String name;
    //endregion

    //region // PROPERTIES
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public UserReadDTO() {}
}
