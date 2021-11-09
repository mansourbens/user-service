package com.mansourbens.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
