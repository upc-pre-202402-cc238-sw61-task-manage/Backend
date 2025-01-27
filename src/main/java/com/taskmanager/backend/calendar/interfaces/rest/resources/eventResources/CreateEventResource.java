package com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources;

import java.time.LocalDateTime;

public record CreateEventResource(
        Long projectId,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
