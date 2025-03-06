package com.taskmanager.backend.group.domain.services;

import com.taskmanager.backend.group.domain.model.commands.CreateGroupUserCommand;
import com.taskmanager.backend.group.domain.model.commands.DeleteAllUsersFromGroupCommand;
import com.taskmanager.backend.group.domain.model.commands.DeleteGroupUserCommand;
import com.taskmanager.backend.group.domain.model.entities.GroupUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GroupUserCommandService {
    Optional<GroupUser> handle(CreateGroupUserCommand command);
    void handle(DeleteGroupUserCommand command);
    void handle(DeleteAllUsersFromGroupCommand command);
}
