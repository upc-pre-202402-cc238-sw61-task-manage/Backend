package com.taskmanager.backend.iam.interfaces.rest.transform;

import com.taskmanager.backend.iam.domain.model.commands.UpdateProfileCommand;
import com.taskmanager.backend.iam.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.phoneNumber(),
                resource.profilePicture()
        );
    }
}
