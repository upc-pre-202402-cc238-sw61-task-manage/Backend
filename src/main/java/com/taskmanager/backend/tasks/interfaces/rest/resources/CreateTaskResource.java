package com.taskmanager.backend.tasks.interfaces.rest.resources;

import java.util.Date;

public record CreateTaskResource(
        String taskName,
        String taskDescription,
        Date dueDate,
        String projectUUID,
        int assignUser
) {
}
