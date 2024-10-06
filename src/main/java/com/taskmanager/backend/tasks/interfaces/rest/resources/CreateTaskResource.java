package com.taskmanager.backend.tasks.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateTaskResource(
        String taskName,
        String taskDescription,
        LocalDate dueDate,
        Long projectId,
        Long assignUser
) {
}
