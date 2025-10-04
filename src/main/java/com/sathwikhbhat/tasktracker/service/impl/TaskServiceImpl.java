package com.sathwikhbhat.tasktracker.service.impl;

import com.sathwikhbhat.tasktracker.domain.entities.Task;
import com.sathwikhbhat.tasktracker.domain.entities.TaskList;
import com.sathwikhbhat.tasktracker.domain.entities.TaskPriority;
import com.sathwikhbhat.tasktracker.domain.entities.TaskStatus;
import com.sathwikhbhat.tasktracker.repository.TaskListRepository;
import com.sathwikhbhat.tasktracker.repository.TaskRepository;
import com.sathwikhbhat.tasktracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID id) {
        return taskRepository.findByTaskListId(id);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list with ID " + taskListId + " does not exist"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                now,
                now,
                taskStatus,
                taskPriority,
                taskList
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTaskById(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        if (!taskId.equals(task.getId())) {
            throw new IllegalArgumentException("Task ID in path and body do not match");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }
        Task existingTask = taskRepository
                .findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + taskId + " does not exist in task list with ID " + taskListId));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

}