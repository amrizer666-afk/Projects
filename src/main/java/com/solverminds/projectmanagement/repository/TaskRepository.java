
package com.solverminds.projectmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solverminds.projectmanagement.entity.Task;

public interface TaskRepository
        extends JpaRepository<Task,Integer>{

    List<Task> findByProjectId(
            Integer projectId);
    
    Long countByProjectId(
            Integer projectId);
}