package com.taskmanager.backend.project.domain.model.entities;

import com.taskmanager.backend.project.domain.model.valueobjects.ProjectUserTypeList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_user_type")
public class ProjectUserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ProjectUserTypeList name;


    public ProjectUserType(ProjectUserTypeList name) {
        this.name = name;
    }

    public String getTypeName(){
        return name.name();
    }
}
