package com.taskmanager.backend.team.domain.model.aggregates;

import com.taskmanager.backend.team.domain.model.commands.CreateTeamCommand;
import com.taskmanager.backend.team.domain.model.commands.UpdateTeamCommand;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
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
@Table(name = "teams")
public class Team extends AuditableAbstractAggregateRoot<Team> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String image;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projectList = new ArrayList<>();

    public Team(CreateTeamCommand command){
        this.title = command.title();
        this.description = command.description();
        this.image = command.image();

        this.projectList = new ArrayList<>();
    }

    public Team updateTeamCommand(UpdateTeamCommand command){
        this.title = command.title();
        this.description = command.description();
        this.image = command.image();
        return this;
    }
}
