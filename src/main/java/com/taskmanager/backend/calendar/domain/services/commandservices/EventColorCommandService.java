package com.taskmanager.backend.calendar.domain.services.commandservices;

import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.SeedEventColorCommand;
import org.springframework.stereotype.Service;

@Service
public interface EventColorCommandService {
    void handle(SeedEventColorCommand command);
}
