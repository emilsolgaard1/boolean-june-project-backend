package com.booleanuk.api.controllers;

import com.booleanuk.api.models.DTOs.TaskLog.TaskLogCreateDTO;
import com.booleanuk.api.models.DTOs.TaskLog.TaskLogReadDTO;
import com.booleanuk.api.models.DTOs.User.UserReadNoProjectDTO;
import com.booleanuk.api.models.TaskLog;
import com.booleanuk.api.repositories.TaskLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskLogController {
    @Autowired
    private TaskLogRepository taskLogRepository;
    @Autowired
    private ModelMapper modelMapper;

    // ------------------ ENDPOINTS ------------------//
    //region // POST //
    @PostMapping
    @RequestMapping("task-logs")
    public ResponseEntity<String> create(@RequestBody TaskLogCreateDTO taskLog) {
        TaskLog taskLogToCreate = this.modelMapper.map(taskLog, TaskLog.class);

        TaskLog newTaskLog;
        try {
            newTaskLog = this.taskLogRepository.save(taskLogToCreate);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more values are missing or invalid."
            );
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{taskLogId}")
                        .buildAndExpand(newTaskLog.getId())
                        .toUri())
                .body("Created successfully.");
    }
    //endregion
    //region // GET //
    @GetMapping("task-logs/{taskLogId}")
    public ResponseEntity<TaskLogReadDTO> get(@PathVariable int taskLogId) {
        TaskLog taskLog = this.taskLogRepository.findById(taskLogId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No taskLog with that id was found."
                ));

        return ResponseEntity.ok(this.modelMapper
                .map(taskLog, TaskLogReadDTO.class)
        );
    }
    @GetMapping("projects/{projectId}/latest-5-task-logs")
    public ResponseEntity<List<TaskLogReadDTO>> getLatestFive(@PathVariable int projectId) {
        List<TaskLog> taskLogs = this.taskLogRepository.findTop5ByTaskProjectIdOrderByLoggedAtDesc(projectId);

        if(taskLogs.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No task logs were found with that projectId."
            );

        return ResponseEntity.ok(taskLogs
                .stream()
                .map(taskLog -> modelMapper
                        .map(taskLog, TaskLogReadDTO.class))
                .collect(Collectors.toList())
        );
    }
    //endregion
}