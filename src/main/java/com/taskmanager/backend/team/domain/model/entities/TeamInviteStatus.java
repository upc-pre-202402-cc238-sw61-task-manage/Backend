package com.taskmanager.backend.team.domain.model.entities;

import com.taskmanager.backend.team.domain.model.valueobjects.TeamInviteStatusList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invite_status")
public class TeamInviteStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TeamInviteStatusList status;

    public TeamInviteStatus(TeamInviteStatusList status) {
        this.status = status;
    }
}
