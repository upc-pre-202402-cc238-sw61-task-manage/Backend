package com.taskmanager.backend.project.interfaces.rest.controllers;

import com.taskmanager.backend.iam.interfaces.rest.resources.UserResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.CreateProjectUserCommand;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteProjectUserCommand;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.PatchProjectUserTypeCommand;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllProfilesByProjectIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllProjectsByUserIdQuery;
import com.taskmanager.backend.project.domain.model.queries.projectUserQueries.GetAllUsersByProjectIdQuery;
import com.taskmanager.backend.project.domain.model.valueobjects.ProjectProfile;
import com.taskmanager.backend.project.domain.model.valueobjects.ProjectUserTypeList;
import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectResource;
import com.taskmanager.backend.project.interfaces.rest.transform.projectTransform.ProjectResourceFromEntityAssembler;
import com.taskmanager.backend.project.domain.model.commands.projectUserCommands.DeleteAllUsersFromProjectCommand;
import com.taskmanager.backend.project.domain.services.commandservices.ProjectUserCommandService;
import com.taskmanager.backend.project.domain.services.queryservices.ProjectUserQueryService;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * This class is a REST controller that exposes the project user resource.
 * It includes the following operations:
 * <ul>
 *     <li> POST   /api/v1/project-users/{projectId}/{userId} : Adds a user to the project </li>
 *     <li> DELETE /api/v1/project-users/{projectId}/{userId} : Removes a user from the project </li>
 *     <li> DELETE /api/v1/project-users/{projectId}/users    : Removes all users from the project </li>
 *     <li> GET    /api/v1/project-users/{projectId}/users    : Returns all the users from the project </li>
 *     <li> GET    /api/v1/project-users/{userId}/projects    : Returns all the projects from the user </li>
 * </ul>
 **/
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

    /**
     * <h3>Add User to Project</h3>
     * <p>Adds an existing user to an existing project</p>
     * @param projectId The id of an existing project
     * @param userId The id of an existing user
     * @return A confirmation message
     */
    @PostMapping("/{projectId}/{userId}/{type}")
    public ResponseEntity<String> addUserToProject(@PathVariable Long projectId, @PathVariable Long userId, @PathVariable ProjectUserTypeList type) {
        var createProjectUserResourceCommand = new CreateProjectUserCommand(projectId, userId, type);
        projectUserCommandService.handle(createProjectUserResourceCommand);
        return ResponseEntity.ok("User with type "  + type + " added to project");
    }

    @PatchMapping("/{projectId}/{userId}/{type}")
    public ResponseEntity<String> patchProjectUserType(@PathVariable Long projectId, @PathVariable Long userId, @PathVariable ProjectUserTypeList type){
        var patchProjectUserTypeCommand = new PatchProjectUserTypeCommand(projectId,userId,type);
        projectUserCommandService.handle(patchProjectUserTypeCommand);
        return ResponseEntity.ok("Changed the user type to " + type);
    }

    /**
     * <h3>Delete User From Project</h3>
     * <p>Removes a user from a project</p>
     * @param projectId The id of an existing project
     * @param userId The id of an existing user
     * @return A confirmation message
     */
    @DeleteMapping("/{projectId}/{userId}")
    public ResponseEntity<String> deleteUserFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        var deleteProjectUserResourceCommand = new DeleteProjectUserCommand(projectId, userId);
        projectUserCommandService.handle(deleteProjectUserResourceCommand);
        return ResponseEntity.ok("User deleted from project");
    }

    /**
     * <h3>Delete all users from project</h3>
     * <p>Removes all the users from a project</p>
     * @param projectId The id of the project the users will be removed from
     * @return A confirmation message
     */
    @DeleteMapping("/{projectId}/users")
    public ResponseEntity<String> deleteAllUsersFromProject(@PathVariable Long projectId) {
        var deleteAllUsersFromProjectCommand = new DeleteAllUsersFromProjectCommand(projectId);
        projectUserCommandService.handle(deleteAllUsersFromProjectCommand);
        return ResponseEntity.ok("All users deleted from project");
    }

    /**
     * <h3>Get all users from project</h3>
     * <p>Retrieves all the users from an existing projects</p>
     * @param projectId The id of the project the users will be retrieved from
     * @return List of users
     * @see UserResource
     */
    @GetMapping("/{projectId}/users")
    public ResponseEntity<List<UserResource>> getAllUsersFromProject(@PathVariable Long projectId) {
        var getAllUsersByProjectIdQuery = new GetAllUsersByProjectIdQuery(projectId);
        var users = projectUserQueryService.handle(getAllUsersByProjectIdQuery);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    /**
     * <h3>Get all profiles from project</h3>
     * <p>Retrieves all the profiles of the users that belong to an existing project</p>
     * @param projectId The id of the project
     * @return A list of project profiles
     * @see ProjectProfile
     */
    @GetMapping("/{projectId}/profiles")
    public ResponseEntity<List<ProjectProfile>> getAllProfilesFromProject(@PathVariable Long projectId){
        var getAllProfilesByProjectIdQuery = new GetAllProfilesByProjectIdQuery(projectId);
        var profiles = projectUserQueryService.handle(getAllProfilesByProjectIdQuery);
        return ResponseEntity.ok(profiles);
    }

    /**
     * <h3>Get all projects from user</h3>
     * @param userId The id of the user the projects will be retrieved from
     * @return List of projects
     * @see ProjectResource
     */
    @GetMapping("/{userId}/projects")
    public ResponseEntity<List<ProjectResource>> getAllProjectsFromUser(@PathVariable Long userId) {
        var getAllProjectsByUserIdQuery = new GetAllProjectsByUserIdQuery(userId);
        var projects = projectUserQueryService.handle(getAllProjectsByUserIdQuery);
        var projectResource = projects
                .stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(projectResource);
    }
}
