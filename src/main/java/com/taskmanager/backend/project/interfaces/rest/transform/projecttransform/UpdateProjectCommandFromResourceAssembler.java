package com.taskmanager.backend.project.interfaces.rest.transform.projecttransform;

import com.taskmanager.backend.project.domain.model.commands.projectcommands.UpdateProjectCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.UpdateProjectResource;

public class UpdateProjectCommandFromResourceAssembler {
    public static UpdateProjectCommand toCommandFromResource(long projectId, UpdateProjectResource resource) {
        return new UpdateProjectCommand(
                projectId,
                resource.title(),
                resource.description()
        );
    }
}
