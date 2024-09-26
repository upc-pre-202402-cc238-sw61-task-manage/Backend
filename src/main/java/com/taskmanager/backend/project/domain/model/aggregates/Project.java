package com.taskmanager.backend.project.domain.model.aggregates;

import com.taskmanager.backend.project.domain.model.commands.CreateProjectCommand;
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
    private Long id;
    private String projectName;
    private String projectDescription;
    private String status;
    private String assignUser;
    private String projectManager;
    private String projectMember;

    public Project(CreateProjectCommand command){
        this.projectName = command.projectName();
        this.projectDescription = command.projectDescription();
    }
}
