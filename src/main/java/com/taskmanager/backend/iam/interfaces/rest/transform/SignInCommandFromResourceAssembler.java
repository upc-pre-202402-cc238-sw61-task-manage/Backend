package com.taskmanager.backend.iam.interfaces.rest.transform;

import com.taskmanager.backend.iam.domain.model.commands.SignInCommand;
import com.taskmanager.backend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}