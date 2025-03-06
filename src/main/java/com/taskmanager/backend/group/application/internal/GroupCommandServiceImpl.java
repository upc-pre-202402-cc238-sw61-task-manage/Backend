package com.taskmanager.backend.group.application.internal;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.group.domain.model.commands.DeleteGroupCommand;
import com.taskmanager.backend.group.domain.model.commands.UpdateGroupCommand;
import com.taskmanager.backend.group.domain.services.GroupCommandService;
import com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories.GroupRepository;
import com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories.GroupUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <h3>Group Command Service Implementation</h3>
 * This class implements the {@link GroupCommandService} interface and provides
 * the implementation for the {@link CreateTeamCommand}, {@link UpdateGroupCommand} and {@link DeleteGroupCommand}
 */
@Service
public class GroupCommandServiceImpl implements GroupCommandService {
    private final GroupRepository groupRepository;
    private final GroupUserRepository groupUserRepository;

    public GroupCommandServiceImpl(
            GroupRepository groupRepository,
            GroupUserRepository groupUserRepository
    ){
        this.groupRepository = groupRepository;
        this.groupUserRepository = groupUserRepository;
    }

    private Team findGroup(Long groupId){
        var group = groupRepository.findById(groupId);
        if(group.isEmpty()) throw new RuntimeException("Group not found");
        return group.get();
    }

    @Override
    public Optional<Team> handle(CreateTeamCommand command) {
        var group = new Team(command);
        try{
            groupRepository.save(group);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while creating a new Group");
        }
        return Optional.of(group);
    }

    @Override
    public Optional<Team> handle(UpdateGroupCommand command) {
        var group = findGroup(command.groupId());
        try {
            var updateGroup = groupRepository.save(group.updateGroupCommand(command));
            return Optional.of(updateGroup);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating the group");
        }
    }

    @Override
    public void handle(DeleteGroupCommand command) {
        var group = findGroup(command.groupId());
        try {
            groupUserRepository.deleteAllUsersFromGroupById(group.getId());
            groupRepository.deleteById(group.getId());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting the group");
        }
    }
}
