package com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
    boolean existsByTitleAndProjectId(String title, Long projectId);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByUserId(Long userId);
    List<Task> findByProjectIdAndUserId(Long projectId, Long userId);
    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
    List<Task> findByProjectIdAndUserIdAndStatus(Long projectId, Long userId, TaskStatus status);
    List<Task> findByDueDate(LocalDate localDate);
}
