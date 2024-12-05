package com.taskmanager.backend.calendar.domain.model.commands.eventusercommands;

import jakarta.validation.constraints.NotBlank;

public record CreateEventUserCommand(
        @NotBlank Long eventId,
        @NotBlank Long userId
) {
}
