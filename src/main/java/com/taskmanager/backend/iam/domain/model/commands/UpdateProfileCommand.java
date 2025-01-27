package com.taskmanager.backend.iam.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record UpdateProfileCommand(
        String firstName,
        String lastName,
        String phoneNumber,
        String profilePicture
) {
}
