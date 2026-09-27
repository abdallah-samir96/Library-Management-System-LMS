package com.lms.app.exception;

import com.lms.app.model.dto.commons.ErrorCodes;

public class BookNotFoundException extends RuntimeException {

    private final String details;
    private final String code;
    public BookNotFoundException(String message, String details) {
        super(message);
        this.details = details;
        this.code = ErrorCodes.BOOK_NOT_FOUND;
    }

    public String getDetails() {
        return details;
    }

    public String getCode() {
        return code;
    }
}