package com.taskmanager.backend.project.interfaces.acl;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.queries.projectQueries.GetProjectByIdQuery;
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
    private final ProjectQueryService projectQueryService;

    public ProjectContextFacade(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
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
}
