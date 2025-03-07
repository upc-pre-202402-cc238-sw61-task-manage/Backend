package com.taskmanager.backend.team.domain.model.entities;

import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamUserId;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "team_users")
public class TeamUser {
    @EmbeddedId
    private TeamUserId id;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;
}
