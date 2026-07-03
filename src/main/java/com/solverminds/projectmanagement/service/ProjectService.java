package com.solverminds.projectmanagement.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.solverminds.projectmanagement.entity.Project;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProject(Integer id);

    String addProject(
            Project project,
            HttpSession session);

    String updateProject(
            Integer id,
            Project project,
            HttpSession session);

    String deleteProject(
            Integer id,
            HttpSession session);
}