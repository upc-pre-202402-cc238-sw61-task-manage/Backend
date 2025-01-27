package com.taskmanager.backend.iam.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String profilePicture
) {
}
