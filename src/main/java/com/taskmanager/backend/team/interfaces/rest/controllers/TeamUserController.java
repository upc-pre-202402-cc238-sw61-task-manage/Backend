package com.taskmanager.backend.team.interfaces.rest.controllers;

import com.taskmanager.backend.team.domain.model.commands.DeleteAllUsersFromTeamCommand;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllUsersByTeamIdQuery;
import com.taskmanager.backend.team.domain.services.TeamUserCommandService;
import com.taskmanager.backend.team.domain.services.TeamUserQueryService;
import com.taskmanager.backend.team.interfaces.rest.resources.CreateTeamUserResource;
import com.taskmanager.backend.team.interfaces.rest.resources.DeleteTeamUserResource;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamResource;
import com.taskmanager.backend.team.interfaces.rest.transform.TeamResourceFromEntityAssembler;
import com.taskmanager.backend.team.interfaces.rest.transform.CreateTeamUserCommandFromResourceAssembler;
import com.taskmanager.backend.team.interfaces.rest.transform.DeleteTeamUserCommandFromResourceAssembler;
import com.taskmanager.backend.iam.interfaces.rest.resources.UserResource;
import com.taskmanager.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.taskmanager.backend.shared.constants.AppConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/team-users")
@Tag(name="Team Users Controller", description = "Team Users Management Endpoints")
public class TeamUserController {
    private final TeamUserCommandService teamUserCommandService;
    private final TeamUserQueryService teamUserQueryService;

    public TeamUserController(TeamUserCommandService teamUserCommandService, TeamUserQueryService teamUserQueryService){
        this.teamUserCommandService = teamUserCommandService;
        this.teamUserQueryService = teamUserQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addUserToTeam(@RequestBody CreateTeamUserResource resource){
        var createTeamUserCommand = CreateTeamUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var teamUser = teamUserCommandService.handle(createTeamUserCommand);
        if(teamUser.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok("User added to the team");
    }

    @DeleteMapping
    public ResponseEntity<?> removeUserFromTeam(@RequestBody DeleteTeamUserResource resource){
        var deleteTeamUserCommand = DeleteTeamUserCommandFromResourceAssembler.toCommandFromResource(resource);
        teamUserCommandService.handle(deleteTeamUserCommand);
        return ResponseEntity.ok("User removed from the team");
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> removeAllUsersFromTeam(@PathVariable Long teamId){
        var deleteAllUsersFromTeamCommand = new DeleteAllUsersFromTeamCommand(teamId);
        teamUserCommandService.handle(deleteAllUsersFromTeamCommand);
        return ResponseEntity.ok("All users removed from team");
    }

    @GetMapping("/team/{teamId}/users")
    public ResponseEntity<List<UserResource>> getAllUsersFromTeam(@PathVariable Long teamId){
        var getAllUsersByTeamIdQuery = new GetAllUsersByTeamIdQuery(teamId);
        var users = teamUserQueryService.handle(getAllUsersByTeamIdQuery);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("user/{userId}/teams")
    public ResponseEntity<List<TeamResource>> getAllTeamsFromUser(@PathVariable Long userId){
        var getAllTeamsByUserIdQuery = new GetAllTeamsByUserIdQuery(userId);
        var teams = teamUserQueryService.handle(getAllTeamsByUserIdQuery);
        var teamResource = teams
                .stream()
                .map(TeamResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(teamResource);
    }
}
