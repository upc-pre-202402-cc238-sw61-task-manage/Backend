package com.taskmanager.backend.project.application.services.commandService;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.UpdateProjectCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {
    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, ProjectUserRepository projectUserRepository) {
        this.projectRepository = projectRepository;
        this.projectUserRepository  = projectUserRepository;
    }

    private Project findProject(Long projectId){
        var project = projectRepository.findById(projectId);
        if(project.isEmpty()) throw new RuntimeException("Project not found");
        return project.get();
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        var project = new Project(command);
        try {
            projectRepository.save(project);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating a new projectId");
        }

        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectCommand command) {
        var project = findProject(command.projectId());
        try {
            var updatedProject = projectRepository.save(project.updateProject(command));
            return Optional.of(updatedProject);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating the projectId");
        }
    }

    @Override
    public void handle(DeleteProjectCommand command) {
        var project = findProject(command.projectId());
        try {
            projectUserRepository.deleteByProjectId(project.getId());
            projectRepository.deleteById(project.getId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting the project");
        }
    }
}