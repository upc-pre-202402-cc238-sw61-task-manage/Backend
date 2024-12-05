package com.taskmanager.backend.project.interfaces.rest.transform.projecttransform;

import com.taskmanager.backend.project.domain.model.commands.projectcommands.CreateProjectCommand;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource){
        return new CreateProjectCommand(
                resource.title(),
                resource.description(),
                resource.leader()
        );
    }
}