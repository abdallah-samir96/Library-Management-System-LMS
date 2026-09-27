package com.lms.app.model.dto.responses;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String code;
    private String message;
    private String details;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(
            String code,
            String message,
            String details,
            LocalDateTime timestamp,
            String path) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}