package com.taskmanager.backend.calendarManagement.domain.model.aggregates;


import com.taskmanager.backend.calendarManagement.domain.model.commands.CreateEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.Project;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.User;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Event extends AuditableAbstractAggregateRoot<Event> {

    @Embedded
    private User user;

    @Embedded
    private Project project;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDate dueDate;

    public Event() {
        this.project = new Project(null);
        this.title = "";
        this.description = "";
        this.dueDate = LocalDate.now();
    }

    public Event(CreateEventCommand command) {
        this.user = new User(command.user());
        this.project = new Project(command.project());
        this.title = command.title();
        this.description = command.description();
        this.dueDate = command.dueDate();
    }

    public Event updateInformation(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        return this;
    }

    public Long getProjectId() { return project.projectEnt();}
    public Long getUserId() { return user.userEnt();}

}
