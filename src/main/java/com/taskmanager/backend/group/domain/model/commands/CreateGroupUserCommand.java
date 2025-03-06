package com.taskmanager.backend.group.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateGroupUserCommand(
        @NotBlank Long groupId,
        @NotBlank Long userId
) {
}
