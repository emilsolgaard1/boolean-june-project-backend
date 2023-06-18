package com.booleanuk.api.repositories;

import com.booleanuk.api.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByProjectId(int projectId);
}
