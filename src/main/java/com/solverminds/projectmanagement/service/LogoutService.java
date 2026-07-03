package com.solverminds.projectmanagement.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface LogoutService {

    void logout(
            HttpSession session,
            HttpServletRequest request);
}