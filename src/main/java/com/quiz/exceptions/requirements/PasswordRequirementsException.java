package com.quiz.exceptions.requirements;

public class PasswordRequirementsException extends RuntimeException{
    public PasswordRequirementsException(String message){
        super(message);
    }
}
