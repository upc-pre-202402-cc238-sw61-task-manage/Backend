package com.taskmanager.backend.calendar.interfaces.resources;


import java.time.LocalDateTime;

public record UpdateEventResource(
        String title,
        String description,
        LocalDateTime dueDate
) {
}
