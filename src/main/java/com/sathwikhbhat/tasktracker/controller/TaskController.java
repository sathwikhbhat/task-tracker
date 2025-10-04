package com.sathwikhbhat.tasktracker.controller;

import com.sathwikhbhat.tasktracker.domain.dto.TaskDto;
import com.sathwikhbhat.tasktracker.mappers.TaskMapper;
import com.sathwikhbhat.tasktracker.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{taskListId}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable UUID taskListId) {
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable UUID taskListId, @RequestBody TaskDto taskDto) {
        return taskMapper.toDto(taskService.createTask(taskListId, taskMapper.fromDto(taskDto)));
    }

    @GetMapping("/{taskId}")
    public Optional<TaskDto> getTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) {
        return taskService.getTaskById(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping("/{taskId}")
    public TaskDto updateTask(@PathVariable UUID taskListId, @PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        return taskMapper.toDto(taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto)));
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }

}