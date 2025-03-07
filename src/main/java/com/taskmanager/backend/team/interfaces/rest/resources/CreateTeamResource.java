package com.taskmanager.backend.team.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateTeamResource(
        @NotBlank String title,
        String description,
        String image
) {
}
