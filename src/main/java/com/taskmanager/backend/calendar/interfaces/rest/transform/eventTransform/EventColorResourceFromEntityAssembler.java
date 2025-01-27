package com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform;

import com.taskmanager.backend.calendar.domain.model.entities.EventColor;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.EventColorResource;

public class EventColorResourceFromEntityAssembler {
    public static EventColorResource toResourceFromEntity(EventColor entity){
        return new EventColorResource(
                entity.getId(),
                entity.getEventColorName()
        );
    }
}
