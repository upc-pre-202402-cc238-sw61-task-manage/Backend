package com.taskmanager.backend.project.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectProfile {
    private Long userId;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
