package com.taskmanager.backend.project.domain.model.queries.taskqueries;

public record GetTaskByIdQuery(Long id) {
    public GetTaskByIdQuery {
        if (id == null){
            throw new IllegalArgumentException("taskId cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("taskId cannot be negative");
        }
    }
}
