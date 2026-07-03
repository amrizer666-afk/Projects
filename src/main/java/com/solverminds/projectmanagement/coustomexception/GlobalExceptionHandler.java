package com.solverminds.projectmanagement.coustomexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(TaskException.class)
	public ResponseEntity<String> handleTaskException(
	        TaskException ex) {

	    ex.printStackTrace();

	    return new ResponseEntity<>(
	            "TASK ERROR : " + ex.getMessage(),
	            HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(LogoutException.class)
	    public ResponseEntity<String> handleLogoutException(
	            LogoutException ex) {

	        ex.printStackTrace();

	        return new ResponseEntity<>(
	                "LOGOUT ERROR : " + ex.getMessage(),
	                HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(ProjectException.class)
	    public ResponseEntity<String> handleProjectException(
	            ProjectException ex) {

	        ex.printStackTrace();

	        return new ResponseEntity<>(
	                "PROJECT ERROR : " + ex.getMessage(),
	                HttpStatus.BAD_REQUEST);
	    }
	

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleLoginException(
            LoginException ex) {

        ex.printStackTrace();

        return new ResponseEntity<>(
                "LOGIN ERROR : " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }
	
	
	 @ExceptionHandler(DashboardException.class)
	    public ResponseEntity<String> handleDashboardException(
	            DashboardException ex) {

	        ex.printStackTrace();

	        return new ResponseEntity<>(
	                "DASHBOARD ERROR : " + ex.getMessage(),
	                HttpStatus.BAD_REQUEST);
	    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(
            Exception ex) {

        ex.printStackTrace();

        return new ResponseEntity<>(
                "ERROR : " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}