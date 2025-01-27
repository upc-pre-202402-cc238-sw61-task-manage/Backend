package com.taskmanager.backend.calendar.domain.model.commands.eventcommands;

import com.taskmanager.backend.calendar.domain.model.valueobjects.EventColorList;
import jakarta.validation.constraints.NotBlank;

public record PatchEventColorCommand(
        @NotBlank Long id,
        @NotBlank EventColorList color
) {
}
