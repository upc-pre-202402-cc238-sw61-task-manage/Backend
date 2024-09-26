package com.taskmanager.backend.tasks.interfaces.rest.resources;

import java.util.Date;

public record UpdateTaskResource(
        String taskName,
        String taskDescription,
        Date dueDate,
        int assignUser
) {
}
