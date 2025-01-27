package com.taskmanager.backend.calendar.interfaces.rest.resources.eventUserResources;

public record CreateEventUserResource(
        Long eventId,
        Long userId
) {
}
