package com.sathwikhbhat.tasktracker.service;

import com.sathwikhbhat.tasktracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID id);

}