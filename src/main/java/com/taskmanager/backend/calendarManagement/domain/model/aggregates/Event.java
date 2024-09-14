package com.taskmanager.backend.calendarManagement.domain.model.aggregates;


import com.taskmanager.backend.calendarManagement.domain.model.commands.CreateEventCommand;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.Project;
import com.taskmanager.backend.calendarManagement.domain.model.valueobjects.User;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

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
    private int day;

    @Column
    private int month;

    @Column
    private int year;


    public Event() {
        this.project = new Project(null);
        this.title = "";
        this.description = "";
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Event(CreateEventCommand command) {
        this.user = new User(command.user());
        this.project = new Project(command.project());
        this.title = command.title();
        this.description = command.description();
        this.day = command.day();
        this.month = command.month();
        this.year = command.year();
    }

    public Event updateInformation(String title, String description, int day, int month, int year) {
        this.title = title;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
        return this;
    }

    public Long getProjectId() { return project.projectEnt();}
    public Long getUserId() { return user.userEnt();}

}
