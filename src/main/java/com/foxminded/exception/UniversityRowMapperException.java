package com.foxminded.exception;

public class UniversityRowMapperException extends RuntimeException{
    public UniversityRowMapperException() {
    }

    public UniversityRowMapperException(String message) {
        super(message);
    }

    public UniversityRowMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniversityRowMapperException(Throwable cause) {
        super(cause);
    }

    public UniversityRowMapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
