package com.taskmanager.backend.project.domain.model.aggregates;

import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project extends AuditableAbstractAggregateRoot<Project> {
    private String projectUUID;
    private String projectName;
    private String projectDescription;
    private String projectManager;
    private String projectStatus;

    public Project(String projectUUID, String projectName, String projectDescription, String projectManager, String projectStatus) {
        this.projectUUID = projectUUID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectManager = projectManager;
        this.projectStatus = projectStatus;
    }
}
