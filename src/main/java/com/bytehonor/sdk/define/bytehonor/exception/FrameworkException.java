package com.bytehonor.sdk.define.bytehonor.exception;

public class FrameworkException extends RuntimeException {

    private static final long serialVersionUID = -1480130710794334949L;

    public FrameworkException() {
        super();
    }

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
