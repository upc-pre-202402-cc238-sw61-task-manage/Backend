package com.taskmanager.backend.calendar.domain.model.aggregates;


import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.CreateEventCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.PatchEventDateCommand;
import com.taskmanager.backend.calendar.domain.model.commands.eventcommands.UpdateEventCommand;
import com.taskmanager.backend.calendar.domain.model.entities.EventColor;
import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.shared.domain.model.valueobjects.DateRange;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Event extends AuditableAbstractAggregateRoot<Event> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @AttributeOverride(name = "value", column = @Column(name = "title"))
    private String title;

    @AttributeOverride(name = "value", column = @Column(name = "description"))
    private String description;

    @Embedded
    private DateRange dateRange;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private EventColor color;

    public Event(CreateEventCommand command, Project project, EventColor color) {
        this.project = project;
        this.title = command.title();
        this.description = command.description();
        this.dateRange = new DateRange(command.startDate(), command.endDate());
        this.color = color;
    }

    public Event updateEvent(UpdateEventCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.dateRange.setStartDate(command.startDate());
        this.dateRange.setEndDate(command.endDate());
        return this;
    }

    public Event patchEventDate(PatchEventDateCommand command){
        this.dateRange.setStartDate(command.startDate());
        this.dateRange.setEndDate(command.endDate());
        return this;
    }

    public Event patchColor(EventColor color) {
        this.color = color;
        return this;
    }

}
