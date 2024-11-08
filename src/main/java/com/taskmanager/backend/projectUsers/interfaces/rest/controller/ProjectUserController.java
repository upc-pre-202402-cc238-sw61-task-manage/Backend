package com.taskmanager.backend.projectUsers.interfaces.rest.controller;

import com.taskmanager.backend.iam.interfaces.rest.resources.UserResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.taskmanager.backend.project.interfaces.rest.resources.ProjectResource;
import com.taskmanager.backend.project.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import com.taskmanager.backend.projectUsers.domain.services.ProjectUserCommandService;
import com.taskmanager.backend.projectUsers.domain.services.ProjectUserQueryService;
import com.taskmanager.backend.projectUsers.interfaces.rest.resources.CreateProjectUserResource;
import com.taskmanager.backend.projectUsers.interfaces.rest.resources.DeleteProjectUserResource;
import com.taskmanager.backend.projectUsers.interfaces.rest.transform.CreateProjectUserResourceCommandFromResourceAssembler;
import com.taskmanager.backend.projectUsers.interfaces.rest.transform.DeleteProjectUserResourceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/project-users")
@Tag(name="Project Users", description = "Task Management Endpoints")
public class ProjectUserController {
    private final ProjectUserCommandService projectUserCommandService;
    private final ProjectUserQueryService projectUserQueryService;

    public ProjectUserController(ProjectUserCommandService projectUserCommandService, ProjectUserQueryService projectUserQueryService) {
        this.projectUserCommandService = projectUserCommandService;
        this.projectUserQueryService = projectUserQueryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUserToProject(@RequestBody CreateProjectUserResource resource) {
        var createProjectUserResourceCommand = CreateProjectUserResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        try {
            projectUserCommandService.handle(createProjectUserResourceCommand);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteUserFromProject(@RequestBody DeleteProjectUserResource resource) {
        var deleteProjectUserResourceCommand = DeleteProjectUserResourceCommandFromResourceAssembler.toCommandFromResource(resource);
        try {
            projectUserCommandService.handle(deleteProjectUserResourceCommand);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/project/{projectId}/users")
    public ResponseEntity<List<UserResource>> getUsersByProject(@PathVariable Long projectId) {
        var users = projectUserQueryService.findUsersByProjectId(projectId);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/user/{userId}/projects")
    public ResponseEntity<List<ProjectResource>> getProjectsByUser(@PathVariable Long userId) {
        var projects = projectUserQueryService.findProjectsByUserId(userId);
        var projectResource = projects
                .stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(projectResource);
    }
}
