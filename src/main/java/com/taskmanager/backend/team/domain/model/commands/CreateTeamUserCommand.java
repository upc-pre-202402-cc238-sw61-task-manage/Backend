package com.taskmanager.backend.team.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateTeamUserCommand(
        @NotBlank Long teamId,
        @NotBlank Long userId
) {
}
