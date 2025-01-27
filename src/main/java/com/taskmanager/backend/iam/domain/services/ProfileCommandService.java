package com.taskmanager.backend.iam.domain.services;

import com.taskmanager.backend.iam.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.iam.domain.model.commands.UpdateProfileCommand;
import com.taskmanager.backend.iam.domain.model.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command, Long userId);
}
