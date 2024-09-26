package com.taskmanager.backend.tasks.domain.model.queries;

public record GetTaskByIdQuery(Long id) {
    public GetTaskByIdQuery {
        if (id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("id cannot be negative");
        }
    }
}
