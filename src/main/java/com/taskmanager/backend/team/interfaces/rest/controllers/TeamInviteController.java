package com.taskmanager.backend.team.interfaces.rest.controllers;

import com.taskmanager.backend.shared.constants.AppConstants;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteRespondCommand;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteSendCommand;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamInvitesByInvitedUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamInvitesByInvitingUserIdQuery;
import com.taskmanager.backend.team.domain.services.TeamInviteCommandService;
import com.taskmanager.backend.team.domain.services.TeamInviteQueryService;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamInviteReceivedResource;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamInviteSentResource;
import com.taskmanager.backend.team.interfaces.rest.transform.TeamInviteReceivedResourceFromEntityAssembler;
import com.taskmanager.backend.team.interfaces.rest.transform.TeamInviteSentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/teams-invites", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Team Invites", description = "Team Invites Management Endpoints")
public class TeamInviteController {
    private final TeamInviteCommandService teamInviteCommandService;
    private final TeamInviteQueryService teamInviteQueryService;

    public TeamInviteController(TeamInviteCommandService teamInviteCommandService, TeamInviteQueryService teamInviteQueryService) {
        this.teamInviteCommandService = teamInviteCommandService;
        this.teamInviteQueryService = teamInviteQueryService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendInvite(
            @RequestParam Long teamId,
            @RequestParam Long invitingUserId,
            @RequestParam Long invitedUserId
    ) {
        var teamInviteSendCommand = new TeamInviteSendCommand(teamId, invitingUserId, invitedUserId);
        teamInviteCommandService.handle(teamInviteSendCommand);
        return ResponseEntity.ok("Invitation sent successfully");
    }

    @PostMapping("/respond-invite")
    public ResponseEntity<String> respondToInvite(@RequestParam Long inviteId, @RequestParam boolean accept) {
        var teamInviteRespondCommand = new TeamInviteRespondCommand(inviteId, accept);
        teamInviteCommandService.handle(teamInviteRespondCommand);
        return ResponseEntity.ok(accept ? "Invitation accepted" : "Invitation rejected");
    }

    @GetMapping("/received/{user_id}")
    public ResponseEntity<List<TeamInviteReceivedResource>> getAllReceivedInvites(@PathVariable Long user_id){
        var getAllTeamInvitesByInvitedUserId = new GetAllTeamInvitesByInvitedUserIdQuery(user_id);
        var teamInvites = teamInviteQueryService.handle(getAllTeamInvitesByInvitedUserId);
        var teamInvitesResource = teamInvites
                .stream()
                .map(TeamInviteReceivedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(teamInvitesResource);
    }

    @GetMapping("/sent/{user_id}")
    public ResponseEntity<List<TeamInviteSentResource>> getAllSentInvites(@PathVariable Long user_id){
        var getAllTeamInvitesByInvitingUserId = new GetAllTeamInvitesByInvitingUserIdQuery(user_id);
        var teamInvites = teamInviteQueryService.handle(getAllTeamInvitesByInvitingUserId);
        var teamInvitesResource = teamInvites
                .stream()
                .map(TeamInviteSentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(teamInvitesResource);
    }
}
