package com.taskmanager.backend.calendar.domain.model.aggregates;


import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.CreateEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventColorCommand;
import com.taskmanager.backend.calendar.domain.model.valueObjects.EventColor;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Event extends AuditableAbstractAggregateRoot<Event> {

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @AttributeOverride(name = "value", column = @Column(name = "title"))
    private String title;

    @AttributeOverride(name = "value", column = @Column(name = "description"))
    private String description;

    @AttributeOverride(name = "value", column = @Column(name = "date"))
    @FutureOrPresent
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name="color")
    private EventColor color;


    public Event(CreateEventCommand command, Project project) {
        this.project = project;
        this.title = command.title();
        this.description = command.description();
        this.date = command.date();
        this.color = EventColor.BLUE;
    }

    public Event updateEvent(String title, String description, LocalDateTime date) {
        this.title = title;
        this.description = description;
        this.date = date;
        return this;
    }

    public Event patchColor(PatchEventColorCommand command) {
        this.color = command.color();
        return this;
    }

}
