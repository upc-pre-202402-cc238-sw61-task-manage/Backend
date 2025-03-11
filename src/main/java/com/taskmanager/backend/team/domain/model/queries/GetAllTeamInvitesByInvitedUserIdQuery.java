package com.taskmanager.backend.team.domain.model.queries;

public record GetAllTeamInvitesByInvitedUserIdQuery(Long invitedUserId) {
    public GetAllTeamInvitesByInvitedUserIdQuery {
        if(invitedUserId == null) throw new IllegalArgumentException("id cannot be null");
        if(invitedUserId < 0) throw new IllegalArgumentException("id cannot be negative");
    }
}
