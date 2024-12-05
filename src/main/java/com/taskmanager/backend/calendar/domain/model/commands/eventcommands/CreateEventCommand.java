package com.taskmanager.backend.calendar.domain.model.commands.eventcommands;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateEventCommand(
        @NotBlank Long projectId,
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank LocalDateTime date
) {
}
