package com.taskmanager.backend.group.application.internal;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.commands.CreateGroupUserCommand;
import com.taskmanager.backend.group.domain.model.commands.DeleteAllUsersFromGroupCommand;
import com.taskmanager.backend.group.domain.model.commands.DeleteGroupUserCommand;
import com.taskmanager.backend.group.domain.model.entities.GroupUser;
import com.taskmanager.backend.group.domain.model.valueobjects.GroupUserId;
import com.taskmanager.backend.group.domain.services.GroupUserCommandService;
import com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories.GroupRepository;
import com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories.GroupUserRepository;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.iam.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <h3>Group User Query Command Implementation</h3>
 * This class implements the {@link GroupUserCommandService} interface and provides an implementation for the
 * {@link CreateGroupUserCommand},  {@link DeleteGroupUserCommand} and {@link DeleteAllUsersFromGroupCommand}
 */
@Service
public class GroupUserCommandServiceImpl implements GroupUserCommandService {
    private final GroupRepository groupRepository;
    private final GroupUserRepository groupUserRepository;
    private final UserContextFacade userContextFacade;

    public GroupUserCommandServiceImpl(
            GroupRepository groupRepository,
            GroupUserRepository groupUserRepository,
            UserContextFacade userContextFacade
    ){
        this.groupRepository = groupRepository;
        this.groupUserRepository = groupUserRepository;
        this.userContextFacade = userContextFacade;
    }

    private User findUser(Long userId){
        var user = userContextFacade.fetchUserById(userId);
        if(user == null) throw new RuntimeException("User does not exist");
        return user;
    }

    private Team findGroup(Long groupId){
        var group = groupRepository.findById(groupId);
        if(group.isEmpty()) throw new RuntimeException("Group does not exist");
        return group.get();
    }

    @Override
    public Optional<GroupUser> handle(CreateGroupUserCommand command) {
        var user = findUser(command.userId());
        var group = findGroup(command.groupId());

        GroupUserId groupUserId = new GroupUserId(group.getId(), user.getId());
        if(groupUserRepository.existsById(groupUserId)) throw new RuntimeException("The user is already in the group");

        GroupUser groupUser = new GroupUser();
        groupUser.setId(groupUserId);
        groupUser.setUser(user);
        groupUser.setTeam(group);

        groupUserRepository.save(groupUser);
        return Optional.of(groupUser);
    }

    @Override
    public void handle(DeleteGroupUserCommand command) {
        Long groupId = findGroup(command.groupId()).getId();
        Long userId = findUser(command.userId()).getId();

        GroupUserId groupUserId = new GroupUserId(groupId,userId);
        GroupUser groupUser = groupUserRepository.findById(groupUserId)
                .orElseThrow(()-> new RuntimeException("The user is not in the group"));
        groupUserRepository.delete(groupUser);
    }

    @Override
    public void handle(DeleteAllUsersFromGroupCommand command) {
        var groupId = findGroup(command.groupId()).getId();
        groupUserRepository.deleteAllUsersFromGroupById(groupId);
    }
}
