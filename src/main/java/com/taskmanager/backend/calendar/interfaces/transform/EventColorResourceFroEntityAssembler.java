package com.taskmanager.backend.calendar.interfaces.transform;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.interfaces.resources.EventColorResource;

public class EventColorResourceFroEntityAssembler {
    public static EventColorResource toResourceFromEntity(Event entity){
        return new EventColorResource(
                entity.getId(),
                entity.getColor()
        );
    }
}
