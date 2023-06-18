package com.booleanuk.api.controllers;

import com.booleanuk.api.models.DTOs.Project.ProjectCreateDTO;
import com.booleanuk.api.models.DTOs.Project.ProjectReadDTO;
import com.booleanuk.api.models.Project;
import com.booleanuk.api.models.ProjectMaster;
import com.booleanuk.api.repositories.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;

    // ------------------ ENDPOINTS ------------------//
    //region // POST //
    @PostMapping
    @RequestMapping("projects")
    public ResponseEntity<String> create(@RequestBody ProjectCreateDTO project) {
        Project projectToCreate = this.modelMapper.map(project, Project.class);

        Project newProject;
        try {
            newProject = this.projectRepository.save(projectToCreate);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more values are missing or invalid."
            );
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{projectId}")
                        .buildAndExpand(newProject.getId())
                        .toUri())
                .body("Created successfully.");
    }
    //endregion
    //region // GET //
    @GetMapping("projects/{projectId}")
    public ResponseEntity<ProjectReadDTO> get(@PathVariable int projectId) {
        Project project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No project with that id was found."
                ));

        return ResponseEntity.ok(this.modelMapper
                .map(project, ProjectReadDTO.class)
        );
    }
    //endregion
}