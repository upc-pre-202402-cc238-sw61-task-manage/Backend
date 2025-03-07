package com.taskmanager.backend.team.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTeamCommand(
        @NotBlank Long teamId,
        @Size(min = 1, max = 255) String title,
        String description,
        String image
) {

}
