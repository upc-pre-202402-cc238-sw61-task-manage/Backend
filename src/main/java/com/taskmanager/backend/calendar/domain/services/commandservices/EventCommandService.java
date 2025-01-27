package com.taskmanager.backend.calendar.domain.services.commandservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EventCommandService {
    Optional<Event> handle(CreateEventCommand command);
    Optional<Event> handle(UpdateEventCommand command);
    void handle(DeleteEventCommand command);
    Optional<Event> handle(PatchEventColorCommand command);
    Optional<Event> handle(PatchEventDateCommand command);
}
