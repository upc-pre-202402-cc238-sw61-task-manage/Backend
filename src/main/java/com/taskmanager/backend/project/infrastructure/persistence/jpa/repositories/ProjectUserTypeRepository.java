package com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.project.domain.model.entities.ProjectUserType;
import com.taskmanager.backend.project.domain.model.valueobjects.ProjectUserTypeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the ProjectUserType entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface ProjectUserTypeRepository extends JpaRepository<ProjectUserType, Long> {
    Optional<ProjectUserType> findByName(ProjectUserTypeList name);
    boolean existsByName(ProjectUserTypeList name);
}
