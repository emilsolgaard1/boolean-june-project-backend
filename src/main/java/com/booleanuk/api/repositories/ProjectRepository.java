package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Project;
import com.booleanuk.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
