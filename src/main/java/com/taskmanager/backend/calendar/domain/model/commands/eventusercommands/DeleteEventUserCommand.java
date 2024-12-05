package com.taskmanager.backend.calendar.domain.model.commands.eventusercommands;

import jakarta.validation.constraints.NotBlank;

public record DeleteEventUserCommand(
        @NotBlank Long eventId,
        @NotBlank Long userId
) {
}
