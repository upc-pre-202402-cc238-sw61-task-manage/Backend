package com.taskmanager.backend.project.domain.services.commandservices;

import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.CreateProjectUserCommand;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteProjectUserCommand;
import org.springframework.stereotype.Service;

@Service
public interface ProjectUserCommandService {
    void handle (CreateProjectUserCommand command);
    void handle (DeleteProjectUserCommand command);
    void handle(DeleteAllUsersFromProjectCommand command);
}
