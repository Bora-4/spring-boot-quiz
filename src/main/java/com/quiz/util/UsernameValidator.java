package com.quiz.util;

import com.quiz.exceptions.requirements.UsernameRequirementsException;

import java.util.regex.Pattern;

public class UsernameValidator {
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{7,23}$");

    public static void validate(String username){
        if(username == null || !USERNAME_PATTERN.matcher(username).matches()){
            throw new UsernameRequirementsException("Username should be 8-24 characters long, " +
                    "should not start with a special character, " +
                    "and should have no spaces.");
        }
    }
}
