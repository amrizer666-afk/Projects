package com.solverminds.projectmanagement.service.impl;

import java.util.HashMap;
import com.solverminds.projectmanagement.coustomexception.LoginException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.repository.UserRepository;
import com.solverminds.projectmanagement.service.ActivityLogService;
import com.solverminds.projectmanagement.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityLogService activityLogService;

    @Override
    public Map<String, String> login(
            User user,
            HttpSession session,
            HttpServletRequest request) {

        Map<String, String> response =
                new HashMap<>();

        try {

            User dbUser =
                    userRepository
                            .findByUsernameAndPassword(
                                    user.getUsername(),
                                    user.getPassword());

            if (dbUser != null) {

                session.setAttribute(
                        "user",
                        dbUser);

                session.setAttribute(
                        "username",
                        dbUser.getUsername());

                session.setAttribute(
                        "role",
                        dbUser.getRole());

                activityLogService.saveLog(
                        dbUser.getUsername(),
                        dbUser.getRole(),
                        "LOGIN",
                        "AUTHENTICATION",
                        dbUser.getUsername(),
                        "User logged in successfully",
                        request.getRemoteAddr());

                response.put(
                        "status",
                        "success");

            } else {

                activityLogService.saveLog(
                        user.getUsername(),
                        "UNKNOWN",
                        "LOGIN",
                        "AUTHENTICATION",
                        user.getUsername(),
                        "Invalid username or password",
                        request.getRemoteAddr());

                response.put(
                        "status",
                        "fail");
            }

        } catch (Exception e) {

            try {

                activityLogService.saveLog(
                        user.getUsername(),
                        "UNKNOWN",
                        "LOGIN",
                        "AUTHENTICATION",
                        user.getUsername(),
                        "ERROR : " + e.getMessage(),
                        request.getRemoteAddr());

            } catch (Exception logException) {

                logException.printStackTrace();
            }

            throw new LoginException(
                    "Login failed : "
                            + e.getMessage(),
                    e);
        }

        return response;
    }
}