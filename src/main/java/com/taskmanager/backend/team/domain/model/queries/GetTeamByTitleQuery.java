package com.taskmanager.backend.team.domain.model.queries;

public record GetTeamByTitleQuery(String title) {
    public GetTeamByTitleQuery {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Team title cannot be empty");
        }
    }
}
