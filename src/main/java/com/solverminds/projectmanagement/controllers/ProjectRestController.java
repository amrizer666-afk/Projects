package com.solverminds.projectmanagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.solverminds.projectmanagement.entity.Project;
import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.repository.TaskRepository;
import com.solverminds.projectmanagement.service.ActivityLogService;
import com.solverminds.projectmanagement.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAll() {

        return projectService
                .getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getOne(
            @PathVariable Integer id) {

        return projectService
                .getProject(id);
    }

    @PostMapping
    public String add(
            @RequestBody Project project,
            HttpSession session) {

        return projectService
                .addProject(
                        project,
                        session);
    }

    @PutMapping("/{id}")
    public String update(
            @PathVariable Integer id,
            @RequestBody Project project,
            HttpSession session) {

        return projectService
                .updateProject(
                        id,
                        project,
                        session);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Integer id,
            HttpSession session) {

        return projectService
                .deleteProject(
                        id,
                        session);
    }
}