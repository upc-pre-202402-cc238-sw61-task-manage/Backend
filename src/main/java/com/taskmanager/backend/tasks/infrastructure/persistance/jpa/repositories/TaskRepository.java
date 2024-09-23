package com.taskmanager.backend.tasks.infrastructure.persistance.jpa.repositories;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskName(String taskName);
    boolean existsByTaskName(String taskName);

    List<Task> findAllByAssignUser(int assignUser);
}
