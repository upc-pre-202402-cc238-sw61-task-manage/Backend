package com.taskmanager.backend.project.domain.model.queries.projectqueries;

public record GetProjectByTitleQuery(String title) {
    public GetProjectByTitleQuery {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Project title cannot be empty");
        }
    }
}
