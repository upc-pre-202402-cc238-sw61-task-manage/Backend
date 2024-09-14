package com.taskmanager.backend.calendarManagement.domain.services;

import com.taskmanager.backend.calendarManagement.domain.model.commands.CreateEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.commands.DeleteEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.commands.UpdateEventCommand;
import org.springframework.scheduling.config.Task;

import java.util.Optional;

public interface EventCommandService {

    Long handle(CreateEventCommand command);
    Optional<Task> handle(UpdateEventCommand command);
    void handle(DeleteEventCommand command);

}
