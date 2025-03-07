package com.taskmanager.backend.project.application.internal.commandService;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.valueobjects.ProjectUserId;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.taskmanager.backend.project.domain.model.entities.ProjectUser;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.CreateProjectUserCommand;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteProjectUserCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectUserCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectUserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserCommandServiceImpl implements ProjectUserCommandService {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectRepository projectRepository;
    private final UserContextFacade userContext;

    public ProjectUserCommandServiceImpl(
            ProjectUserRepository projectUserRepository,
            ProjectRepository projectRepository,
            UserContextFacade userContext
    ) {
        this.projectUserRepository = projectUserRepository;
        this.projectRepository  = projectRepository;
        this.userContext = userContext;
    }

    private Project findProject(Long projectId){
        var project = projectRepository.findById(projectId);
        if(project.isEmpty()) throw new RuntimeException("Project not found");
        return project.get();
    }

    private User findUser(Long userId){
        var user = userContext.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User not found");
        return user;
    }

    @Override
    public void handle(CreateProjectUserCommand command) {
        var project = findProject(command.projectId());
        var user = findUser(command.userId());

        ProjectUserId projectUserId = new ProjectUserId(project.getId(), user.getId());
        if(projectUserRepository.existsById(projectUserId)) throw new RuntimeException("The user is already in the project");

        ProjectUser projectUser = new ProjectUser();
        projectUser.setId(projectUserId);
        projectUser.setProject(project);
        projectUser.setUser(user);

        projectUserRepository.save(projectUser);
    }

    @Override
    public void handle(DeleteProjectUserCommand command) {
        var projectId = findProject(command.projectId()).getId();
        var userId = findUser(command.userId()).getId();

        ProjectUserId projectUserId = new ProjectUserId(projectId, userId);

        ProjectUser projectUser = projectUserRepository
                .findById(projectUserId)
                .orElseThrow(() -> new RuntimeException("The user is not in the project"));

        projectUserRepository.delete(projectUser);
    }

    @Override
    public void handle(DeleteAllUsersFromProjectCommand command) {
        var projectId = findProject(command.projectId()).getId();
        projectUserRepository.removeAllUsersFromProjectByProjectId(projectId);
    }
}

