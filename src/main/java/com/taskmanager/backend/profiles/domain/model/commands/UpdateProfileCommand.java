package com.taskmanager.backend.profiles.domain.model.commands;

public record UpdateProfileCommand(
        Long id,
        String property,
        Object newValue
) {

}
