package com.taskmanager.backend.calendarManagement.domain.services;

import com.taskmanager.backend.calendarManagement.domain.model.aggregates.Event;
import com.taskmanager.backend.calendarManagement.domain.model.commands.CreateEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.commands.DeleteEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.commands.UpdateEventCommand;

import java.util.Optional;

public interface EventCommandService {

    Long handle(CreateEventCommand command);
    Optional<Event> handle(UpdateEventCommand command);
    void handle(DeleteEventCommand command);

}
