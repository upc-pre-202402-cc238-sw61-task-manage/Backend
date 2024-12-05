package com.taskmanager.backend.calendar.domain.model.commands.eventcommands;

import com.taskmanager.backend.calendar.domain.model.valueObjects.EventColor;
import jakarta.validation.constraints.NotBlank;

public record PatchEventColorCommand(
        @NotBlank Long id,
        @NotBlank EventColor color
) {
}
