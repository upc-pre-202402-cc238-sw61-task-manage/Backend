package com.taskmanager.backend.tasks.application.services;

import com.taskmanager.backend.tasks.domain.model.aggregates.Task;
import com.taskmanager.backend.tasks.domain.model.queries.GetAllTasksByUserIdQuery;
import com.taskmanager.backend.tasks.domain.model.queries.GetAllTasksQuery;
import com.taskmanager.backend.tasks.domain.model.queries.GetTaskByIdQuery;
import com.taskmanager.backend.tasks.domain.model.queries.GetTaskByNameQuery;
import com.taskmanager.backend.tasks.domain.services.TaskQueryService;
import com.taskmanager.backend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;
    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return this.taskRepository.findById(query.id());
    }

    @Override
    public Optional<Task> handle(GetTaskByNameQuery query) {
        return this.taskRepository.findByTaskName(query.name());
    }

    @Override
    public List<Task> handle(GetAllTasksQuery query) {
        return this.taskRepository.findAll();
    }

    @Override
    public List<Task> handle(GetAllTasksByUserIdQuery query) {
        return taskRepository.findAllByAssignUser(query.UserId());
    }
}
