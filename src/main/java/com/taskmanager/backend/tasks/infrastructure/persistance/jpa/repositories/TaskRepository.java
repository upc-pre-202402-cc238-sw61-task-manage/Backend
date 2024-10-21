package com.taskmanager.backend.tasks.infrastructure.persistance.jpa.repositories;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskName(String taskName);
    boolean existsByTaskName(String taskName);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByProjectIdAndAssignUser(Long projectId, Long assignUser);
    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
    List<Task> findByProjectIdAndAssignUserAndStatus(Long projectId, Long assignUser, TaskStatus status);
}
