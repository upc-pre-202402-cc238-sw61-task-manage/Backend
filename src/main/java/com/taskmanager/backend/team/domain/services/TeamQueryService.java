package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsQuery;
import com.taskmanager.backend.team.domain.model.queries.GetTeamByIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetTeamByTitleQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamQueryService {
    List<Team> handle(GetAllTeamsQuery query);
    Optional<Team> handle(GetTeamByIdQuery query);
    Optional<Team> handle(GetTeamByTitleQuery query);
}
