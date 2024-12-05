package com.taskmanager.backend.calendar.interfaces.resources.eventuserresources;

public record CreateEventUserResource(
        Long eventId,
        Long userId
) {
}
