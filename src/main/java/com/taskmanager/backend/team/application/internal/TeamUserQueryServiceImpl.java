package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.entities.TeamUser;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllUsersByTeamIdQuery;
import com.taskmanager.backend.team.domain.services.TeamUserQueryService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamUserRepository;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>Team User Query Service Implementation</h3>
 * This class implements the {@link TeamUserQueryService} interface and provides an implementation for the
 * {@link GetAllTeamsByUserIdQuery} and {@link GetAllUsersByTeamIdQuery}
 */
@Service
public class TeamUserQueryServiceImpl implements TeamUserQueryService {

    private final TeamUserRepository teamUserRepository;

    public TeamUserQueryServiceImpl(TeamUserRepository teamUserRepository){
        this.teamUserRepository = teamUserRepository;
    }

    /**
     * This method is used to handle the {@link GetAllTeamsByUserIdQuery} query
     * <p>It finds all the {@link User} from the Team User Table</p>
     * @param query {@link GetAllTeamsByUserIdQuery} instance
     * @return {@link List} of {@link User}
     */
    @Override
    public List<Team> handle(GetAllTeamsByUserIdQuery query) {
        return teamUserRepository.findAllByUserId(query.userId())
                .stream()
                .map(TeamUser::getTeam)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to handle the {@link GetAllUsersByTeamIdQuery} query
     * <p>It finds all the {@link Team} from the Team User Table</p>
     * @param query {@link GetAllUsersByTeamIdQuery} instance
     * @return {@link List} of {@link Team}
     */
    @Override
    public List<User> handle(GetAllUsersByTeamIdQuery query) {
        return teamUserRepository.findAllByTeamId(query.teamId())
                .stream()
                .map(TeamUser::getUser)
                .collect(Collectors.toList());
    }
}
