package com.taskmanager.backend.project.domain.services;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.UpdateProjectCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectCommand command);
    void handle(DeleteProjectCommand command);
}
