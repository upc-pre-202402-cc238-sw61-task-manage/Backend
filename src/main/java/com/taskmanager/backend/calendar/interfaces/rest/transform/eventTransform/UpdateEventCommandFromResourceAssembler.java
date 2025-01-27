package com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.UpdateEventCommand;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.UpdateEventResource;

public class UpdateEventCommandFromResourceAssembler {

    public static UpdateEventCommand toCommandFromResource(Long eventId, UpdateEventResource resource) {
        return new UpdateEventCommand(
                eventId,
                resource.title(),
                resource.description(),
                resource.startDate(),
                resource.endDate()
        );
    }
}
