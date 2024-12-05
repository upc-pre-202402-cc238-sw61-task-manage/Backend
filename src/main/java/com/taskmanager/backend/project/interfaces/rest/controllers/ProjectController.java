package com.taskmanager.backend.project.interfaces.rest.controllers;

import com.taskmanager.backend.project.domain.model.commands.projectcommands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.projectqueries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectCommandService;
import com.taskmanager.backend.project.domain.services.queryservices.ProjectQueryService;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.CreateProjectResource;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.ProjectResource;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.ProjectTaskResource;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.UpdateProjectResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projecttransform.CreateProjectCommandFromResourceAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.projecttransform.ProjectResourceFromEntityAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.projecttransform.ProjectTaskResourceFromEntityAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.projecttransform.UpdateProjectCommandFromResourceAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/projects")
@Tag(name="Projects", description = "Projects Management Endpoints")
public class ProjectController {
    private final ProjectQueryService projectQueryService;
    private final ProjectCommandService projectCommandService;

    public ProjectController(ProjectQueryService projectQueryService, ProjectCommandService projectCommandService) {
        this.projectQueryService = projectQueryService;
        this.projectCommandService = projectCommandService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long projectId) {
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var project = projectQueryService.handle(getProjectByIdQuery);
        return project.map(value -> ResponseEntity
                .ok(ProjectResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tasks/{projectId}")
    public ResponseEntity<ProjectTaskResource> getProjectTasks(@PathVariable Long projectId) {
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var project = projectQueryService.handle(getProjectByIdQuery);
        return project
                .map(entity -> ResponseEntity.ok(ProjectTaskResourceFromEntityAssembler.toResourceFromEntity(entity)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects(){
        var getAllProjectsQuery = new GetAllProjectsQuery();
        var projects = projectQueryService.handle(getAllProjectsQuery);
        var projectsResource = projects.stream().map(ProjectResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(projectsResource);
    }

    @PostMapping
    public ResponseEntity<ProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        var createProjectCommand = CreateProjectCommandFromResourceAssembler.toCommandFromResource(resource);
        var project = projectCommandService.handle(createProjectCommand);
        if (project.isEmpty()) return ResponseEntity.notFound().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return ResponseEntity.ok(projectResource);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResource> updateProject(@PathVariable Long projectId, @RequestBody UpdateProjectResource resource) {
        var updateProjectCommand = UpdateProjectCommandFromResourceAssembler.toCommandFromResource(projectId, resource);
        var updatedProject = projectCommandService.handle(updateProjectCommand);
        if (updatedProject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(updatedProject.get());
        return ResponseEntity.ok(projectResource);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        var deleteProjectCommand = new DeleteProjectCommand(projectId);
        projectCommandService.handle(deleteProjectCommand);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
