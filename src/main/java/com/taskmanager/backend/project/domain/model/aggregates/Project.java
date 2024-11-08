package com.taskmanager.backend.project.domain.model.aggregates;

import com.taskmanager.backend.project.domain.model.commands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.UpdateProjectCommand;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
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
    @AttributeOverride(name = "value", column = @Column(name = "project_name"))
    private String projectName;
    @AttributeOverride(name = "value", column = @Column(name = "project_description"))
    private String projectDescription;
    @AttributeOverride(name = "value", column = @Column(name = "project_leader"))
    private String projectLeader;

    public Project(CreateProjectCommand command){
        this.projectName = command.projectName();
        this.projectDescription = command.projectDescription();
        this.projectLeader = command.projectLeader();
    }

    public Project updateProject(UpdateProjectCommand command) {
        this.projectName = command.projectName();
        this.projectDescription = command.projectDescription();
        return this;
    }
    
}
