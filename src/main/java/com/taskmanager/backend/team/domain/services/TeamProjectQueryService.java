package com.taskmanager.backend.team.domain.services;

import com.taskmanager.backend.team.domain.model.queries.GetTeamProjectsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamProjectQueryService {
    List<TeamProject> handle(GetTeamProjectsByUserIdQuery query);
}
