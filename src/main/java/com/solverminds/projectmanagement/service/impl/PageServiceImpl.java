package com.solverminds.projectmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.ActivityLog;
import com.solverminds.projectmanagement.entity.Project;
import com.solverminds.projectmanagement.repository.ActivityLogRepository;
import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.service.PageService;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private ProjectRepository projectRepository;

    
	@Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public List<Project> getAllProjects() {

        try {

            return projectRepository.findAll();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to fetch projects",
                    e);
        }
    }

    @Override
    public Project getProjectById(
            Integer projectId) {

        try {

            return projectRepository
                    .findById(projectId)
                    .orElse(null);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to fetch project",
                    e);
        }
    }

    @Override
    public List<ActivityLog> getAllActivityLogs() {

        try {

            return activityLogRepository
                    .findAll();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to fetch activity logs",
                    e);
        }
    }
}