package com.taskmanager.backend.profiles.application.internal.queryservices;


import com.taskmanager.backend.profiles.domain.model.agreggates.Profile;
import com.taskmanager.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.taskmanager.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.taskmanager.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.taskmanager.backend.profiles.domain.services.ProfilePhotoService;
import com.taskmanager.backend.profiles.domain.services.ProfileQueryService;
import com.taskmanager.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;
    private final ProfilePhotoService profilePhotoService;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository, ProfilePhotoService profilePhotoService) {
        this.profileRepository = profileRepository;
        this.profilePhotoService = profilePhotoService;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        Optional<Profile> profile = profileRepository.findById(query.id());
        if (profile.isEmpty()) {
            throw new IllegalArgumentException("Profile with id " + query.id() + " not found");
        }
        if (profile.get().getProfilePhoto() == null) {
            profile.get().setProfilePhoto( profilePhotoService.getRandomProfilePhoto());
        }
        profileRepository.save(profile.get());

        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        System.out.println("ProfileQueryServiceImpl.handle" + query.emailAddress());
        Optional<Profile> profile = profileRepository.findByEmail(query.emailAddress());
        if (profile.isEmpty()) {
            throw new IllegalArgumentException("Profile with email " + query.emailAddress() + " not found");
        }
        if (profile.get().getProfilePhoto() == null) {
            profile.get().setProfilePhoto( profilePhotoService.getRandomProfilePhoto());
        }
        profileRepository.save(profile.get());

        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}