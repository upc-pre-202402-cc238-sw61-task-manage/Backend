package com.taskmanager.backend.group.infrastructure.persistence.jpa.repositories;

import com.taskmanager.backend.group.domain.model.entities.GroupUser;
import com.taskmanager.backend.group.domain.model.valueobjects.GroupUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUserId> {

    @Query("SELECT gu FROM GroupUser gu WHERE gu.id.groupId = :groupId")
    List<GroupUser> findAllByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT gu FROM GroupUser gu WHERE gu.id.userId = :userId")
    List<GroupUser> findAllByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM GroupUser gu WHERE gu.group.id = :groupId")
    void deleteAllUsersFromGroupById(@Param("groupId") Long groupId);
}
