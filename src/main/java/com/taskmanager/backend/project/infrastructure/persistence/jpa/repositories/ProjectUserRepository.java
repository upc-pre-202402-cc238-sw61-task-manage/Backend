package com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.project.domain.model.entities.ProjectUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    List<ProjectUser> findByProjectId(Long projectId);
    List<ProjectUser> findByUserId(Long userId);
    Optional<ProjectUser> findByProjectIdAndUserId(Long projectId, Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProjectUser pu WHERE pu.project.id = :projectId")
    void deleteByProjectId(@Param("projectId") Long projectId);
}
