package com.taskmanager.backend.team.interfaces.rest.controllers;

import com.taskmanager.backend.shared.constants.AppConstants;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteRespondCommand;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteSendCommand;
import com.taskmanager.backend.team.domain.services.TeamInviteCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppConstants.API_BASE_PATH + "/teams-invites", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Team Invites", description = "Team Invites Management Endpoints")
public class TeamInviteController {
    private final TeamInviteCommandService teamInviteCommandService;

    public TeamInviteController(final TeamInviteCommandService teamInviteCommandService) {
        this.teamInviteCommandService = teamInviteCommandService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendInvite(
            @RequestParam Long teamId,
            @RequestParam Long invitingUserId,
            @RequestParam Long invitedUserId
    ) {
        var teamInviteSendCommand = new TeamInviteSendCommand(teamId, invitingUserId, invitedUserId);
        teamInviteCommandService.handle(teamInviteSendCommand);
        return ResponseEntity.ok("Invitation sent successfully.");
    }

    @PostMapping("/respond-invite")
    public ResponseEntity<String> respondToInvite(@RequestParam Long inviteId, @RequestParam boolean accept) {
        var teamInviteRespondCommand = new TeamInviteRespondCommand(inviteId, accept);
        teamInviteCommandService.handle(teamInviteRespondCommand);
        return ResponseEntity.ok(accept ? "Invitation accepted." : "Invitation rejected.");
    }
}
