package com.solverminds.projectmanagement.service;

import java.util.List;

import com.solverminds.projectmanagement.entity.ActivityLog;
import com.solverminds.projectmanagement.entity.Project;

public interface PageService {

    List<Project> getAllProjects();

    Project getProjectById(Integer projectId);

    List<ActivityLog> getAllActivityLogs();
}