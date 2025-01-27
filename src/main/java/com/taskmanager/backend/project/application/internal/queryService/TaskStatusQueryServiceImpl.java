package com.taskmanager.backend.project.application.internal.queryService;

import com.taskmanager.backend.project.domain.model.entities.TaskStatus;
import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetAllTaskStatusQuery;
import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetTaskStatusByNameQuery;
import com.taskmanager.backend.project.domain.services.queryservices.TaskStatusQueryService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusQueryServiceImpl implements TaskStatusQueryService {

    private final TaskStatusRepository repository;

    public TaskStatusQueryServiceImpl(TaskStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TaskStatus> handle(GetAllTaskStatusQuery getAllTaskStatusQuery) {
        return repository.findAll();
    }

    @Override
    public Optional<TaskStatus> handle(GetTaskStatusByNameQuery query) {
        return repository.findByName(query.name());
    }
}
