package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteRespondCommand;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteSendCommand;
import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.domain.model.entities.TeamUser;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamUserId;
import com.taskmanager.backend.team.domain.services.TeamInviteCommandService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamInviteRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TeamInviteCommandServiceImpl implements TeamInviteCommandService {
    private final TeamInviteRepository teamInviteRepository;
    private final TeamUserRepository teamUserRepository;
    private final TeamRepository teamRepository;
    private final UserContextFacade userContextFacade;

    public TeamInviteCommandServiceImpl(
            TeamInviteRepository teamInviteRepository,
            UserContextFacade userContextFacade,
            TeamUserRepository teamUserRepository,
            TeamRepository teamRepository
    ) {
        this.teamInviteRepository = teamInviteRepository;
        this.userContextFacade = userContextFacade;
        this.teamUserRepository = teamUserRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * <h3>Handle Team Invite Send Command</h3>
     * <p>Allows the user to create an invite to a team for another user</p>
     * @param command An {@link TeamInviteSendCommand} instance
     */
    @Override
    public void handle(TeamInviteSendCommand command) {
        var team = teamRepository.findById(command.teamId()).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        var invitingUser = userContextFacade.fetchUserById(command.invitingUserId());
        if(invitingUser == null) throw new IllegalArgumentException("Inviting user not found");

        var invitedUser = userContextFacade.fetchUserById(command.invitedUserId());
        if(invitedUser == null) throw new IllegalArgumentException("Inviting user not found");

        var teamUserId = new TeamUserId(team.getId(), invitedUser.getId());

        boolean isMember = teamUserRepository.existsById(teamUserId);
        if(isMember) throw new IllegalArgumentException("The user is already in the team");

        var existingInvite = teamInviteRepository.findByTeamIdAndInvitedUserId(team.getId(), invitedUser.getId());
        if(existingInvite.isPresent()) throw new IllegalArgumentException("The user was already invited");

        var invite = new TeamInvite(team,invitedUser,invitingUser);
        teamInviteRepository.save(invite);
    }

    /**
     * <h3>Handle Team Invite Respond Command</h3>
     * <p>Allows the user to respond the invite</p>
     * @param command An {@link TeamInviteRespondCommand} instance
     */
    @Override
    @Transactional
    public void handle(TeamInviteRespondCommand command) {
        var invite = teamInviteRepository
                .findById(command.inviteId())
                .orElseThrow(() -> new IllegalArgumentException("Invitation not found"));

        if (command.accept()) {
            invite.accept();
            var team = invite.getTeam();
            var user = invite.getInvitedUser();
            var teamUserId = new TeamUserId(team.getId(),user.getId());
            TeamUser teamUser = new TeamUser();
            teamUser.setId(teamUserId);
            teamUser.setUser(user);
            teamUser.setTeam(team);
            teamUserRepository.save(teamUser);
        } else {
            invite.reject();
        }

        teamInviteRepository.save(invite);
    }
}
