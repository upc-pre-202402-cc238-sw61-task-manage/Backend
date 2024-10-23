package com.taskmanager.backend.calendarManagement.interfaces.transform;

import com.taskmanager.backend.calendarManagement.domain.model.commands.CreateEventCommand;
import com.taskmanager.backend.calendarManagement.interfaces.resources.CreateEventResource;

public class CreateEventCommandFromResourceAssembler {

    public static CreateEventCommand toCommandFromResource(CreateEventResource resource) {
        return new CreateEventCommand(resource.userId(), resource.projectId(), resource.title(), resource.description(), resource.dueDate());
    }

}
