package com.taskmanager.backend.project.domain.model.queries.taskQueries;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;

public record GetAllTasksByProjectIdQuery(Long projectId, Long userId, TaskStatusList status) {
    public GetAllTasksByProjectIdQuery {
        if (projectId == null){
            throw new IllegalArgumentException("taskId cannot be null");
        }
        if (projectId < 0){
            throw new IllegalArgumentException("taskId cannot be negative");
        }
        if (userId != null && userId < 0) {
            throw new IllegalArgumentException("userId cannot be negative");
        }

    }
}
