package com.solverminds.projectmanagement.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solverminds.projectmanagement.entity.ActivityLog;
import com.solverminds.projectmanagement.repository.ActivityLogRepository;

@Service
public class ActivityLogService {

    @Autowired
    private ActivityLogRepository repository;

    public void saveLog(
            String username,
            String role,
            String action,
            String module,
            String referenceId,
            String description,
            String ipAddress) {

        ActivityLog log =
                new ActivityLog();

        log.setUsername(username);
        log.setRole(role);
        log.setActionType(action);
        log.setModuleName(module);
        log.setReferenceId(referenceId);
        log.setDescription(description);
        log.setIpAddress(ipAddress);
        log.setCreatedAt(
                LocalDateTime.now());

        repository.save(log);
    }
}
