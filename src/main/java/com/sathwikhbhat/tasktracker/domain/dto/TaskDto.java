package com.sathwikhbhat.tasktracker.domain.dto;

import com.sathwikhbhat.tasktracker.domain.entities.TaskPriority;
import com.sathwikhbhat.tasktracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}