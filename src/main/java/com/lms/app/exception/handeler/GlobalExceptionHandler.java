package com.lms.app.exception.handeler;

import com.lms.app.exception.BlobAlreadyAssignedException;
import com.lms.app.exception.BlobNotFoundException;
import com.lms.app.exception.BookNotFoundException;
import com.lms.app.model.dto.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(exception = BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex, HttpServletRequest request){
        var errorResponse = new ErrorResponse();
        errorResponse.setDetails(ex.getDetails());
        errorResponse.setCode(ex.getCode());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
    @ExceptionHandler(exception = BlobNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBlobNotFound(BlobNotFoundException ex, HttpServletRequest request){
        var errorResponse = new ErrorResponse();
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetails(ex.getDetails());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
    @ExceptionHandler(exception = BlobAlreadyAssignedException.class)
    public ResponseEntity<ErrorResponse> blobAlreadyAssigned(BlobAlreadyAssignedException ex, HttpServletRequest request){
        var errorResponse = new ErrorResponse();
        errorResponse.setCode(ex.getCode());
        errorResponse.setDetails(ex.getDetails());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
