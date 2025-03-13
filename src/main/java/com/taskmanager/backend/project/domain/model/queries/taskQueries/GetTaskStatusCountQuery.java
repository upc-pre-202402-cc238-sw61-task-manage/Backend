package com.taskmanager.backend.project.domain.model.queries.taskQueries;

public record GetTaskStatusCountQuery(Long projectId) {
    public GetTaskStatusCountQuery {
        if (projectId == null){
            throw new IllegalArgumentException("projectId cannot be null");
        }
        if (projectId < 0){
            throw new IllegalArgumentException("projectId cannot be negative");
        }
    }
}
