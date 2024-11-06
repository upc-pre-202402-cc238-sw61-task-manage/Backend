package com.taskmanager.backend.profiles.domain.services;

import com.taskmanager.backend.profiles.domain.model.agreggates.Profile;
import com.taskmanager.backend.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
