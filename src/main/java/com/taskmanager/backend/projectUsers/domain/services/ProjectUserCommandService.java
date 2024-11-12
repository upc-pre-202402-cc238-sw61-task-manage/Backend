package com.taskmanager.backend.projectUsers.domain.services;

import com.taskmanager.backend.projectUsers.domain.model.commands.CreateProjectUserCommand;
import com.taskmanager.backend.projectUsers.domain.model.commands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.projectUsers.domain.model.commands.DeleteProjectUserCommand;
import org.springframework.stereotype.Service;

@Service
public interface ProjectUserCommandService {
    void handle (CreateProjectUserCommand command);
    void handle (DeleteProjectUserCommand command);
    void handle(DeleteAllUsersFromProjectCommand command);
}
