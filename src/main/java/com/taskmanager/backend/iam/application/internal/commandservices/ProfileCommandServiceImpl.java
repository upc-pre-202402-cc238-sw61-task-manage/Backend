package com.taskmanager.backend.iam.application.internal.commandservices;

import com.taskmanager.backend.iam.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.iam.domain.model.commands.UpdateProfileCommand;
import com.taskmanager.backend.iam.domain.model.entities.Profile;
import com.taskmanager.backend.iam.domain.services.ProfileCommandService;
import com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    private void validateEmail(String email){
        String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com|net|org|edu$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) throw new RuntimeException("Invalid email");
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var user = userRepository
                .findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(user.getProfile() != null) throw new RuntimeException("The user already has an profile");

        validateEmail(command.email());

        var profile = new Profile(command);
        user.setProfile(profile);
        try {
            userRepository.save(user);
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
