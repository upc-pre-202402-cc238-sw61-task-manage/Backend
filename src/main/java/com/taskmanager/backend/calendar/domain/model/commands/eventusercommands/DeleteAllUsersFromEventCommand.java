package com.taskmanager.backend.calendar.domain.model.commands.eventusercommands;

import jakarta.validation.constraints.NotBlank;

public record DeleteAllUsersFromEventCommand(
        @NotBlank Long eventId
) {
}
