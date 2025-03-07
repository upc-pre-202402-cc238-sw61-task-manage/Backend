package com.taskmanager.backend.team.domain.model.queries;

public record GetTeamByIdQuery(Long id) {
    public GetTeamByIdQuery {
        if (id == null){
            throw new IllegalArgumentException("teamId cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("teamId cannot be negative");
        }
    }
}
