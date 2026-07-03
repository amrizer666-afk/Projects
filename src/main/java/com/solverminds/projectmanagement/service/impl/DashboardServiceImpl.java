package com.solverminds.projectmanagement.service.impl;

import java.util.ArrayList;
 import com.solverminds.projectmanagement.coustomexception.DashboardException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.Project;
import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.repository.TaskRepository;
import com.solverminds.projectmanagement.repository.dto.DashboardDTO;
import com.solverminds.projectmanagement.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public DashboardDTO getDashboard() {

        DashboardDTO dto = new DashboardDTO();

        try {

            dto.setTotalProjects(
                    projectRepository.count());

            dto.setTotalTasks(
                    taskRepository.count());

            List<Project> projects =
                    projectRepository.findAll();

            List<String> names =
                    new ArrayList<>();

            List<Long> counts =
                    new ArrayList<>();

            for (Project p : projects) {

                names.add(
                        p.getProjectName());

                counts.add(
                        taskRepository
                                .countByProjectId(
                                        p.getId()));
            }

            dto.setProjectNames(names);
            dto.setTaskCounts(counts);


        } catch (Exception e) {

            throw new DashboardException(
                    "Error while loading dashboard data",
                    e);
        }

        return dto;
    }
}