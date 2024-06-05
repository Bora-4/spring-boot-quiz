package com.quiz.exceptions.duplicates;

public class DuplicateUsernameException extends DuplicateEntityException{

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(String type, String attribute, String value) {
        super(type, attribute, value);
    }
}
