package com.taskmanager.backend.project.domain.services.queryservices;

import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetTaskStatusCountQuery;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskStatusCountQueryService {
    List<TaskStatusCount> handle(GetTaskStatusCountQuery query);
}
