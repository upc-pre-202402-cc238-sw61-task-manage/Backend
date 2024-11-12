package com.taskmanager.backend.projectUsers.application.services;

import com.taskmanager.backend.iam.interfaces.acl.IamContextFacade;
import com.taskmanager.backend.project.interfaces.acl.ProjectContextFacade;
import com.taskmanager.backend.projectUsers.domain.model.aggregates.ProjectUser;
import com.taskmanager.backend.projectUsers.domain.model.commands.CreateProjectUserCommand;
import com.taskmanager.backend.projectUsers.domain.model.commands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.projectUsers.domain.model.commands.DeleteProjectUserCommand;
import com.taskmanager.backend.projectUsers.domain.services.ProjectUserCommandService;
import com.taskmanager.backend.projectUsers.infrastructure.persistance.jpa.repositories.ProjectUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectUserCommandServiceImpl implements ProjectUserCommandService {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectContextFacade projectContext;
    private final IamContextFacade iamContext;

    public ProjectUserCommandServiceImpl(
            ProjectUserRepository projectUserRepository,
            ProjectContextFacade projectContext,
            IamContextFacade iamContext
    ) {
        this.projectUserRepository = projectUserRepository;
        this.projectContext = projectContext;
        this.iamContext = iamContext;
    }

    @Override
    public void handle(CreateProjectUserCommand command) {
        Long projectId = projectContext.fetchProjectById(command.projectId()).getId();
        if (projectId == 0L) {
            throw new RuntimeException("Project not found");
        }

        Long userId = iamContext.fetchUserIdByUserId(command.userId());
        if (userId == 0L) {
            throw new RuntimeException("User not found");
        }

        Optional<ProjectUser> existingProjectUser = projectUserRepository.findByProjectIdAndUserId(
                projectContext.fetchProjectById(projectId).getId(),userId
        );

        if (existingProjectUser.isPresent()) {
            throw new RuntimeException("The user already has a project with the same name");
        }

        ProjectUser projectUser = new ProjectUser();
        projectUser.setProject(projectContext.fetchProjectById(projectId));
        projectUser.setUser(iamContext.fetchUserById(userId));

        projectUserRepository.save(projectUser);
    }

    @Override
    public void handle(DeleteProjectUserCommand command) {
        Long projectId = projectContext.fetchProjectById(command.projectId()).getId();
        Long userId = iamContext.fetchUserIdByUserId(command.userId());
        if (projectId == 0L || userId == 0L) {
            throw new RuntimeException("Project or User not found");
        }

        ProjectUser projectUser = projectUserRepository
                .findByProjectIdAndUserId(command.projectId(), command.userId())
                .orElseThrow(() -> new RuntimeException("ProjectUser relation not found"));

        projectUserRepository.delete(projectUser);
    }

    @Override
    public void handle(DeleteAllUsersFromProjectCommand command) {
        Long projectId = projectContext.fetchProjectById(command.projectId()).getId();
        if (projectId == 0L) {
            throw new RuntimeException("Project not found");
        }

        List<ProjectUser> projectUsers = projectUserRepository.findAllByProjectId(projectId);
        if (projectUsers.isEmpty()) {
            throw new RuntimeException("No users found for the project");
        }

        projectUserRepository.deleteAll(projectUsers);
    }
}

