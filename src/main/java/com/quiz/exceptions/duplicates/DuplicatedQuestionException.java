package com.quiz.exceptions.duplicates;

public class DuplicatedQuestionException extends DuplicateEntityException{
    public DuplicatedQuestionException(String message) {
        super(message);
    }

    public DuplicatedQuestionException(String type, String attribute, String value) {
        super(type, attribute, value);
    }
}
