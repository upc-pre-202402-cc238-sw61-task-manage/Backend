package com.taskmanager.backend.project.domain.services.commandservices;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.UpdateProjectCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectCommand command);
    void handle(DeleteProjectCommand command);
}
