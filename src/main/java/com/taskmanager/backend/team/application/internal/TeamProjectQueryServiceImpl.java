package com.taskmanager.backend.team.application.internal;

import com.taskmanager.backend.project.domain.model.valueobjects.ProjectLightInfo;
import com.taskmanager.backend.team.domain.model.queries.GetTeamProjectsByUserIdQuery;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamProject;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamProjectQueryResponse;
import com.taskmanager.backend.team.domain.services.TeamProjectQueryService;
import com.taskmanager.backend.team.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeamProjectQueryServiceImpl implements TeamProjectQueryService {
    private final TeamRepository teamRepository;

    public TeamProjectQueryServiceImpl(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamProject> handle(GetTeamProjectsByUserIdQuery query) {
        List<Object[]> response = teamRepository.findTeamProjectsByUserId(query.userId());

        Map<Long, List<ProjectLightInfo>> groupedProjects = response
                .stream()
                .filter(obj -> obj[3] != null) //Checks if there are any projects
                .map(obj -> new TeamProjectQueryResponse(
                        (Long) obj[0], // Team ID
                        (String) obj[1], // Team Title
                        (String) obj[2], // Team Image
                        (Long) obj[3], // Project ID
                        (String) obj[4] // Project Title
                ))
                .collect(Collectors.groupingBy(
                        TeamProjectQueryResponse::getTeamId,
                        Collectors.mapping(
                                t -> new ProjectLightInfo(t.getProjectId(), t.getProjectTitle()),
                                Collectors.toList()
                        )
                ));

        return response
                .stream()
                .map(obj -> (Long) obj[0]) //Team ID
                .distinct()
                .map(teamId -> new TeamProject(
                        teamId,
                        (String) response
                                .stream()
                                .filter(obj -> Objects.equals(obj[0], teamId))
                                .findFirst()
                                .get()[1], //Team Title
                        (String) response
                                .stream()
                                .filter(obj -> Objects.equals(obj[0], teamId))
                                .findFirst()
                                .get()[2], //Team Image
                        groupedProjects.getOrDefault(teamId, new ArrayList<>()) //Team Projects
                ))
                .toList();
    }
}

