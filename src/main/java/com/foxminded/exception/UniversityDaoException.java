package com.foxminded.exception;

public class UniversityDaoException extends RuntimeException{

    public UniversityDaoException(){

    }

    public UniversityDaoException(String message) {
        super(message);
    }

    public UniversityDaoException(Throwable cause) {
        super(cause);
    }

    public UniversityDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniversityDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
