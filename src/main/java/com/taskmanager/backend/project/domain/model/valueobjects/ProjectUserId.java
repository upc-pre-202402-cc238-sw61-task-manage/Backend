package com.taskmanager.backend.project.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ProjectUserId implements Serializable {
    private Long projectId;
    private Long userId;
}
