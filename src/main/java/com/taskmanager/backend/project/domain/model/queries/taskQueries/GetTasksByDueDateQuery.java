package com.taskmanager.backend.project.domain.model.queries.taskQueries;

import java.time.LocalDateTime;

public record GetTasksByDueDateQuery(LocalDateTime dueDate) {
    public GetTasksByDueDateQuery {
        if(dueDate == null){
            throw new IllegalArgumentException("date cannot be null");
        }
    }
}
