package com.sathwikhbhat.tasktracker.service;

import com.sathwikhbhat.tasktracker.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList taskList);

}