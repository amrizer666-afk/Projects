package com.solverminds.projectmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.solverminds.projectmanagement.entity.Project;

public interface ProjectRepository
        extends JpaRepository<Project,Integer>{

	Project findByProjectCode(String projectCode);
	
	  @Query(
		        value = "SELECT " +
		                "p.project_code, " +
		                "p.project_name, " +
		                "p.client_name, " +
		                "p.start_date, " +
		                "p.end_date, " +
		                "t.task_name, " +
		                "t.assigned_employee, " +
		                "t.priority, " +
		                "t.status, " +
		                "t.due_date " +
		                "FROM projects p " +
		                "LEFT JOIN tasks t " +
		                "ON p.id = t.project_id " +
		                "ORDER BY p.project_name",
		        nativeQuery = true)
		    List<Object[]> getProjectTaskDetails();

}