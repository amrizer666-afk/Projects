package com.solverminds.projectmanagement.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.repository.dto.ProjectTaskDTO;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectTaskDTO> getProjectTasks() {

        List<Object[]> rows =
                projectRepository.getProjectTaskDetails();

        List<ProjectTaskDTO> result =
                new ArrayList<>();

        for(Object[] row : rows) {

            ProjectTaskDTO dto =
                    new ProjectTaskDTO();
           
            dto.setProjectCode((String) row[0]);
            dto.setProjectName((String) row[1]);
            dto.setClientName((String) row[2]);
            dto.setStartDate(
            	    row[3] != null
            	        ? ((java.sql.Date) row[3]).toLocalDate()
            	        : null);

            	dto.setEndDate(
            	    row[4] != null
            	        ? ((java.sql.Date) row[4]).toLocalDate()
            	        : null);
            dto.setTaskName((String) row[5]);
            dto.setAssignedEmployee((String) row[6]);
            dto.setPriority((String) row[7]);
            dto.setStatus((String) row[8]);
            dto.setDueDate(
            	    row[9] != null
            	        ? ((java.sql.Date) row[9]).toLocalDate()
            	        : null);

            result.add(dto);
        }

        return result;
    }
}