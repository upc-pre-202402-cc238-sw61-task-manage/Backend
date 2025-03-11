package com.taskmanager.backend.team.domain.model.entities;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.team.domain.model.aggregates.Team;
import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatusList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team_invites")
public class TeamInvite extends AuditableAbstractAggregateRoot<TeamInvite> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "invited_user_id", nullable = false)
    private User invitedUser;

    @ManyToOne
    @JoinColumn(name = "inviting_user_id", nullable = false)
    private User invitingUser;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TeamInviteStatus status;

    public TeamInvite(Team team, User invitedUser, User invitingUser, TeamInviteStatus status) {
        this.team = team;
        this.invitedUser = invitedUser;
        this.invitingUser = invitingUser;
        this.status = status;
    }

    public void updateStatus(TeamInviteStatus status) {
        this.status = status;
    }
}
