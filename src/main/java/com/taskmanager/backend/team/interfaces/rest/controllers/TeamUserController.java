package com.taskmanager.backend.team.interfaces.rest.controllers;

import com.taskmanager.backend.team.domain.model.commands.CreateTeamUserCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteAllUsersFromTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.DeleteTeamUserCommand;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllUsersByTeamIdQuery;
import com.taskmanager.backend.team.domain.services.TeamUserCommandService;
import com.taskmanager.backend.team.domain.services.TeamUserQueryService;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamResource;
import com.taskmanager.backend.team.interfaces.rest.transform.TeamResourceFromEntityAssembler;
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

    @PostMapping("/{teamId}/{userId}")
    public ResponseEntity<String> addUserToTeam(@PathVariable Long teamId, @PathVariable Long userId){
        var createTeamUserCommand = new CreateTeamUserCommand(teamId, userId);
        var teamUser = teamUserCommandService.handle(createTeamUserCommand);
        if(teamUser.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok("User added to the team");
    }

    @DeleteMapping("/{teamId}/{userId}")
    public ResponseEntity<String> removeUserFromTeam(@PathVariable Long teamId, @PathVariable Long userId){
        var deleteTeamUserCommand = new DeleteTeamUserCommand(teamId, userId);
        teamUserCommandService.handle(deleteTeamUserCommand);
        return ResponseEntity.ok("User removed from the team");
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<String> removeAllUsersFromTeam(@PathVariable Long teamId){
        var deleteAllUsersFromTeamCommand = new DeleteAllUsersFromTeamCommand(teamId);
        teamUserCommandService.handle(deleteAllUsersFromTeamCommand);
        return ResponseEntity.ok("All users removed from team");
    }

    @GetMapping("/{teamId}/users")
    public ResponseEntity<List<UserResource>> getAllUsersFromTeam(@PathVariable Long teamId){
        var getAllUsersByTeamIdQuery = new GetAllUsersByTeamIdQuery(teamId);
        var users = teamUserQueryService.handle(getAllUsersByTeamIdQuery);
        var userResource = users
                .stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{userId}/teams")
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
