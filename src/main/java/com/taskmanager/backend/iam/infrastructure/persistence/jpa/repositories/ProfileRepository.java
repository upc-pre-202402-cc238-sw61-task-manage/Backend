package com.taskmanager.backend.iam.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.iam.domain.model.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * This interface is responsible for providing the Profile entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmail(String email);
}
