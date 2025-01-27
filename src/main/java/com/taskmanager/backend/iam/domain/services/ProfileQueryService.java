package com.taskmanager.backend.iam.domain.services;

import com.taskmanager.backend.iam.domain.model.entities.Profile;
import com.taskmanager.backend.iam.domain.model.queries.GetProfileByEmailQuery;
import com.taskmanager.backend.iam.domain.model.queries.GetProfileByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
}
