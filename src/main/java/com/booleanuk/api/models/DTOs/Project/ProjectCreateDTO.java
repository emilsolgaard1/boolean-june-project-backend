package com.booleanuk.api.models.DTOs.Project;

import com.booleanuk.api.models.ProjectMaster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectCreateDTO {
    //region // FIELDS //
    @JsonProperty("projectMasterId")
    private ProjectMaster projectMaster;
    private String name;
    //endregion

    //region // PROPERTIES //
    public ProjectMaster getProjectMaster() {
        return projectMaster;
    }

    public void setProjectMaster(ProjectMaster projectMaster) {
        this.projectMaster = projectMaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public ProjectCreateDTO() {}
}
