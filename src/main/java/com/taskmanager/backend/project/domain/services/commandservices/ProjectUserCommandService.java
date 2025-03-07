package com.taskmanager.backend.project.domain.services.commandservices;

import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.*;
import org.springframework.stereotype.Service;

@Service
public interface ProjectUserCommandService {
    void handle (CreateProjectUserCommand command);
    void handle (PatchProjectUserTypeCommand command);
    void handle (DeleteProjectUserCommand command);
    void handle (DeleteAllUsersFromProjectCommand command);
    void handle (SeedProjectUserTypeCommand command);
}
