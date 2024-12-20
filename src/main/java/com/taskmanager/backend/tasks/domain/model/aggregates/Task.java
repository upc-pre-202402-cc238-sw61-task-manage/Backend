package com.taskmanager.backend.tasks.domain.model.aggregates;

import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.tasks.domain.model.commands.CreateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.UpdateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.valueObjects.TaskStatus;
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
    @AttributeOverride(name = "value", column = @Column(name = "task_name"))
    private String taskName;

    @AttributeOverride(name = "value", column = @Column(name = "task_description"))
    private String taskDescription;

    @AttributeOverride(name = "value", column = @Column(name = "due_date"))
    private LocalDate dueDate;

    @AttributeOverride(name = "value", column = @Column(name = "project_id"))
    private Long projectId;

    @AttributeOverride(name = "value", column = @Column(name = "asign_user"))
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    public Task(CreateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        this.dueDate = command.dueDate();
        this.projectId = command.projectUUID();
        this.userId = command.assignUser();
        this.status = TaskStatus.NEW;
    }

    public Task updateTask(UpdateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        this.dueDate = command.dueDate();
        this.userId = command.assignUser();
        this.status = command.status();
        return this;
    }
}
