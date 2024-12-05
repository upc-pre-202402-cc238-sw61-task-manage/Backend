package com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.iam.domain.model.entities.Role;
import com.taskmanager.backend.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the Role entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * This method is responsible for finding the role by title.
     * @param name The role title.
     * @return The role object.
     */
    Optional<Role> findByName(Roles name);

    /**
     * This method is responsible for checking if the role exists by title.
     * @param name The role title.
     * @return True if the role exists, false otherwise.
     */
    boolean existsByName(Roles name);

}