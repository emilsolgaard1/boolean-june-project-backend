package com.booleanuk.api.controllers;

import com.booleanuk.api.models.DTOs.Task.TaskCreateDTO;
import com.booleanuk.api.models.DTOs.Task.TaskReadDTO;
import com.booleanuk.api.models.DTOs.Task.TaskUpdateDTO;
import com.booleanuk.api.models.Project;
import com.booleanuk.api.models.Task;
import com.booleanuk.api.repositories.ProjectRepository;
import com.booleanuk.api.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(exposedHeaders = "*")
@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;

    // ------------------ ENDPOINTS ------------------//
    //region // POST //
    @PostMapping
    @RequestMapping("projects/{projectId}/tasks")
    public ResponseEntity<String> create(@PathVariable int projectId, @RequestBody TaskCreateDTO task) {
        Project project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No project with that id was found."
                ));

        Task taskToCreate = modelMapper.map(task, Task.class);
        taskToCreate.setProject(project);

        Task newTask;
        try {
            newTask = this.taskRepository.save(taskToCreate);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more values are missing or invalid."
            );
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{taskId}")
                        .buildAndExpand(newTask.getId())
                        .toUri())
                .body("Created successfully.");
    }
    //endregion
    //region // GET //
    @GetMapping("tasks/{taskId}")
    public ResponseEntity<TaskReadDTO> get(@PathVariable int taskId) {
        Task task = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No task with that taskId was found."
                ));

        return ResponseEntity.ok(modelMapper
                .map(task, TaskReadDTO.class)
        );
    }
    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskReadDTO>> getAllFromProject(@PathVariable int projectId) {
        List<Task> tasks = this.taskRepository.findAllByProjectId(projectId);

        if(tasks.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No tasks were found with that projectId."
            );

        return ResponseEntity.ok(tasks
                .stream()
                .map(task -> modelMapper
                        .map(task, TaskReadDTO.class))
                .collect(Collectors.toList())
        );
    }
    @GetMapping("/projects/{projectId}/completed-tasks")
    public ResponseEntity<List<TaskReadDTO>> getAllCompletedFromProject(@PathVariable int projectId) {
        List<Task> tasks = this.taskRepository.findAllByProjectIdAndCompleted(projectId, true);

        if(tasks.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No completed tasks were found with that projectId."
            );

        return ResponseEntity.ok(tasks
                .stream()
                .map(task -> modelMapper
                        .map(task, TaskReadDTO.class))
                .collect(Collectors.toList())
        );
    }
    //endregion
    //region // PUT //
    @PutMapping("tasks/{taskId}")
    public ResponseEntity<String> update(@PathVariable int taskId, @RequestBody TaskUpdateDTO task) {
        Task taskToUpdate = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No task with that taskId was found."
                ));

        boolean changed = false;

        if(!Objects.equals(taskToUpdate.getProductOwner(), task.getProductOwner())) {
            taskToUpdate.setProductOwner(task.getProductOwner());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getDeveloper(), task.getDeveloper())) {
            taskToUpdate.setDeveloper(task.getDeveloper());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getEstimateMinutes(), task.getEstimateMinutes())) {
            taskToUpdate.setEstimateMinutes(task.getEstimateMinutes());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.isCompleted(), task.isCompleted())) {
            taskToUpdate.setCompleted(task.isCompleted());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getSpentMinutes(), task.getSpentMinutes())) {
            taskToUpdate.setSpentMinutes(task.getSpentMinutes());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getStartedAt(), task.getStartedAt())) {
            taskToUpdate.setStartedAt(task.getStartedAt());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getCompletedAt(), task.getCompletedAt())) {
            taskToUpdate.setCompletedAt(task.getCompletedAt());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getTitle(), task.getTitle())) {
            taskToUpdate.setTitle(task.getTitle());
            changed = true;
        }
        if(!Objects.equals(taskToUpdate.getDescription(), task.getDescription())) {
            taskToUpdate.setDescription(task.getDescription());
            changed = true;
        }

        if(changed) {
            try {
                this.taskRepository.save(taskToUpdate);
            } catch (Exception ex) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "One or more values are missing or invalid."
                );
            }
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .build()
                        .toUri())
                .body("Updated successfully.");
    }
    //endregion
    //region // DELETE //
    @DeleteMapping("tasks/{taskId}")
    public ResponseEntity<String> delete(@PathVariable int taskId) {
        Task taskToDelete = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No task with that id was found."
                ));

        this.taskRepository.delete(taskToDelete);

        return ResponseEntity
                .ok("Deleted successfully.");
    }
    //endregion
}