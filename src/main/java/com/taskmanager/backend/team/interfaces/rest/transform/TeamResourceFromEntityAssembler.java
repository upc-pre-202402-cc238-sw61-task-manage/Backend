package com.taskmanager.backend.team.interfaces.rest.transform;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.interfaces.rest.resources.TeamResource;

public class TeamResourceFromEntityAssembler {
    public static TeamResource toResourceFromEntity(Team entity){
        return new TeamResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage()
        );
    }
}
