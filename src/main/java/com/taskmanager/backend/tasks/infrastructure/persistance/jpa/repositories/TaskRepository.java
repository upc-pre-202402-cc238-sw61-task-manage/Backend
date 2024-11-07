package com.taskmanager.backend.tasks.infrastructure.persistance.jpa.repositories;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskName(String taskName);
    boolean existsByTaskNameAndProjectId(String taskName, Long projectId);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByUserId(Long userId);
    List<Task> findByProjectIdAndUserId(Long projectId, Long userId);
    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
    List<Task> findByProjectIdAndUserIdAndStatus(Long projectId, Long userId, TaskStatus status);
    List<Task> findByDueDate(LocalDate localDate);
}
