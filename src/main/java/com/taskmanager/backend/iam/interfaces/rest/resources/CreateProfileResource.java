package com.taskmanager.backend.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateProfileResource(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        String phoneNumber,
        String profilePicture
) {
}
