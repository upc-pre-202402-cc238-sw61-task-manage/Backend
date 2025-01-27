package com.taskmanager.backend.calendar.domain.model.commands.eventcommands;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PatchEventDateCommand(
        @NotBlank Long id,
        @NotBlank LocalDateTime startDate,
        @NotBlank LocalDateTime endDate
) {
}
