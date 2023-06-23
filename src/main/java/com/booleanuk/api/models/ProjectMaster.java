package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "project_masters")
public class ProjectMaster {
    //region // FIELDS //
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @MapsId
    private User user;
    @OneToOne(mappedBy = "projectMaster")
    @JsonIgnoreProperties("projectMaster")
    private Project project;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    //endregion

    public ProjectMaster() {

    }
    public ProjectMaster(int id) {
        this.id = id;
    }
    public ProjectMaster(int id, Project project) {
        this.id = id;
        this.project = project;
    }
}
