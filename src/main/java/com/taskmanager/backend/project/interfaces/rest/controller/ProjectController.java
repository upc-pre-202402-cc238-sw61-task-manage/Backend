package com.taskmanager.backend.project.interfaces.rest.controller;

import com.taskmanager.backend.project.domain.model.commands.DeleteProjectCommand;
import com.taskmanager.backend.project.domain.model.queries.GetAllProjectsQuery;
import com.taskmanager.backend.project.domain.model.queries.GetProjectByIdQuery;
import com.taskmanager.backend.project.domain.services.ProjectCommandService;
import com.taskmanager.backend.project.domain.services.ProjectQueryService;
import com.taskmanager.backend.project.interfaces.rest.resources.CreateProjectResource;
import com.taskmanager.backend.project.interfaces.rest.resources.ProjectResource;
import com.taskmanager.backend.project.interfaces.rest.resources.UpdateProjectResource;
import com.taskmanager.backend.project.interfaces.rest.transform.CreateProjectCommandFromResourceAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.UpdateProjectCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/projects")
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
