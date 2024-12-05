package com.taskmanager.backend.calendar.domain.services.commandservices;

import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.CreateEventUserCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteAllUsersFromEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventusercommands.DeleteEventUserCommand;
import org.springframework.stereotype.Service;

@Service
public interface EventUserCommandService {
    void handle(CreateEventUserCommand command);
    void handle(DeleteEventUserCommand command);
    void handle(DeleteAllUsersFromEventCommand command);
}
