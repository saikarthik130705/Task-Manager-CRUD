package com.saikarthik.taskmanager.dto.response;

import com.saikarthik.taskmanager.model.entity.TaskStatus;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String projectName;
    private String assignedToName;

    public TaskResponse(Long id, String title, String description, TaskStatus status, String projectName, String assignedToName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectName = projectName;
        this.assignedToName = assignedToName;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public String getProjectName() { return projectName; }
    public String getAssignedToName() { return assignedToName; }
}