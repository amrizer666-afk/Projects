package com.solverminds.projectmanagement.controllers;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.solverminds.projectmanagement.entity.Project;
import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.repository.ActivityLogRepository;
import com.solverminds.projectmanagement.repository.ProjectRepository;
import com.solverminds.projectmanagement.service.PageService;
import com.solverminds.projectmanagement.service.ProjectTaskService;

@Controller
public class PageController {
	
	
	@Autowired
	private PageService pageService;
	@Autowired
	private ProjectRepository projectRepository;
	
	   @Autowired
	    private ProjectTaskService projectTaskService;
	   
	   @Autowired
	    private ActivityLogRepository activityLogRepository;

	@GetMapping("/login")
	public String login(HttpSession session) {

	    if (session.getAttribute("user") != null) {
	        return "redirect:/home";
	    }
	    
	    

	    return "login";
	}

    @GetMapping("/home")
    public String home(HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "home";
    }
    
    @GetMapping("/projects")
    public String projectList(Model model,
                              HttpSession session) {

        if(session.getAttribute("user")==null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "projects",
                pageService.getAllProjects());

        return "project-list";
    }
    
    
    @GetMapping("/projectsanstasks")
    public String projectsAndTasksList(
            Model model,
            HttpSession session) {

        if(session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "projectTasks",
                projectTaskService.getProjectTasks());

        return " project-task";
    }    
   

    @GetMapping("/tasks/{projectId}")
    public String taskPage(
            @PathVariable Integer projectId,
            Model model,
            HttpSession session) {

     

        Object user =
                session.getAttribute("user");

        System.out.println("Session user : " + user);

        if(user == null) {

            System.out.println(
                "User session is null. Redirecting to login.");

            return "redirect:/login";
        }

        Project project =
                pageService
                        .getProjectById(
                                projectId);

        System.out.println(
            "Project object : " + project);

        if(project != null) {

            System.out.println(
                "Project ID : "
                + project.getId());

            System.out.println(
                "Project Code : "
                + project.getProjectCode());

            System.out.println(
                "Project Name : "
                + project.getProjectName());

            System.out.println(
                "Project End Date : "
                + project.getEndDate());

        } else {

            System.out.println(
                "Project not found");
        }

        model.addAttribute(
                "projectId",
                projectId);

        model.addAttribute(
                "projectEndDate",
                project.getEndDate());

       

        return "Task-list";
    }
    
    @GetMapping("/activitylogs")
    public String activityLogs(
            Model model,
            HttpSession session) {

        User user =
                (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        if (!"ADMIN".equals(user.getRole())) {
            return "redirect:/home";
        }

        model.addAttribute(
                "activityLogs",
                pageService
                        .getAllActivityLogs());

        return "activity-log";
    }

    
}