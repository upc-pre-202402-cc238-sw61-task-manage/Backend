package com.taskmanager.backend.iam.interfaces.rest.transform;

import com.taskmanager.backend.iam.domain.model.entities.Profile;
import com.taskmanager.backend.iam.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource transformResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getProfilePicture()
        );
    }
}
