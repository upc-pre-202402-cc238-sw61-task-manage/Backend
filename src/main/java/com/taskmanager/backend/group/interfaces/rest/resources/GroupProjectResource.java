package com.taskmanager.backend.group.interfaces.rest.resources;

import com.taskmanager.backend.project.interfaces.rest.resources.projectResources.ProjectLightResource;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * <h3>Group Project Resource</h3>
 * <p>
 *     An alternative group resource.
 *     Includes the project list related to the groupId.
 * </p>
 * @param id
 * @param title
 * @param projectList
 */
public record GroupProjectResource(
        @NotBlank Long id,
        String title,
        List<ProjectLightResource> projectList
) {
}
