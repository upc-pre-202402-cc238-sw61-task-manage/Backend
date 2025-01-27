package com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.project.domain.model.entities.TaskStatus;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the TaskStatus entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    Optional<TaskStatus> findByName(TaskStatusList status);
    boolean existsByName(TaskStatusList status);
}
