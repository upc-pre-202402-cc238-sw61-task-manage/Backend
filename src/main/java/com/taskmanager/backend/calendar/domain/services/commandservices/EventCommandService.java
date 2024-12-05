package com.taskmanager.backend.calendar.domain.services.commandservices;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.CreateEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.DeleteEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventColorCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.UpdateEventCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EventCommandService {
    Optional<Event> handle(CreateEventCommand command);
    Optional<Event> handle(UpdateEventCommand command);
    void handle(DeleteEventCommand command);
    Optional<Event> handle(PatchEventColorCommand command);
    void deleteExpiredEvents();
}
