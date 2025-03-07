package com.taskmanager.backend.project.domain.model.commands.projectUserCommands;

import com.taskmanager.backend.project.domain.model.valueobjects.ProjectUserTypeList;
import jakarta.validation.constraints.NotBlank;

public record PatchProjectUserTypeCommand(
        @NotBlank Long projectId,
        @NotBlank Long userId,
        @NotBlank ProjectUserTypeList type
) {
}
