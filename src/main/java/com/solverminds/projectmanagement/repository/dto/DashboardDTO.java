package com.solverminds.projectmanagement.repository.dto;

import java.util.List;

public class DashboardDTO {

    public Long getTotalProjects() {
		return totalProjects;
	}
	public void setTotalProjects(Long totalProjects) {
		this.totalProjects = totalProjects;
	}
	public Long getTotalTasks() {
		return totalTasks;
	}
	public void setTotalTasks(Long totalTasks) {
		this.totalTasks = totalTasks;
	}
	public List<String> getProjectNames() {
		return projectNames;
	}
	public void setProjectNames(List<String> projectNames) {
		this.projectNames = projectNames;
	}
	public List<Long> getTaskCounts() {
		return taskCounts;
	}
	public void setTaskCounts(List<Long> taskCounts) {
		this.taskCounts = taskCounts;
	}
	private Long totalProjects;
    private Long totalTasks;
    private List<String> projectNames;
    private List<Long> taskCounts;
    
	@Override
	public String toString() {
		return "DashboardDTO [totalProjects=" + totalProjects + ", totalTasks=" + totalTasks + ", projectNames="
				+ projectNames + ", taskCounts=" + taskCounts + "]";
	}
    
    

    // getters and setters
}