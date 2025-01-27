package com.taskmanager.backend.calendar.domain.services.commandservices;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.entities.EventUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EventUserCommandService {
    Optional<EventUser> handle(CreateEventUserCommand command);
    void handle(DeleteEventUserCommand command);
    void handle(DeleteAllUsersFromEventCommand command);
}
