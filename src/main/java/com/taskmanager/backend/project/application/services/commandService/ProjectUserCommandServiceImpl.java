package com.taskmanager.backend.project.application.services.commandService;

import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.taskmanager.backend.project.domain.model.entities.ProjectUser;
import com.taskmanager.backend.project.domain.model.commands.projectusercommands.CreateProjectUserCommand;
import com.taskmanager.backend.project.domain.model.commands.projectusercommands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectusercommands.DeleteProjectUserCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectUserCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private Long findUserId(Long userId){
        var user = userContext.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User not found");
        return user.getId();
    }

    @Override
    public void handle(CreateProjectUserCommand command) {
        var project = findProject(command.projectId());
        Long projectId = project.getId();
        Long userId = findUserId(command.userId());

        Optional<ProjectUser> existingProjectUser = projectUserRepository.findByProjectIdAndUserId(projectId,userId);

        if (existingProjectUser.isPresent()) throw new RuntimeException("The user is already in the project");

        ProjectUser projectUser = new ProjectUser();
        projectUser.setProject(project);
        projectUser.setUser(userContext.fetchUserById(userId));

        projectUserRepository.save(projectUser);
    }

    @Override
    public void handle(DeleteProjectUserCommand command) {
        var project = findProject(command.projectId());
        Long projectId = project.getId();
        Long userId = findUserId(command.userId());

        ProjectUser projectUser = projectUserRepository
                .findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new RuntimeException("ProjectUser relation not found"));

        projectUserRepository.delete(projectUser);
    }

    @Override
    public void handle(DeleteAllUsersFromProjectCommand command) {
        var projectId = findProject(command.projectId()).getId();

        List<ProjectUser> projectUsers = projectUserRepository.findByProjectId(projectId);
        if (projectUsers.isEmpty()) throw new RuntimeException("No users found in the project");

        projectUserRepository.deleteAll(projectUsers);
    }
}

