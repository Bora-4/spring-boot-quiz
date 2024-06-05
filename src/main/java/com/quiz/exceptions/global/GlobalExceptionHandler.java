package com.quiz.exceptions.global;

import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.exceptions.requirements.PasswordRequirementsException;
import com.quiz.exceptions.requirements.UsernameRequirementsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameRequirementsException.class)
    public ResponseEntity<String> handleUsernameException(UsernameRequirementsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordRequirementsException.class)
    public ResponseEntity<String> handlePasswordException(PasswordRequirementsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
