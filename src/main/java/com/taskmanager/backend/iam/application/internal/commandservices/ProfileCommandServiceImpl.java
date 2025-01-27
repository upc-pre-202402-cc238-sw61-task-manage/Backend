package com.taskmanager.backend.iam.application.internal.commandservices;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.iam.domain.model.commands.UpdateProfileCommand;
import com.taskmanager.backend.iam.domain.model.entities.Profile;
import com.taskmanager.backend.iam.domain.services.ProfileCommandService;
import com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    private User findUser(Long userId){
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var user = findUser(command.userId());
        var profile = new Profile(command, user);
        try {
            profileRepository.save(profile);
            return Optional.of(profile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating profile: " + e.getMessage());
        }
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command, Long userId) {
        var existingProfile = profileRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        try {
            profileRepository.save(existingProfile.updateProfile(command));
            return Optional.of(existingProfile);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating profile: " + e.getMessage());
        }
    }
}
