package com.taskmanager.backend.calendarManagement.interfaces.resources;

public record EventResource(
        Long id,
        Long projectId,
        Long userId,
        String title,
        String description,
        int day,
        int month,
        int year
) {
}
