package com.taskmanager.backend.calendar.interfaces.resources.eventuserresources;

public record DeleteEventUserResource(
        Long eventId,
        Long userId
) {
}
