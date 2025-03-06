package com.taskmanager.backend.group.domain.model.entities;

import com.taskmanager.backend.group.domain.model.aggregates.Team;
import com.taskmanager.backend.group.domain.model.valueobjects.GroupUserId;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "group_users")
public class GroupUser {
    @EmbeddedId
    private GroupUserId id;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Team team;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;
}
