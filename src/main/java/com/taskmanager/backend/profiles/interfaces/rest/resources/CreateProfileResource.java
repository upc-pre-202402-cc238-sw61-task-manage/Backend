package com.taskmanager.backend.profiles.interfaces.rest.resources;

public record CreateProfileResource(String firstName, String lastName, String email, String phoneNumber, Long userId) {
}
