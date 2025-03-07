package com.taskmanager.backend.project.interfaces.rest.controllers;

import com.taskmanager.backend.iam.interfaces.rest.resources.UserResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllProjectsByUserIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllUsersByProjectIdQuery;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projectTransform.ProjectResourceFromEntityAssembler;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectUserCommandService;
import com.taskmanager.backend.project.domain.services.queryservices.ProjectUserQueryService;
import com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources.CreateProjectUserResource;
import com.taskmanager.backend.project.interfaces.rest.resources.projectUserResources.DeleteProjectUserResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projectUserTransform.CreateProjectUserResourceCommandFromResourceAssembler;
import com.taskmanager.backend.project.interfaces.rest.transform.projectUserTransform.DeleteProjectUserResourceCommandFromResourceAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/project-users")
@Tag(name="Project Users", description = "Project User Endpoints")
public class ProjectUserController {
    private final ProjectUserCommandService projectUserCommandService;
    private final ProjectUserQueryService projectUserQueryService;

    public ProjectUserController(ProjectUserCommandService projectUserCommandService, ProjectUserQueryService projectUserQueryService) {
        this.projectUserCommandService = projectUserCommandService;
        this.projectUserQueryService = projectUserQueryService;
    }

    @PostMapping
    public ResponseEntity<String> addUserToProject(@RequestBody CreateProjectUserResource resource) {
        var createProjectUserResourceCommand = CreateProjectUserResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        try {
            projectUserCommandService.handle(createProjectUserResourceCommand);
            return ResponseEntity.ok("User added to project");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserFromProject(@RequestBody DeleteProjectUserResource resource) {
        var deleteProjectUserResourceCommand = DeleteProjectUserResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        try {
            projectUserCommandService.handle(deleteProjectUserResourceCommand);
            return ResponseEntity.ok("User deleted from project");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/project/{projectId}/users")
    public ResponseEntity<String> deleteAllUsersFromProject(@PathVariable Long projectId) {
        try {
            var deleteAllUsersFromProjectCommand = new DeleteAllUsersFromProjectCommand(projectId);
            projectUserCommandService.handle(deleteAllUsersFromProjectCommand);
            return ResponseEntity.ok("All users deleted from project");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/project/{projectId}/users")
    public ResponseEntity<List<UserResource>> getUsersByProject(@PathVariable Long projectId) {
        var getAllUsersByProjectIdQuery = new GetAllUsersByProjectIdQuery(projectId);
        var users = projectUserQueryService.handle(getAllUsersByProjectIdQuery);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/user/{userId}/projects")
    public ResponseEntity<List<ProjectResource>> getProjectsByUser(@PathVariable Long userId) {
        var getAllProjectsByUserIdQuery = new GetAllProjectsByUserIdQuery(userId);
        var projects = projectUserQueryService.handle(getAllProjectsByUserIdQuery);
        var projectResource = projects
                .stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(projectResource);
    }
}
