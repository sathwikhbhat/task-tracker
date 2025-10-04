package com.sathwikhbhat.tasktracker.service.impl;

import com.sathwikhbhat.tasktracker.domain.entities.Task;
import com.sathwikhbhat.tasktracker.repository.TaskRepository;
import com.sathwikhbhat.tasktracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID id) {
        return taskRepository.findByTaskListId(id);
    }

}