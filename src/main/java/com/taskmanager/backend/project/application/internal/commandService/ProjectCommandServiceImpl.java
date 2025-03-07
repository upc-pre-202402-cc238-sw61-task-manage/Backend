package com.taskmanager.backend.project.application.internal.commandService;

import com.taskmanager.backend.calendar.interfaces.acl.EventUserContextFacade;
import com.taskmanager.backend.team.interfaces.acl.TeamContextFacade;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.UpdateProjectCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectCommandService;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories.ProjectUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <h3>Project Command Service Implementation</h3>
 * This class implements the {@link ProjectCommandService} interface and provides
 * the implementation for the {@link CreateProjectCommand}, {@link UpdateProjectCommand} and {@link DeleteProjectCommand}
 */
@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {
    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;
    private final EventUserContextFacade eventUserContextFacade;
    private final TeamContextFacade teamContextFacade;

    public ProjectCommandServiceImpl(
            ProjectRepository projectRepository,
            ProjectUserRepository projectUserRepository,
            EventUserContextFacade eventUserContextFacade,
            TeamContextFacade teamContextFacade
    ) {
        this.projectRepository = projectRepository;
        this.projectUserRepository  = projectUserRepository;
        this.eventUserContextFacade = eventUserContextFacade;
        this.teamContextFacade = teamContextFacade;
    }

    private Project findProject(Long projectId){
        var project = projectRepository.findById(projectId);
        if(project.isEmpty()) throw new RuntimeException("Project not found");
        return project.get();
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        var team = teamContextFacade.fetchTeamById(command.teamId());
        if(team == null) return Optional.empty();
        var project = new Project(command, team);
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
            project.getEventList().forEach(event -> eventUserContextFacade.deleteAllUsersFromEventByEventId(event.getId()));
            projectUserRepository.deleteByProjectId(project.getId());
            projectRepository.deleteById(project.getId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting the project");
        }
    }
}