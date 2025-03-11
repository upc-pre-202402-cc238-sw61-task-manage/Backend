package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteRespondCommand;
import com.taskmanager.backend.team.domain.model.commands.TeamInviteSendCommand;
import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.domain.model.entities.TeamInviteStatus;
import com.taskmanager.backend.team.domain.model.entities.TeamUser;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatusList;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamUserId;
import com.taskmanager.backend.team.domain.services.TeamInviteCommandService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamInviteRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamInviteStatusRepository;
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
    private final TeamInviteStatusRepository teamInviteStatusRepository;

    public TeamInviteCommandServiceImpl(
            TeamInviteRepository teamInviteRepository,
            UserContextFacade userContextFacade,
            TeamUserRepository teamUserRepository,
            TeamRepository teamRepository,
            TeamInviteStatusRepository teamInviteStatusRepository
    ) {
        this.teamInviteRepository = teamInviteRepository;
        this.userContextFacade = userContextFacade;
        this.teamUserRepository = teamUserRepository;
        this.teamRepository = teamRepository;
        this.teamInviteStatusRepository = teamInviteStatusRepository;
    }

    private TeamInviteStatus findStatus(TeamInviteStatusList status){
        var foundStatus = teamInviteStatusRepository.findTeamInviteStatusByStatus(status);
        if(foundStatus.isEmpty()) throw new RuntimeException("Status not found");
        return foundStatus.get();
    }

    /**
     * <h3>Handle Team TeamInviteStatus Send Command</h3>
     * <p>Allows the user to create an invite to a team for another user</p>
     * @param command An {@link TeamInviteSendCommand} instance
     */
    @Override
    public void handle(TeamInviteSendCommand command) {
        var team = teamRepository.findById(command.teamId()).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        var invitingUser = userContextFacade.fetchUserById(command.invitingUserId());
        if(invitingUser == null) throw new IllegalArgumentException("Inviting user not found");

        var invitedUser = userContextFacade.fetchUserById(command.invitedUserId());
        if(invitedUser == null) throw new IllegalArgumentException("Invited user not found");

        var status = findStatus(TeamInviteStatusList.PENDING);

        var teamUserId = new TeamUserId(team.getId(), invitedUser.getId());

        boolean isMember = teamUserRepository.existsById(teamUserId);
        if(isMember) throw new IllegalArgumentException("The user is already in the team");

        var existingInvite = teamInviteRepository.findByTeamAndInvitedUser(team, invitedUser);
        if(existingInvite.isPresent()) throw new IllegalArgumentException("The user was already invited");

        var invite = new TeamInvite(team,invitedUser,invitingUser,status);
        teamInviteRepository.save(invite);
    }

    /**
     * <h3>Handle Team TeamInviteStatus Respond Command</h3>
     * <p>Allows the user to respond the invite</p>
     * @param command An {@link TeamInviteRespondCommand} instance
     */
    @Override
    @Transactional
    public void handle(TeamInviteRespondCommand command) {
        var invite = teamInviteRepository
                .findById(command.inviteId())
                .orElseThrow(() -> new IllegalArgumentException("Invitation not found"));
        TeamInviteStatus status;
        if(invite.getStatus() != findStatus(TeamInviteStatusList.PENDING)) throw new IllegalArgumentException("The invite was already responded");
        if (command.accept()) {
            status = findStatus(TeamInviteStatusList.ACCEPTED);
            invite.setStatus(status);
            var team = invite.getTeam();
            var user = invite.getInvitedUser();
            var teamUserId = new TeamUserId(team.getId(),user.getId());
            TeamUser teamUser = new TeamUser();
            teamUser.setId(teamUserId);
            teamUser.setUser(user);
            teamUser.setTeam(team);
            teamUserRepository.save(teamUser);
        } else {
            status = findStatus(TeamInviteStatusList.REJECTED);
            invite.setStatus(status);
        }
        teamInviteRepository.save(invite);
    }
}
