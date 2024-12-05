package com.taskmanager.backend.project.domain.services.queryservices;

import com.taskmanager.backend.project.domain.model.entities.Task;
import com.taskmanager.backend.project.domain.model.queries.taskqueries.*;
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
    List<Task> handle(GetTasksByDueDateQuery query);
}
