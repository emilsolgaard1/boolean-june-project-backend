package com.booleanuk.api.models.DTOs.User;

public class UserReadNoProjectDTO {
    //region // FIELDS
    private int id;
    private String name;
    //endregion

    //region // PROPERTIES
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public UserReadNoProjectDTO() {}
}
