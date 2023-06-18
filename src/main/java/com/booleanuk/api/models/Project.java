package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "project_master_id", nullable = false)
    @JsonIgnoreProperties("project")
    private ProjectMaster projectMaster;
    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    private List<User> users;
    @Column(name = "name", nullable = false)
    private String name;
    //endregion

    //region // PROPERTIES //
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectMaster getProjectMaster() {
        return projectMaster;
    }

    public void setProjectMaster(ProjectMaster projectMaster) {
        this.projectMaster = projectMaster;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public Project() {

    }
    public Project(int id) {
        this.id = id;
    }
    public Project(ProjectMaster projectMaster, List<User> users, String name) {
        this.projectMaster = projectMaster;
        this.users = users;
        this.name = name;
    }
}
