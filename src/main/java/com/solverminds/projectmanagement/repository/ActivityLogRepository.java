package com.solverminds.projectmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.solverminds.projectmanagement.entity.ActivityLog;

public interface ActivityLogRepository
        extends JpaRepository<ActivityLog, Long> {

}