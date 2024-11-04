package com.taskmanager.backend.tasks.domain.model.queries;

public record GetTasksByUserIdQuery(Long id) {
    public GetTasksByUserIdQuery {
        if (id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("id cannot be negative");
        }
    }
}
