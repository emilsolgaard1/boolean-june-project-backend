package com.booleanuk.api.models.DTOs.User;

public class UserCreateDTO {
    //region // FIELDS
    private String name;
    //endregion

    //region // PROPERTIES
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public UserCreateDTO() {}
}
