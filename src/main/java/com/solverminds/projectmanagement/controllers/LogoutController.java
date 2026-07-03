package com.solverminds.projectmanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.solverminds.projectmanagement.service.LogoutService;

@Controller
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @GetMapping("/logout")
    public String logout(
            HttpSession session,
            HttpServletRequest request) {

        logoutService.logout(
                session,
                request);

        return "redirect:/login";
    }
}