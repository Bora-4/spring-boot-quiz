package com.quiz.util;

import com.quiz.exceptions.requirements.PasswordRequirementsException;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

    public static void validate(String password){
        if(password == null || !PASSWORD_PATTERN.matcher(password).matches()){
            throw new PasswordRequirementsException("Password should be at least 8 characters long,"+
                    "contain a capital letter, and a special character.");
        }
    }
}
