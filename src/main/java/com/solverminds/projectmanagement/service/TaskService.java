package com.solverminds.projectmanagement.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.solverminds.projectmanagement.entity.Task;

public interface TaskService {

    List<Task> getTasks(Integer projectId);

    Task getTask(Integer id);

    String addTask(
            Task task,
            HttpSession session);

    String updateTask(
            Integer id,
            Task task,
            HttpSession session);

    String deleteTask(
            Integer id,
            HttpSession session);
}

