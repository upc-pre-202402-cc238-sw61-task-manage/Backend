package com.taskmanager.backend.project.domain.model.aggregates;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectcommands.UpdateProjectCommand;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.project.domain.model.entities.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project extends AuditableAbstractAggregateRoot<Project> {
    @AttributeOverride(name = "value", column = @Column(name = "title"))
    private String title;
    @AttributeOverride(name = "value", column = @Column(name = "description"))
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> taskList = new ArrayList<>();

    @AttributeOverride(name = "value", column = @Column(name = "leader"))
    private String leader;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> eventList = new ArrayList<>();

    public Project(CreateProjectCommand command){
        this.title = command.title();
        this.description = command.description();
        this.leader = command.leader();
        this.taskList = new ArrayList<>();
        this.eventList = new ArrayList<>();
    }

    public Project updateProject(UpdateProjectCommand command) {
        this.title = command.title();
        this.description = command.description();
        return this;
    }
}
