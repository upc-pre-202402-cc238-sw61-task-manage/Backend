package com.taskmanager.backend.tasks.domain.model.queries;

import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;

public record GetAllTasksByProjectIdQuery(Long projectId, Long userId, TaskStatus status) {
    public GetAllTasksByProjectIdQuery {
        if (projectId == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        if (projectId < 0){
            throw new IllegalArgumentException("id cannot be negative");
        }
        if (userId != null && userId < 0) {
            throw new IllegalArgumentException("userId cannot be negative");
        }

    }
}
