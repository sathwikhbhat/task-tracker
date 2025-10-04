package com.sathwikhbhat.tasktracker.mappers;

import com.sathwikhbhat.tasktracker.domain.dto.TaskListDto;
import com.sathwikhbhat.tasktracker.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}