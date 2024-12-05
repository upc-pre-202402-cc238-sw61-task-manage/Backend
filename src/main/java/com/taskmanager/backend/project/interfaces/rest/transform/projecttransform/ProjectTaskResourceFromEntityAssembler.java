package com.taskmanager.backend.project.interfaces.rest.transform.projecttransform;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.interfaces.rest.resources.projectresources.ProjectTaskResource;
import com.taskmanager.backend.project.interfaces.rest.resources.taskResources.TaskLightResource;
import com.taskmanager.backend.project.interfaces.rest.transform.taskTransform.TaskLightResourceFromEntityAssembler;

import java.util.List;


public class ProjectTaskResourceFromEntityAssembler {
    public static ProjectTaskResource toResourceFromEntity(Project entity){
        List<TaskLightResource> taskResources = entity.getTaskList()
                .stream()
                .map(TaskLightResourceFromEntityAssembler::transformResourceFromEntity)
                .toList();

        return new ProjectTaskResource(
                entity.getId(),
                entity.getTitle(),
                taskResources
        );
    }
}
