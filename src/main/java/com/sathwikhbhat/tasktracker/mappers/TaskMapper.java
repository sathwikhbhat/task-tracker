package com.sathwikhbhat.tasktracker.mappers;

import com.sathwikhbhat.tasktracker.domain.dto.TaskDto;
import com.sathwikhbhat.tasktracker.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}