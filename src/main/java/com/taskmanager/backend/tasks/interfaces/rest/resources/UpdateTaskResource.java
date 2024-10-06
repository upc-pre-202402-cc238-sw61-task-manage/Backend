package com.taskmanager.backend.tasks.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateTaskResource(
        String taskName,
        String taskDescription,
        LocalDate dueDate,
        Long assignUser
) {
}
