package com.saikarthik.taskmanager.repository;

import com.saikarthik.taskmanager.model.entity.Role;
import com.saikarthik.taskmanager.model.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}