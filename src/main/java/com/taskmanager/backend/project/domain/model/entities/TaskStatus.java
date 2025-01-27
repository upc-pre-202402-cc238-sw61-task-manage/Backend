package com.taskmanager.backend.project.domain.model.entities;

import com.taskmanager.backend.project.domain.model.valueobjects.TaskStatusList;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TaskStatusList name;

    public TaskStatus(TaskStatusList name) {
        this.name = name;
    }

    public String getStatusName(){
        return name.name();
    }
}
