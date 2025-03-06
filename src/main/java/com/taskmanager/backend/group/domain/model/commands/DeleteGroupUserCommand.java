package com.taskmanager.backend.group.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteGroupUserCommand(
        @NotBlank Long groupId,
        @NotBlank Long userId
) {
}
