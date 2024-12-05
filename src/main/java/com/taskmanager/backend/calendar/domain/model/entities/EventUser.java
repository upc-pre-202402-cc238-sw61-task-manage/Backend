package com.taskmanager.backend.calendar.domain.model.entities;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
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
@Table(name = "event_users")
public class EventUser extends AuditableAbstractAggregateRoot<EventUser> {
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
