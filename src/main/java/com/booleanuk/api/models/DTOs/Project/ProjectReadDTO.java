package com.booleanuk.api.models.DTOs.Project;

import com.booleanuk.api.models.DTOs.User.ProjectMasterReadNoProjectDTO;
import com.booleanuk.api.models.DTOs.User.UserReadNoProjectDTO;

import java.util.List;

public class ProjectReadDTO {
    //region // FIELDS //
    private int id;
    private ProjectMasterReadNoProjectDTO projectMaster;
    private List<UserReadNoProjectDTO> users;
    private String name;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectMasterReadNoProjectDTO getProjectMaster() {
        return projectMaster;
    }

    public void setProjectMaster(ProjectMasterReadNoProjectDTO projectMaster) {
        this.projectMaster = projectMaster;
    }

    public List<UserReadNoProjectDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserReadNoProjectDTO> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public ProjectReadDTO() {}
}
