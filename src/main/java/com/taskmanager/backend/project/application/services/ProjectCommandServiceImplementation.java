package com.taskmanager.backend.project.application.services;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.UpdateProjectCommand;
import com.taskmanager.backend.project.domain.services.ProjectCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImplementation implements ProjectCommandService {
    private final com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository projectRepository;

    public ProjectCommandServiceImplementation(com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        if (projectRepository.existsByProjectName(command.projectName())) {
            throw new IllegalArgumentException("A project with that name already exists");
        }
        var project = new Project(command);
        try {
            projectRepository.save(project);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating a new project");
        }

        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectCommand command) {
        if (projectRepository.existsByProjectName(command.getProjectName())) {
            throw new IllegalArgumentException("A project with that name already exists");
        }
        var project = projectRepository.findById(command.getProjectId());
        if (project.isEmpty()) throw new IllegalArgumentException("A project with that id does not exist");
        var newProject = project.get();
        try {
            var updatedProject = projectRepository.save(newProject.updateProject(command));
            return Optional.of(updatedProject);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating the project");
        }
    }

    @Override
    public void handle(DeleteProjectCommand command) {
        if (!projectRepository.existsById(command.projectId())) {
            throw new IllegalArgumentException("A project with that id does not exist");
        }
        try {
            projectRepository.deleteById(command.projectId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting the project");
        }
    }
}