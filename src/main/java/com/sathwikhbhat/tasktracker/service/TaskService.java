package com.sathwikhbhat.tasktracker.service;

import com.sathwikhbhat.tasktracker.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID id);

    Task createTask(UUID taskListId, Task task);

    Optional<Task> getTaskById(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskListId, UUID taskId, Task task);

    void deleteTask(UUID taskListId, UUID taskId);

}