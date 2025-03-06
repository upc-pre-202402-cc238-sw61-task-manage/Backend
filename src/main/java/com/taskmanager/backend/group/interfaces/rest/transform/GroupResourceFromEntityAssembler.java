package com.taskmanager.backend.group.interfaces.rest.transform;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.interfaces.rest.resources.GroupResource;

public class GroupResourceFromEntityAssembler {
    public static GroupResource toResourceFromEntity(Team entity){
        return new GroupResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage()
        );
    }
}
