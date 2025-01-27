package com.taskmanager.backend.iam.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateProfileCommand(
        @NotBlank Long userId,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        String phoneNumber,
        String profilePicture
) {
}
