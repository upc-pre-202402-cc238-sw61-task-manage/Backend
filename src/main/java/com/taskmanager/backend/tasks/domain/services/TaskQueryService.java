package com.taskmanager.backend.tasks.domain.services;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.queries.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskQueryService {
    Optional<Task> handle (GetTaskByIdQuery query);
    Optional<Task> handle (GetTaskByNameQuery query);
    List<Task> handle (GetAllTasksQuery query);
    List<Task> handle(GetAllTasksByProjectIdQuery query);
    List<Task> handle(GetTasksByUserIdQuery query);
}
