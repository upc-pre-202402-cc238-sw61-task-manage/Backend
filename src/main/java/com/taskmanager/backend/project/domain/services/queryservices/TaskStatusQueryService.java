package com.taskmanager.backend.project.domain.services.queryservices;

import com.taskmanager.backend.project.domain.model.entities.TaskStatus;
import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetAllTaskStatusQuery;
import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetTaskStatusByNameQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskStatusQueryService {
    List<TaskStatus> handle(GetAllTaskStatusQuery getAllTaskStatusQuery);
    Optional<TaskStatus> handle(GetTaskStatusByNameQuery query);
}
