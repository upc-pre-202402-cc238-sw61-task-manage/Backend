package com.taskmanager.backend.group.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateGroupResource(
        @NotBlank String title,
        String description,
        String image
) {
}
