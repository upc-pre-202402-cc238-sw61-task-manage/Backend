package com.taskmanager.backend.project.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.project.domain.model.entities.ProjectUser;
import com.taskmanager.backend.project.domain.model.valueobjects.ProjectUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, ProjectUserId> {
    @Query("SELECT pu FROM ProjectUser  pu WHERE pu.id.projectId = :projectId")
    List<ProjectUser> findAllUsersByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT pu FROM ProjectUser pu WHERE pu.id.userId = :userId")
    List<ProjectUser> findAllProjectsByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProjectUser pu WHERE pu.project.id = :projectId")
    void removeAllUsersFromProjectByProjectId(@Param("projectId") Long projectId);

    @Query(value = """
        SELECT
	        pu.user_id AS user_id, pf.first_name AS first_name, pf.last_name AS last_name, pf.profile_picture AS profile_picture
            FROM project_users pu
	            JOIN users u ON u.id = pu.user_id
	            JOIN profiles pf ON u.profile_id = pf.id
            WHERE pu.project_id = :projectId
    """, nativeQuery = true)
    List<Object[]> getAllProfilesByProjectId(@Param("projectId") Long projectId);
}
