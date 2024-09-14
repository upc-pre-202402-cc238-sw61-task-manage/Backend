package com.taskmanager.backend.calendarManagement.domain.model.aggregates;


import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Event extends AuditableAbstractAggregateRoot<Event> {


}
