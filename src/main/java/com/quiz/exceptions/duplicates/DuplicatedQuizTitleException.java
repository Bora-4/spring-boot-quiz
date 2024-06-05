package com.quiz.exceptions.duplicates;

public class DuplicatedQuizTitleException extends DuplicateEntityException{
    public DuplicatedQuizTitleException(String message) {
        super(message);
    }

    public DuplicatedQuizTitleException(String type, String attribute, String value) {
        super(type, attribute, value);
    }
}
