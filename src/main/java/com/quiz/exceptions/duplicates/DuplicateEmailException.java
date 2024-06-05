package com.quiz.exceptions.duplicates;

public class DuplicateEmailException extends DuplicateEntityException{
    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String type, String attribute, String value) {
        super(type, attribute, value);
    }
}
