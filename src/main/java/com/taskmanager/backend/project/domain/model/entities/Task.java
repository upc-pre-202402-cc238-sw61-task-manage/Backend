package com.taskmanager.backend.project.domain.model.entities;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.PatchTaskStatusCommand;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.CreateTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskcommands.UpdateTaskCommand;
import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task extends AuditableAbstractAggregateRoot<Task> {
    @AttributeOverride(name = "value", column = @Column(name = "title"))
    private String title;

    @AttributeOverride(name = "value", column = @Column(name = "description"))
    private String description;

    @AttributeOverride(name = "value", column = @Column(name = "due_date"))
    private LocalDate dueDate;

    @Getter
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @AttributeOverride(name = "value", column = @Column(name = "assign_user"))
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    public Task(CreateTaskCommand command, Project project){
        this.title = command.title();
        this.description = command.description();
        this.dueDate = command.dueDate();
        this.project = project;
        this.userId = command.userId();
        this.status = TaskStatus.NEW;
    }

    public Task updateTask(UpdateTaskCommand command){
        this.title = command.title();
        this.description = command.description();
        this.dueDate = command.dueDate();
        this.userId = command.userId();
        this.status = command.status();
        return this;
    }

    public Task patchTaskStatus(PatchTaskStatusCommand command){
        this.status = command.status();
        return this;
    }

}
