package com.taskmanager.backend.group.domain.model.queries;

public record GetGroupByTitleQuery(String title) {
    public GetGroupByTitleQuery {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Group title cannot be empty");
        }
    }
}
