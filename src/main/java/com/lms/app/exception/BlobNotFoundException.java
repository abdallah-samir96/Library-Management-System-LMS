package com.lms.app.exception;

import com.lms.app.model.dto.commons.ErrorCodes;

public class BlobNotFoundException extends RuntimeException {

    private final String details;
    private final String code;
    public BlobNotFoundException(String message, String details) {
        super(message);
        this.details = details;
        this.code = ErrorCodes.BLOB_NOT_FOUND;
    }

    public String getDetails() {
        return details;
    }

    public String getCode() {
        return code;
    }
}