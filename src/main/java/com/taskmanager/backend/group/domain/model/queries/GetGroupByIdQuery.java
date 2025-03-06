package com.taskmanager.backend.group.domain.model.queries;

public record GetGroupByIdQuery(Long id) {
    public GetGroupByIdQuery {
        if (id == null){
            throw new IllegalArgumentException("groupId cannot be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("groupId cannot be negative");
        }
    }
}
