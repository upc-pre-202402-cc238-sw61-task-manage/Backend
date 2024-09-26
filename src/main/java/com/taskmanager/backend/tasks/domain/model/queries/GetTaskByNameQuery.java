package com.taskmanager.backend.tasks.domain.model.queries;

public record GetTaskByNameQuery(String name) {
    public GetTaskByNameQuery {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
    }
}
