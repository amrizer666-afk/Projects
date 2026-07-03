package com.solverminds.projectmanagement.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.Project;
import com.solverminds.projectmanagement.entity.Task;
import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.repository.TaskRepository;
import com.solverminds.projectmanagement.service.ActivityLogService;
import com.solverminds.projectmanagement.service.TaskService;
import com.solverminds.projectmanagement.coustomexception.TaskException;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ActivityLogService activityLogService;

    @Override
    public List<Task> getTasks(Integer projectId) {

        try {

            return taskRepository
                    .findByProjectId(
                            projectId);

        } catch (Exception e) {

            throw new TaskException(
                    "Unable to fetch tasks",
                    e);
        }
    }

    @Override
    public Task getTask(Integer id) {

        try {

            return taskRepository
                    .findById(id)
                    .orElse(null);

        } catch (Exception e) {

            throw new TaskException(
                    "Unable to fetch task",
                    e);
        }
    }

    @Override
    public String addTask(
            Task task,
            HttpSession session) {

        User user =
                (User) session
                        .getAttribute(
                                "user");

        Project project = null;

        try {

            if (user == null)
                throw new TaskException(
                        "Please Login");

            if ("WORKER".equals(
                    user.getRole()))
                throw new TaskException(
                        "Access Denied");

            project =
                    projectRepository
                            .findById(
                                    task.getProject()
                                            .getId())
                            .orElseThrow(
                                    () ->
                                            new TaskException(
                                                    "Project Not Found"));

            if (task.getDueDate()
                    .isAfter(
                            project.getEndDate())) {

                throw new TaskException(
                        "Task due date cannot exceed project end date");
            }

            long count =
                    taskRepository.count()
                            + 1;

            task.setTaskCode(
                    String.format(
                            "TSK%03d",
                            count));

            taskRepository.save(
                    task);

            activityLogService
                    .saveLog(
                            user.getUsername(),
                            user.getRole(),
                            "CREATE",
                            "TASK",
                            task.getTaskCode(),
                            "Created task : "
                                    + task.getTaskName()
                                    + " for project "
                                    + project.getProjectCode(),
                            "LOCALHOST");

            return "TASK CREATED";

        } catch (Exception e) {

            try {

                activityLogService
                        .saveLog(
                                user != null
                                        ? user.getUsername()
                                        : "SYSTEM",

                                user != null
                                        ? user.getRole()
                                        : "SYSTEM",

                                "ERROR",

                                "TASK",

                                task.getTaskCode(),

                                "Task creation failed : "
                                        + e.getMessage(),

                                "LOCALHOST");

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            throw new TaskException(
                    "Unable to create task",
                    e);
        }
    }

    @Override
    public String updateTask(
            Integer id,
            Task task,
            HttpSession session) {

        User user =
                (User) session
                        .getAttribute(
                                "user");

        Task dbTask = null;

        try {

            if (user == null)
                throw new TaskException(
                        "Please Login");

            if ("WORKER".equals(
                    user.getRole()))
                throw new TaskException(
                        "Access Denied");

            dbTask =
                    taskRepository
                            .findById(id)
                            .orElseThrow(
                                    () ->
                                            new TaskException(
                                                    "Task Not Found"));

            if ("COMPLETED"
                    .equals(
                            dbTask.getStatus())
                    &&
                    !"ADMIN"
                            .equals(
                                    user.getRole())) {

                throw new TaskException(
                        "Completed task can be edited only by ADMIN");
            }

            Project project =
                    dbTask.getProject();

            if (task.getDueDate()
                    .isAfter(
                            project.getEndDate())) {

                throw new TaskException(
                        "Task due date cannot exceed project end date");
            }

            dbTask.setTaskName(
                    task.getTaskName());

            dbTask.setAssignedEmployee(
                    task.getAssignedEmployee());

            dbTask.setPriority(
                    task.getPriority());

            dbTask.setStatus(
                    task.getStatus());

            dbTask.setDueDate(
                    task.getDueDate());

            taskRepository
                    .save(
                            dbTask);

            activityLogService
                    .saveLog(
                            user.getUsername(),
                            user.getRole(),
                            "UPDATE",
                            "TASK",
                            dbTask.getTaskCode(),
                            "Updated task : "
                                    + dbTask.getTaskName(),
                            "LOCALHOST");

            return "TASK UPDATED";

        } catch (Exception e) {

            try {

                activityLogService
                        .saveLog(
                                user != null
                                        ? user.getUsername()
                                        : "SYSTEM",

                                user != null
                                        ? user.getRole()
                                        : "SYSTEM",

                                "ERROR",

                                "TASK",

                                dbTask != null
                                        ? dbTask.getTaskCode()
                                        : String.valueOf(
                                                id),

                                "Task update failed : "
                                        + e.getMessage(),

                                "LOCALHOST");

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            throw new TaskException(
                    "Unable to update task",
                    e);
        }
    }

    @Override
    public String deleteTask(
            Integer id,
            HttpSession session) {

        User user =
                (User) session
                        .getAttribute(
                                "user");

        Task task = null;

        try {

            if (user == null)
                throw new TaskException(
                        "Please Login");

            if (!"ADMIN".equals(
                    user.getRole())) {

                throw new TaskException(
                        "Only ADMIN can delete");
            }

            task =
                    taskRepository
                            .findById(id)
                            .orElseThrow(
                                    () ->
                                            new TaskException(
                                                    "Task Not Found"));

            String taskCode =
                    task.getTaskCode();

            String taskName =
                    task.getTaskName();

            taskRepository
                    .deleteById(id);

            activityLogService
                    .saveLog(
                            user.getUsername(),
                            user.getRole(),
                            "DELETE",
                            "TASK",
                            taskCode,
                            "Deleted task : "
                                    + taskName,
                            "LOCALHOST");

            return "TASK DELETED";

        } catch (Exception e) {

            try {

                activityLogService
                        .saveLog(
                                user != null
                                        ? user.getUsername()
                                        : "SYSTEM",

                                user != null
                                        ? user.getRole()
                                        : "SYSTEM",

                                "ERROR",

                                "TASK",

                                task != null
                                        ? task.getTaskCode()
                                        : String.valueOf(
                                                id),

                                "Task delete failed : "
                                        + e.getMessage(),

                                "LOCALHOST");

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            throw new TaskException(
                    "Unable to delete task",
                    e);
        }
    }
}