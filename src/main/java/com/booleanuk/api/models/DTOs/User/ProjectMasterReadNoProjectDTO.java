package com.booleanuk.api.models.DTOs.User;

public class ProjectMasterReadNoProjectDTO {
    //region // FIELDS
    private UserReadNoProjectDTO user;
    //endregion

    //region // PROPERTIES
    public UserReadNoProjectDTO getUser() {
        return user;
    }

    public void setUser(UserReadNoProjectDTO user) {
        this.user = user;
    }
    //endregion

    public ProjectMasterReadNoProjectDTO() {}
}
