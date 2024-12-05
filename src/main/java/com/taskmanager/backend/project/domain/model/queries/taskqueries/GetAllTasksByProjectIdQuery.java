package com.taskmanager.backend.project.domain.model.queries.taskqueries;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;

public record GetAllTasksByProjectIdQuery(Long projectId, Long userId, TaskStatus status) {
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
