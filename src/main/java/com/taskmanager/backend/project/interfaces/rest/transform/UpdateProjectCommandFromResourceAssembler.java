package com.taskmanager.backend.project.interfaces.rest.transform;

import com.taskmanager.backend.project.domain.model.commands.UpdateProjectCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.UpdateProjectResource;

public class UpdateProjectCommandFromResourceAssembler {
    public static UpdateProjectCommand toCommandFromResource(long projectId, UpdateProjectResource resource) {
        return new UpdateProjectCommand(
                projectId,
                resource.projectName(),
                resource.projectDescription(),
                resource.startDate(),
                resource.endDate(),
                resource.projectManager()
        );
    }
}
