package com.taskmanager.backend.group.application.internal;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.entities.GroupUser;
import com.taskmanager.backend.group.domain.model.queries.GetAllGroupsByUserIdQuery;
import com.taskmanager.backend.group.domain.model.queries.GetAllUsersByGroupIdQuery;
import com.taskmanager.backend.group.domain.services.GroupUserQueryService;
import com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories.GroupUserRepository;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>Group User Query Service Implementation</h3>
 * This class implements the {@link GroupUserQueryService} interface and provides an implementation for the
 * {@link GetAllGroupsByUserIdQuery} and {@link GetAllUsersByGroupIdQuery}
 */
@Service
public class GroupUserQueryServiceImpl implements GroupUserQueryService {

    private final GroupUserRepository groupUserRepository;

    public GroupUserQueryServiceImpl(GroupUserRepository groupUserRepository){
        this.groupUserRepository = groupUserRepository;
    }

    /**
     * This method is used to handle the {@link GetAllGroupsByUserIdQuery} query
     * <p>It finds all the {@link User} from the Group User Table</p>
     * @param query {@link GetAllGroupsByUserIdQuery} instance
     * @return {@link List} of {@link User}
     */
    @Override
    public List<Team> handle(GetAllGroupsByUserIdQuery query) {
        return groupUserRepository.findAllByUserId(query.userId())
                .stream()
                .map(GroupUser::getTeam)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to handle the {@link GetAllUsersByGroupIdQuery} query
     * <p>It finds all the {@link Team} from the Group User Table</p>
     * @param query {@link GetAllUsersByGroupIdQuery} instance
     * @return {@link List} of {@link Team}
     */
    @Override
    public List<User> handle(GetAllUsersByGroupIdQuery query) {
        return groupUserRepository.findAllByGroupId(query.groupId())
                .stream()
                .map(GroupUser::getUser)
                .collect(Collectors.toList());
    }
}
