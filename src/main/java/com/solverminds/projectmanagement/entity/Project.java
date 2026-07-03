package com.solverminds.projectmanagement.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(
        strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="project_code")
    private String projectCode;

    @Column(name="project_name")
    private String projectName;

    @Column(name="client_name")
    private String clientName;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="status")
    private String status;

    @OneToMany(
        mappedBy="project",
        cascade=CascadeType.ALL,
        fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Task> tasks;

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(
            String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(
            String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(
            String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(
            LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(
            LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(
            List<Task> tasks) {
        this.tasks = tasks;
    }
}