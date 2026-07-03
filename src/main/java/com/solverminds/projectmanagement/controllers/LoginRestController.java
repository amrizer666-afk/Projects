package com.solverminds.projectmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.repository.UserRepository;
import com.solverminds.projectmanagement.service.ActivityLogService;
import com.solverminds.projectmanagement.service.LoginService;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody User user,
            HttpSession session,
            HttpServletRequest request) {

        return loginService.login(
                user,
                session,
                request);
    }
}
