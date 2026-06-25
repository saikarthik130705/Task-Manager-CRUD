package com.saikarthik.taskmanager.repository;

import com.saikarthik.taskmanager.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCreatorId(Long creatorId);
}