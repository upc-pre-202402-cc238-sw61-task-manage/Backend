package com.taskmanager.backend.calendarManagement.interfaces.transform;

import com.taskmanager.backend.calendarManagement.domain.model.commands.UpdateEventCommand;
import com.taskmanager.backend.calendarManagement.interfaces.resources.UpdateEventResource;

public class UpdateEventCommandFromResourceAssembler {

    public static UpdateEventCommand toCommandFromResource(Long eventId, UpdateEventResource resource) {
        return new UpdateEventCommand(eventId, resource.title(), resource.description(), resource.dueDate());
    }
}
