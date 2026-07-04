package com.saikarthik.taskmanager.service;

import com.saikarthik.taskmanager.dto.request.TaskRequest;
import com.saikarthik.taskmanager.dto.response.TaskResponse;
import com.saikarthik.taskmanager.model.entity.Project;
import com.saikarthik.taskmanager.model.entity.Task;
import com.saikarthik.taskmanager.model.entity.TaskStatus;
import com.saikarthik.taskmanager.model.entity.User;
import com.saikarthik.taskmanager.repository.ProjectRepository;
import com.saikarthik.taskmanager.repository.TaskRepository;
import com.saikarthik.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskResponse createTask(TaskRequest request, String email) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User assignedTo = userRepository.findById(request.getAssignedToId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProject(project);
        task.setAssignedTo(assignedTo);
        task.setStatus(TaskStatus.TODO);

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public Page<TaskResponse> getTasksByProject(Long projectId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findByProjectId(projectId, pageable)
                .map(this::mapToResponse);
    }

    public Page<TaskResponse> getTasksByProjectAndStatus(Long projectId, TaskStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findByProjectIdAndStatus(projectId, status, pageable)
                .map(this::mapToResponse);
    }

    public Page<TaskResponse> getMyTasks(String email, int page, int size) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findByAssignedToId(user.getId(), pageable)
                .map(this::mapToResponse);
    }

    public TaskResponse updateTaskStatus(Long id, TaskStatus status, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getAssignedTo().getEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to update this task");
        }

        task.setStatus(status);
        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    public void deleteTask(Long id, String email) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getAssignedTo().getEmail().equals(email)) {
            throw new RuntimeException("You are not authorized to delete this task");
        }

        taskRepository.delete(task);
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProject().getName(),
                task.getAssignedTo().getName()
        );
    }
}