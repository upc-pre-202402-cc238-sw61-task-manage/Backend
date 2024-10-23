package com.taskmanager.backend.calendarManagement.interfaces.resources;

import java.time.LocalDate;

public record UpdateEventResource(
        String title,
        String description,
        LocalDate dueDate
) {
}
