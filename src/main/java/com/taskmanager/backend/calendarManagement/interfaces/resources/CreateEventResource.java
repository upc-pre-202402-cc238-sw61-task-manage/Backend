package com.taskmanager.backend.calendarManagement.interfaces.resources;

public record CreateEventResource(
        Long projectId,
        Long userId,
        String title,
        String description,
        int day,
        int month,
        int year
) {
}
