package com.taskmanager.backend.calendarManagement.interfaces.resources;

public record UpdateEventResource(
        String title,
        String description,
        int day,
        int month,
        int year
) {
}
