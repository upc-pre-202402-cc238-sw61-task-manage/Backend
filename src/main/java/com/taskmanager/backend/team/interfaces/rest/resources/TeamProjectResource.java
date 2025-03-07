package com.taskmanager.backend.team.interfaces.rest.resources;

import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectLightResource;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * <h3>Team Project Resource</h3>
 * <p>
 *     An alternative team resource.
 *     Includes the project list related to the teamId.
 * </p>
 * @param id
 * @param title
 * @param projectList
 */
public record TeamProjectResource(
        @NotBlank Long id,
        String title,
        List<ProjectLightResource> projectList
) {
}
