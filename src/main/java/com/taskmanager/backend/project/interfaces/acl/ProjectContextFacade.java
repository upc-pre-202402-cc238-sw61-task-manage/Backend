package com.taskmanager.backend.project.interfaces.acl;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.UpdateProjectCommand;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectCommandService;
import com.taskmanager.backend.project.domain.services.queryservices.ProjectQueryService;
import org.springframework.stereotype.Service;

/**
 * Project Context Facade
 * <p>
 *     This class is a facade for the Project context. It provides a simple
 *     interface for other bounded contexts to interact with the Project context
 *     This class is part of the ACL layer
 * </p>
 **/
@Service
public class ProjectContextFacade {
    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;

    public ProjectContextFacade(ProjectCommandService projectCommandService, ProjectQueryService projectQueryService) {
        this.projectCommandService = projectCommandService;
        this.projectQueryService = projectQueryService;
    }

    /**
     * Creates a task with the given title, description and due date
     * @param projectName The title of the Project
     * @param projectDescription The complete description of the Project.
     * @param projectLeader The leader of the projectId
     * @return The taskId of the Task if it is created successfully
     */
    public Long createProject(String projectName, String projectDescription, String projectLeader) {
        var createProjectCommand = new CreateProjectCommand(projectName, projectDescription, projectLeader);
        var result = projectCommandService.handle(createProjectCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the taskId of the Project with the given taskId
     * @param projectId The title of the projectId
     * @return the taskId of the projectId if it is found
     */
    public Project fetchProjectById(Long projectId) {
        var getProjectById = new GetProjectByIdQuery(projectId);
        var result = projectQueryService.handle(getProjectById);
        return result.orElse(null);
    }

    /**
     * Updates the information of the Project with the given parameters
     * @param projectName The title of the Project
     * @param projectDescription The complete description of the Project.
     * @return The taskId of the Project if it is created successfully
     */
    public Long updateProject(Long projectId, String projectName, String projectDescription) {
        var updateProjectCommand = new UpdateProjectCommand(projectId, projectName, projectDescription);
        var result = projectCommandService.handle(updateProjectCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Deletes the Project of the given taskId
     * @param projectId The taskId of the Project
     * @return true if the Project is deleted successfully
     */
    public boolean deleteProject(Long projectId) {
        var deleteProjectCommand = new DeleteProjectCommand(projectId);
        try {
            projectCommandService.handle(deleteProjectCommand);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }
}
