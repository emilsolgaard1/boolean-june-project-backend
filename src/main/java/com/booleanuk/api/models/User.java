package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    //region // FIELDS //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "user")
    private ProjectMaster projectMaster;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties("users")
    private Project project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    public User() {

    }
    public User(int id) {
        this.id = id;
    }
    public User(ProjectMaster projectMaster, Project project, String name) {
        this.projectMaster = projectMaster;
        this.project = project;
        this.name = name;
    }
}
