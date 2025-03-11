package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.entities.TeamInvite;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamInvitesByInvitedUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamInvitesByInvitingUserIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamInviteQueryService {
    List<TeamInvite> handle(GetAllTeamInvitesByInvitedUserIdQuery query);
    List<TeamInvite> handle(GetAllTeamInvitesByInvitingUserIdQuery query);
}
