package com.taskmanager.backend.group.interfaces.rest.resources;

public record CreateGroupUserResource(
        Long groupId,
        Long userId
) {
}
