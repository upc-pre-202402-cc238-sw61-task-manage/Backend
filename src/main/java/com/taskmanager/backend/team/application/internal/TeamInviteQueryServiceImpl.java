package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.domain.model.entities.TeamInviteStatus;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamInvitesByInvitedUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamInvitesByInvitingUserIdQuery;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatusList;
import com.taskmanager.backend.team.domain.services.TeamInviteQueryService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamInviteRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamInviteStatusRepository;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamInviteQueryServiceImpl implements TeamInviteQueryService {

    private final TeamInviteRepository teamInviteRepository;
    private final UserContextFacade userContextFacade;

    public TeamInviteQueryServiceImpl(TeamInviteRepository teamInviteRepository, UserContextFacade userContextFacade){
        this.teamInviteRepository = teamInviteRepository;
        this.userContextFacade = userContextFacade;
    }

    private User findUser(Long userId){
        var user = userContextFacade.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User not found");
        return user;
    }

    @Override
    public List<TeamInvite> handle(GetAllTeamInvitesByInvitedUserIdQuery query) {
        var user = findUser(query.invitedUserId());
        return teamInviteRepository.findAllTeamInviteByInvitedUser(user);
    }

    @Override
    public List<TeamInvite> handle(GetAllTeamInvitesByInvitingUserIdQuery query) {
        var user = findUser(query.invitingUserId());
        return teamInviteRepository.findAllTeamInviteByInvitingUser(user);
    }
}
