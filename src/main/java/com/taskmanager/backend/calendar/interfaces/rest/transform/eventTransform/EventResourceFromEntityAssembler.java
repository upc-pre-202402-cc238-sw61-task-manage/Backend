package com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.EventResource;

public class EventResourceFromEntityAssembler {

    public static EventResource toResourceFromEntity(Event entity){
        return new EventResource(
                entity.getId(),
                entity.getProject().getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDateRange().getStartDate(),
                entity.getDateRange().getEndDate()
        );
    }
}
