package com.booleanuk.api.models.DTOs.User;

import com.booleanuk.api.models.Project;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateProjectDTO {
    //region // FIELDS
    @JsonProperty("projectId")
    private Project project;
    //endregion

    //region // PROPERTIES
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    //endregion

    public UserUpdateProjectDTO() {}
}
