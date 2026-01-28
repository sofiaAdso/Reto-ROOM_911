package com.room911.utils.exception;

public class ResourceNoFoundException extends RuntimeException {

    public ResourceNoFoundException(String message) {
        super(message);
    }

    public ResourceNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
