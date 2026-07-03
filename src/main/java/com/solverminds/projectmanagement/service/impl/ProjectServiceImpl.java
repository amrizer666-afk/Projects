package com.solverminds.projectmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.Project;
import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.repository.TaskRepository;
import com.solverminds.projectmanagement.service.ActivityLogService;
import com.solverminds.projectmanagement.service.ProjectService;
import com.solverminds.projectmanagement.coustomexception.ProjectException;


@Service
public class ProjectServiceImpl
        implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ActivityLogService activityLogService;

    @Override
    public List<Project> getAllProjects() {

        return projectRepository.findAll();
    }

    @Override
    public Project getProject(
            Integer id) {

        return projectRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public String addProject(
            Project project,
            HttpSession session) {

        try {

            User user =
                    (User) session
                            .getAttribute(
                                    "user");

            if (user == null)
                throw new ProjectException(
                        "Please Login");

            if ("WORKER".equals(
                    user.getRole()))
                throw new ProjectException(
                        "Access Denied");

            projectRepository
                    .save(project);

            project.setProjectCode(
                    String.format(
                            "AM%03d",
                            project.getId()));

            projectRepository
                    .save(project);

            activityLogService
                    .saveLog(
                            user.getUsername(),
                            user.getRole(),
                            "CREATE",
                            "PROJECT",
                            project.getProjectCode(),
                            "Created project : "
                                    + project.getProjectName(),
                            "LOCALHOST");

            return "SUCCESS";

        } catch (Exception e) {

            try {

                User user =
                        (User) session
                                .getAttribute(
                                        "user");

                activityLogService
                        .saveLog(
                                user != null
                                        ? user.getUsername()
                                        : "SYSTEM",
                                user != null
                                        ? user.getRole()
                                        : "SYSTEM",
                                "ERROR",
                                "PROJECT",
                                project.getProjectCode(),
                                e.getMessage(),
                                "LOCALHOST");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            throw new ProjectException(
                    "Unable to create project",
                    e);
        }
    }

    @Override
    public String updateProject(
            Integer id,
            Project p,
            HttpSession session) {

        User user =
                (User) session.getAttribute("user");

        try {

            if (user == null)
                return "LOGIN";

            if ("WORKER".equals(
                    user.getRole()))
                return "ACCESS DENIED";

            Optional<Project> opt =
                    projectRepository
                            .findById(id);

            if (!opt.isPresent())
                return "NOT FOUND";

            Project db = opt.get();

            db.setProjectName(
                    p.getProjectName());

            db.setClientName(
                    p.getClientName());

            db.setStartDate(
                    p.getStartDate());

            db.setEndDate(
                    p.getEndDate());

            projectRepository.save(db);

            activityLogService.saveLog(
                    user.getUsername(),
                    user.getRole(),
                    "UPDATE",
                    "PROJECT",
                    db.getProjectCode(),
                    "Updated project : "
                            + db.getProjectName(),
                    "LOCALHOST");

            return "UPDATED";

        } catch (Exception e) {

            try {

                activityLogService.saveLog(
                        user != null
                                ? user.getUsername()
                                : "SYSTEM",
                        user != null
                                ? user.getRole()
                                : "SYSTEM",
                        "ERROR",
                        "PROJECT",
                        String.valueOf(id),
                        "Project update failed : "
                                + e.getMessage(),
                        "LOCALHOST");

            } catch (Exception logEx) {

                logEx.printStackTrace();
            }

            throw new ProjectException(
                    "Unable to update project",
                    e);
        }
    }

    @Override
    public String deleteProject(
            Integer id,
            HttpSession session) {

        User user =
                (User) session.getAttribute(
                        "user");

        Project project = null;

        try {

            if (user == null)
                return "LOGIN";

            if (!"ADMIN".equals(
                    user.getRole()))
                return "ACCESS DENIED";

            Optional<Project> opt =
                    projectRepository
                            .findById(id);

            if (!opt.isPresent())
                return "NOT FOUND";

            project = opt.get();

            taskRepository.deleteAll(
                    taskRepository
                            .findByProjectId(
                                    id));

            projectRepository
                    .deleteById(id);

            activityLogService
                    .saveLog(
                            user.getUsername(),
                            user.getRole(),
                            "DELETE",
                            "PROJECT",
                            project.getProjectCode(),
                            "Deleted project : "
                                    + project.getProjectName(),
                            "LOCALHOST");

            return "DELETED";

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

                                "PROJECT",

                                project != null
                                        ? project.getProjectCode()
                                        : String.valueOf(id),

                                "Project delete failed : "
                                        + e.getMessage(),

                                "LOCALHOST");

            } catch (Exception logEx) {

                logEx.printStackTrace();
            }

            throw new ProjectException(
                    "Unable to delete project",
                    e);
        }
    }
}