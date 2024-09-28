package com.taskmanager.backend.tasks.domain.services;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.queries.GetAllTasksQuery;
import com.taskmanager.backend.tasks.domain.model.queries.GetTaskByIdQuery;
import com.taskmanager.backend.tasks.domain.model.queries.GetTaskByNameQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskQueryService {
    Optional<Task> handle (GetTaskByIdQuery query);
    Optional<Task> handle (GetTaskByNameQuery query);
    List<Task> handle (GetAllTasksQuery query);
}