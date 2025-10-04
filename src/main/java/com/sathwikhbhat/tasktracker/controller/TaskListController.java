package com.sathwikhbhat.tasktracker.controller;

import com.sathwikhbhat.tasktracker.domain.dto.TaskListDto;
import com.sathwikhbhat.tasktracker.mappers.TaskListMapper;
import com.sathwikhbhat.tasktracker.service.TaskListService;
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
@RequestMapping("/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        return taskListMapper.toDto(
                taskListService.createTaskList(
                        taskListMapper.fromDto(taskListDto))
        );
    }

    @GetMapping("/{id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable UUID id) {
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PutMapping("/{id}")
    public TaskListDto updateTaskList(@PathVariable UUID id, @RequestBody TaskListDto taskListDto) {
        return taskListMapper.toDto(
                taskListService.updateTaskList(id,
                        taskListMapper.fromDto(taskListDto))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable UUID id) {
        taskListService.deleteTaskList(id);
    }

}