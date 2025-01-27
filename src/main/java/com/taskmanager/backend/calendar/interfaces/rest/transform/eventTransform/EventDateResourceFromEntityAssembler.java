package com.taskmanager.backend.calendar.interfaces.rest.transform.eventTransform;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.interfaces.rest.resources.eventResources.EventDateResource;

public class EventDateResourceFromEntityAssembler {
    public static EventDateResource toResourceFromEntity(Event entity){
        return new EventDateResource(
                entity.getId(),
                entity.getDateRange().getStartDate(),
                entity.getDateRange().getEndDate()
        );
    }
}
