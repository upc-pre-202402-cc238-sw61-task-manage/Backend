package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.queries.GetAllTeamsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.queries.GetAllUsersByTeamIdQuery;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamUserQueryService {
    List<Team> handle(GetAllTeamsByUserIdQuery query);
    List<User> handle(GetAllUsersByTeamIdQuery query);
}
