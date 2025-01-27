package com.taskmanager.backend.iam.application.internal.queryservices;

import com.taskmanager.backend.iam.domain.model.entities.Profile;
import com.taskmanager.backend.iam.domain.model.queries.GetProfileByEmailQuery;
import com.taskmanager.backend.iam.domain.model.queries.GetProfileByIdQuery;
import com.taskmanager.backend.iam.domain.services.ProfileQueryService;
import com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository repository;

    public ProfileQueryServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return repository.findById(query.profileId());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return repository.findByEmail(query.email());
    }
}
