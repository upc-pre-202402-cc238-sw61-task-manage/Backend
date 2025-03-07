package com.taskmanager.backend.team.interfaces.acl;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.queries.GetTeamByIdQuery;
import com.taskmanager.backend.team.domain.services.TeamQueryService;
import org.springframework.stereotype.Service;

/**
 * <h3>Team Context Facade</h3>
 * <p>
 *     This class is a facade for the Team context. It provides a simple
 *     interface for other bounded contexts to interact with the Team context
 *     This class is part of the ACL layer
 * </p>
 **/
@Service
public class TeamContextFacade {
    private final TeamQueryService teamQueryService;

    public TeamContextFacade(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    public Team fetchTeamById(Long id) {
        var getTeamByIdQuery = new GetTeamByIdQuery(id);
        var team = teamQueryService.handle(getTeamByIdQuery);
        return team.orElse(null);
    }
}
