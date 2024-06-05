package com.quiz.exceptions.duplicates;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message){
        super(message);
    }

    public DuplicateEntityException(String type, String attribute, String value){
        super(String.format("%s with %s %s already exists.", type, attribute, value));
    }
}
