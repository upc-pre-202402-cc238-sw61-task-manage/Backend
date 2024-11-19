package com.taskmanager.backend.profiles.interfaces.rest.platform;

import com.taskmanager.backend.profiles.domain.model.agreggates.Profile;
import com.taskmanager.backend.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getName().firstName(), entity.getName().lastName(), entity.getEmailAddress(),
                entity.getPhoneNumber(),
                entity.getProfilePhoto(),
                entity.getUserId());

    }
}