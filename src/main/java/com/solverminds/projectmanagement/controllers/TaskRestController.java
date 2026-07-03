package com.solverminds.projectmanagement.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.solverminds.projectmanagement.entity.Task;
import com.solverminds.projectmanagement.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/project/{projectId}")
    public List<Task> getTasks(
            @PathVariable Integer projectId) {

        return taskService
                .getTasks(
                        projectId);
    }

    @GetMapping("/{id}")
    public Task getTask(
            @PathVariable Integer id) {

        return taskService
                .getTask(id);
    }

    @PostMapping
    public String addTask(
            @RequestBody Task task,
            HttpSession session) {

        return taskService
                .addTask(
                        task,
                        session);
    }

    @PutMapping("/{id}")
    public String updateTask(
            @PathVariable Integer id,
            @RequestBody Task task,
            HttpSession session) {

        return taskService
                .updateTask(
                        id,
                        task,
                        session);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(
            @PathVariable Integer id,
            HttpSession session) {

        return taskService
                .deleteTask(
                        id,
                        session);
    }
}