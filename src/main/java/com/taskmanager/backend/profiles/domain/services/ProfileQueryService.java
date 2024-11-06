package com.taskmanager.backend.profiles.domain.services;

import com.taskmanager.backend.profiles.domain.model.agreggates.Profile;
import com.taskmanager.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.taskmanager.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.taskmanager.backend.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}
