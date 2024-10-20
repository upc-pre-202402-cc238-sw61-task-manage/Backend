package com.taskmanager.backend.tasks.interfaces.rest.resources;

import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskResource(
        String taskName,
        String taskDescription,
        LocalDate dueDate,
        Long assignUser,
        TaskStatus status
) {
}
