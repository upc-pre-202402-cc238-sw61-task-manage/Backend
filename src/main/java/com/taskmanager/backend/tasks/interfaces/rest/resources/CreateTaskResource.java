package com.taskmanager.backend.tasks.interfaces.rest.resources;

import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;

import java.time.LocalDate;

public record CreateTaskResource(
        String taskName,
        String taskDescription,
        LocalDate dueDate,
        Long projectId,
        Long userId,
        TaskStatus status
) {
}
