package com.sathwikhbhat.tasktracker.mappers.impl;

import com.sathwikhbhat.tasktracker.domain.dto.TaskDto;
import com.sathwikhbhat.tasktracker.domain.entities.Task;
import com.sathwikhbhat.tasktracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                null,
                null,
                taskDto.status(),
                taskDto.priority(),
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }

}