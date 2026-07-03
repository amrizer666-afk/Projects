package com.solverminds.projectmanagement.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.solverminds.projectmanagement.coustomexception.LogoutException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.User;
import com.solverminds.projectmanagement.service.ActivityLogService;
import com.solverminds.projectmanagement.service.LogoutService;

@Service
public class LogoutServiceImpl
        implements LogoutService {

    @Autowired
    private ActivityLogService activityLogService;

    @Override
    public void logout(
            HttpSession session,
            HttpServletRequest request) {

        try {

            User user =
                    (User) session.getAttribute(
                            "user");

            if (user != null) {

                activityLogService.saveLog(
                        user.getUsername(),
                        user.getRole(),
                        "LOGOUT",
                        "AUTHENTICATION",
                        user.getUsername(),
                        "User logged out successfully",
                        request.getRemoteAddr());
            }

            session.invalidate();

        } catch (Exception e) {

            try {

                User user =
                        (User) session.getAttribute(
                                "user");

                activityLogService.saveLog(
                        user != null
                                ? user.getUsername()
                                : "UNKNOWN",
                        user != null
                                ? user.getRole()
                                : "SYSTEM",
                        "LOGOUT",
                        "AUTHENTICATION",
                        user != null
                                ? user.getUsername()
                                : "UNKNOWN",
                        "LOGOUT ERROR : "
                                + e.getMessage(),
                        request.getRemoteAddr());

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            throw new LogoutException(
                    "Logout failed : "
                            + e.getMessage(),
                    e);
        }
    }
}