package com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources;

import java.time.LocalDateTime;

/**
 * Patch Event Date Resource
 * It only allows to change the start and end dates
 * @param startDate
 * @param endDate
 */
public record PatchEventDateResource(
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
