package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsQuery;
import com.taskmanager.backend.team.domain.model.queries.GetTeamByIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetTeamByTitleQuery;
import com.taskmanager.backend.team.domain.services.TeamQueryService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamQueryServiceImpl implements TeamQueryService {
    private final TeamRepository teamRepository;

    public TeamQueryServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> handle(GetAllTeamsQuery query) {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> handle(GetTeamByIdQuery query) {
        return teamRepository.findById(query.id());
    }

    @Override
    public Optional<Team> handle(GetTeamByTitleQuery query) {
        return teamRepository.findByTitle(query.title());
    }
}
