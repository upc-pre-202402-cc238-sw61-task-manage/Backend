package com.taskmanager.backend.team.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteTeamCommand(@NotBlank Long teamId) {
}
