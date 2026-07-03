package com.solverminds.projectmanagement.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.solverminds.projectmanagement.entity.User;

public interface LoginService {

    Map<String, String> login(
            User user,
            HttpSession session,
            HttpServletRequest request);
}