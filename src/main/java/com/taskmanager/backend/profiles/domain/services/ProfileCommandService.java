package com.taskmanager.backend.profiles.domain.services;

import com.taskmanager.backend.profiles.domain.model.agreggates.Profile;
import com.taskmanager.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.profiles.domain.model.commands.DeleteProfileCommand;
import com.taskmanager.backend.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);

    void handle(DeleteProfileCommand command);

    Optional<Profile> handle(UpdateProfileCommand command);
}
