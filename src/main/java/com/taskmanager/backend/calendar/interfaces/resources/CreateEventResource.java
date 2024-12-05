package com.taskmanager.backend.calendar.interfaces.resources;

import java.time.LocalDateTime;

public record CreateEventResource(
        Long projectId,
        String title,
        String description,
        LocalDateTime dueDate
) {
}
