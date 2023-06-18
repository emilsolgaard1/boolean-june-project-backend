package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Task;
import com.booleanuk.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByProjectId(int projectId);
    List<Task> findAllByProjectIdAndCompleted(int projectId, boolean completed);
}
