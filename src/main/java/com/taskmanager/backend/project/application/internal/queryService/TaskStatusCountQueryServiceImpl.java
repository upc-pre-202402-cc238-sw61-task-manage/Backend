package com.taskmanager.backend.project.application.internal.queryService;

import com.taskmanager.backend.project.domain.model.queries.taskQueries.GetTaskStatusCountQuery;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusCount;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import com.taskmanager.backend.project.domain.services.queryservices.TaskStatusCountQueryService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusCountQueryServiceImpl implements TaskStatusCountQueryService {

    private final TaskRepository taskRepository;

    public TaskStatusCountQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskStatusCount> handle(GetTaskStatusCountQuery query) {
        List<Object[]> response = taskRepository.getTaskStatusCountByProjectId(query.projectId());
        return response.stream()
                .map(obj -> new TaskStatusCount(TaskStatusList.valueOf((String) obj[0]), (long) obj[1]))
                .toList();
    }
}
