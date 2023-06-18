package com.booleanuk.api.repositories;

import com.booleanuk.api.models.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskLogRepository extends JpaRepository<TaskLog, Integer> {
    List<TaskLog> findTop5ByTaskProjectIdOrderByLoggedAtDesc(int projectId);
}
