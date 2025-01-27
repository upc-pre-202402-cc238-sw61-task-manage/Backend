package com.taskmanager.backend.iam.interfaces.rest.resources;

public record UpdateProfileResource(
        String firstName,
        String lastName,
        String phoneNumber,
        String profilePicture
) {
}
