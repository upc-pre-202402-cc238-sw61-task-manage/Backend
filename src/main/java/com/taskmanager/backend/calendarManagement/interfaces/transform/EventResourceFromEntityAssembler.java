package com.taskmanager.backend.calendarManagement.interfaces.transform;

import com.taskmanager.backend.calendarManagement.domain.model.aggregates.Event;
import com.taskmanager.backend.calendarManagement.interfaces.resources.EventResource;

public class EventResourceFromEntityAssembler {

    public static EventResource toResourceFromEntity(Event entity){
        return new EventResource(entity.getId(), entity.getProjectId(), entity.getUserId(), entity.getTitle(), entity.getDescription(), entity.getDay(), entity.getMonth(), entity.getYear());
    }
}
