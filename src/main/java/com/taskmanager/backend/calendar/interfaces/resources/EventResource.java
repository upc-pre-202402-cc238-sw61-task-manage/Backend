package com.taskmanager.backend.calendar.interfaces.resources;

import java.time.LocalDateTime;

public record EventResource(
        Long id,
        Long projectId,
        String title,
        String description,
        LocalDateTime dueDate
) {
}
