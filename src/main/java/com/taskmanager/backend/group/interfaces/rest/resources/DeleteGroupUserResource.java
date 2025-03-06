package com.taskmanager.backend.group.interfaces.rest.resources;

public record DeleteGroupUserResource(
        Long groupId,
        Long userId
) {
}
