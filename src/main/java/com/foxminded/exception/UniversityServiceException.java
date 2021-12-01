package com.foxminded.exception;

public class UniversityServiceException extends RuntimeException{
    public UniversityServiceException() {
    }

    public UniversityServiceException(String message) {
        super(message);
    }

    public UniversityServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniversityServiceException(Throwable cause) {
        super(cause);
    }

    public UniversityServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
