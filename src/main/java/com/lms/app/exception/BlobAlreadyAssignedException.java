package com.lms.app.exception;

public class BlobAlreadyAssignedException extends RuntimeException {

    public BlobAlreadyAssignedException(String message) {
        super(message);
    }
}