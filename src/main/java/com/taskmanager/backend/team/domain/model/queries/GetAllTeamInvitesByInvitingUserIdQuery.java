package com.taskmanager.backend.team.domain.model.queries;

public record GetAllTeamInvitesByInvitingUserIdQuery(Long invitingUserId) {
    public GetAllTeamInvitesByInvitingUserIdQuery {
        if(invitingUserId == null) throw new IllegalArgumentException("id cannot be null");
        if(invitingUserId < 0) throw new IllegalArgumentException("id cannot be negative");
    }
}
