package com.saikarthik.taskmanager.dto.response;

public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private String creatorName;

    public ProjectResponse(Long id, String name, String description, String creatorName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorName = creatorName;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCreatorName() { return creatorName; }
}