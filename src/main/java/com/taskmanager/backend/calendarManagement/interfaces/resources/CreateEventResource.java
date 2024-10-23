package com.taskmanager.backend.calendarManagement.interfaces.resources;

import java.time.LocalDate;

public record CreateEventResource(
        Long projectId,
        Long userId,
        String title,
        String description,
        LocalDate dueDate
) {
}
