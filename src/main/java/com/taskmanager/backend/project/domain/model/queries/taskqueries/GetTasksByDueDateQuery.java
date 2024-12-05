package com.taskmanager.backend.project.domain.model.queries.taskqueries;

import java.time.LocalDate;

public record GetTasksByDueDateQuery(LocalDate dueDate) {
    public GetTasksByDueDateQuery {
        if(dueDate == null){
            throw new IllegalArgumentException("date cannot be null");
        }
    }
}
