package com.taskmanager.backend.team.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteTeamUserCommand(
        @NotBlank Long teamId,
        @NotBlank Long userId
) {
}
