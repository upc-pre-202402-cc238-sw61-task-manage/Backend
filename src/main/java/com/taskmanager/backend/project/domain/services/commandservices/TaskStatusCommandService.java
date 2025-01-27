package com.taskmanager.backend.project.domain.services.commandservices;

import com.taskmanager.backend.project.domain.model.commands.taskCommands.SeedTaskStatusCommand;
import org.springframework.stereotype.Service;

@Service
public interface TaskStatusCommandService {
    void handle(SeedTaskStatusCommand command);
}
