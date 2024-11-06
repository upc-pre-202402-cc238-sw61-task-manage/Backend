package com.taskmanager.backend.tasks.domain.model.queries;

import java.time.LocalDate;

public record GetTasksByDueDateQuery(LocalDate dueDate) {
    public GetTasksByDueDateQuery {
        if(dueDate == null){
            throw new IllegalArgumentException("dueDate cannot be null");
        }
    }
}
