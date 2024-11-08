package com.taskmanager.backend.projectUsers.domain.model.aggregates;

import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "project_users")
public class ProjectUser extends AuditableAbstractAggregateRoot<ProjectUser> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
