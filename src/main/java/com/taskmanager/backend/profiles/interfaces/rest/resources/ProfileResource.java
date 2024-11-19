package com.taskmanager.backend.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String profilePhoto,
        Long userId
) {
}
