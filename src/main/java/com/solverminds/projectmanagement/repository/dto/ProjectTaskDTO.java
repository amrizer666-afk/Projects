package com.solverminds.projectmanagement.repository.dto;

import java.time.LocalDate;

public class ProjectTaskDTO {

    public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssignedEmployee() {
		return assignedEmployee;
	}

	public void setAssignedEmployee(String assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	private String projectCode;
    private String projectName;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;

    private String taskName;
    private String assignedEmployee;
    private String priority;
    private String status;
    private LocalDate dueDate;

    public ProjectTaskDTO() {
    }

    public ProjectTaskDTO(
            String projectCode,
            String projectName,
            String clientName,
            LocalDate startDate,
            LocalDate endDate,
            String taskName,
            String assignedEmployee,
            String priority,
            String status,
            LocalDate dueDate) {

        this.projectCode = projectCode;
        this.projectName = projectName;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskName = taskName;
        this.assignedEmployee = assignedEmployee;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
    }

    // Generate getters and setters
}