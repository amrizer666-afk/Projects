package com.solverminds.projectmanagement.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_code")
    private String taskCode;

    @ManyToOne(
        fetch = FetchType.LAZY)
    @JoinColumn(
        name = "project_id")
    @JsonIgnoreProperties({
        "tasks",
        "hibernateLazyInitializer",
        "handler"
    })
    private Project project;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "assigned_employee")
    private String assignedEmployee;

    @Column(name = "priority")
    private String priority;

    @Column(name = "status")
    private String status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(
            Integer id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(
            String taskCode) {
        this.taskCode = taskCode;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(
            Project project) {
        this.project = project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(
            String taskName) {
        this.taskName = taskName;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(
            String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(
            String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(
            LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}