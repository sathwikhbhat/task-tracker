package com.sathwikhbhat.tasktracker.service.impl;

import com.sathwikhbhat.tasktracker.domain.entities.TaskList;
import com.sathwikhbhat.tasktracker.repository.TaskListRepository;
import com.sathwikhbhat.tasktracker.service.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

}