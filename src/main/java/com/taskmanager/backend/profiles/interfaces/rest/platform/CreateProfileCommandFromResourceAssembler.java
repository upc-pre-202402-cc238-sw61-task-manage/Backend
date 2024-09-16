package com.taskmanager.backend.profiles.interfaces.rest.platform;

import com.taskmanager.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName() , resource.lastName(), resource.phoneNumber(),resource.email(), 0L);
    }
}
