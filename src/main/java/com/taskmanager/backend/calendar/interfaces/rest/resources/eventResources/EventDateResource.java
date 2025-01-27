package com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources;

import java.time.LocalDateTime;

/**
 * Event Date Resource
 * An alternative Event Resource
 * @param id
 * @param startDate
 * @param endDate
 */
public record EventDateResource(
        Long id,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
