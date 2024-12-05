package com.taskmanager.backend.project.domain.model.queries.taskqueries;

public record GetTaskByNameQuery(String name) {
    public GetTaskByNameQuery {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
    }
}
