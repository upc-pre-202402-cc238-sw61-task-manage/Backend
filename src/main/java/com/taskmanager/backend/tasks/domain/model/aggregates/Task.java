package com.taskmanager.backend.tasks.domain.model.aggregates;

import com.taskmanager.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.taskmanager.backend.tasks.domain.model.commands.CreateTaskCommand;
import com.taskmanager.backend.tasks.domain.model.commands.UpdateTaskCommand;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Long assignUser;

    public Task(CreateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        this.dueDate = command.dueDate();
        this.projectId = command.projectUUID();
        this.assignUser = command.assignUser();
    }

    public Task updateTask(UpdateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        this.dueDate = command.dueDate();
        this.assignUser = command.assignUser();
        return this;
    }
}
