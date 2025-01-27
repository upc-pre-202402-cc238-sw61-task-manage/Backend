package com.taskmanager.backend.iam.interfaces.rest.transform;

import com.taskmanager.backend.iam.domain.model.commands.CreateProfileCommand;
import com.taskmanager.backend.iam.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource, Long id){
        return new CreateProfileCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.profilePicture()
        );
    }
}
