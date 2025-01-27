package com.taskmanager.backend.calendar.interfaces.rest.resources.eventUserResources;

public record DeleteEventUserResource(
        Long eventId,
        Long userId
) {
}
