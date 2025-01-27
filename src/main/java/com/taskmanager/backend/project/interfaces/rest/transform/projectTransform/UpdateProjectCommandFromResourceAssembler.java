package com.taskmanager.backend.project.interfaces.rest.transform.projectTransform;

import com.taskmanager.backend.project.domain.model.commands.projectCommands.UpdateProjectCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.UpdateProjectResource;

public class UpdateProjectCommandFromResourceAssembler {
    public static UpdateProjectCommand toCommandFromResource(long projectId, UpdateProjectResource resource) {
        return new UpdateProjectCommand(
                projectId,
                resource.title(),
                resource.description()
        );
    }
}
