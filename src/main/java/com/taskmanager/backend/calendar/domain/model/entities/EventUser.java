package com.taskmanager.backend.calendar.domain.model.entities;

import com.taskmanager.backend.calendar.domain.model.aggregates.Event;
import com.taskmanager.backend.calendar.domain.model.valueobjects.EventUserId;
import com.taskmanager.backend.iam.domain.model.aggregates.User;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "event_users")
public class EventUser extends AuditableAbstractAggregateRoot<EventUser> {
    @EmbeddedId
    private EventUserId id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;
}
