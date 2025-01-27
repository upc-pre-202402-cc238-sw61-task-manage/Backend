package com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.CreateEventCommand;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.CreateEventResource;

public class CreateEventCommandFromResourceAssembler {

    public static CreateEventCommand toCommandFromResource(CreateEventResource resource) {
        return new CreateEventCommand(
                resource.projectId(),
                resource.title(),
                resource.description(),
                resource.startDate(),
                resource.endDate()
        );
    }

}
