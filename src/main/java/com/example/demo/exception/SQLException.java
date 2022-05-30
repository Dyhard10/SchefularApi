package com.example.demo.exception;

public class SQLException extends Exception{
    private static final long serialVersionUID = 1L;
    private final int errorCode=407;
    private final String errorMessage;

    public SQLException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}