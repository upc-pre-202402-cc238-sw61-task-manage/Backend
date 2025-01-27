package com.taskmanager.backend.project.domain.model.entities;

import com.taskmanager.backend.project.domain.model.aggregates.Project;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.PatchTaskStatusCommand;
import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.CreateTaskCommand;
import com.taskmanager.backend.project.domain.model.commands.taskCommands.UpdateTaskCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task extends AuditableAbstractAggregateRoot<Task> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "value", column = @Column(name = "title"))
    private String title;

    @AttributeOverride(name = "value", column = @Column(name = "description"))
    private String description;

    @AttributeOverride(name = "value", column = @Column(name = "due_date"))
    private LocalDateTime dueDate;

    @Getter
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @AttributeOverride(name = "value", column = @Column(name = "assign_user"))
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TaskStatus status;

    public Task(CreateTaskCommand command, Project project, TaskStatus status){
        this.title = command.title();
        this.description = command.description();
        this.dueDate = command.dueDate();
        this.project = project;
        this.userId = command.userId();
        this.status = status;
    }

    public Task updateTask(UpdateTaskCommand command, TaskStatus status){
        this.title = command.title();
        this.description = command.description();
        this.dueDate = command.dueDate();
        this.userId = command.userId();
        this.status = status;
        return this;
    }

    public Task patchTaskStatus(TaskStatus status){
        this.status = status;
        return this;
    }

}
