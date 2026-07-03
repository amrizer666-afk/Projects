package com.solverminds.projectmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.solverminds.projectmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);
}