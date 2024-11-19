package com.taskmanager.backend.profiles.application.internal.commandservices;


import com.taskmanager.backend.profiles.domain.model.agreggates.Profile;
import com.taskmanager.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.taskmanager.backend.profiles.domain.services.ProfileCommandService;
import com.taskmanager.backend.profiles.domain.services.ProfilePhotoService;
import com.taskmanager.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final ProfilePhotoService profilePhotoService;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, ProfilePhotoService profilePhotoService) {
        this.profileRepository = profileRepository;
        this.profilePhotoService = profilePhotoService;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmail(emailAddress))
            throw new IllegalArgumentException(
                    "Profile with email " + command.email() + " already exists");
        String profilePhoto =  profilePhotoService.getRandomProfilePhoto();
        var profile = new Profile(command, profilePhoto);
        profileRepository.save(profile);
        return Optional.of(profile);
    }
}