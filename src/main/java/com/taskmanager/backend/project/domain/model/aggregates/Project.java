package com.taskmanager.backend.project.domain.model.aggregates;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.project.domain.model.entities.ProjectUser;
import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.CreateProjectCommand;
import com.taskmanager.backend.project.domain.model.commands.projectCommands.UpdateProjectCommand;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "value", column = @Column(name = "title"))
    private String title;
    @AttributeOverride(name = "value", column = @Column(name = "description"))
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> eventList = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectUser> projectUsers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;


    public Project(CreateProjectCommand command, Team team){
        this.title = command.title();
        this.description = command.description();
        this.team = team;
    }

    public Project updateProject(UpdateProjectCommand command) {
        this.title = command.title();
        this.description = command.description();
        return this;
    }
}
