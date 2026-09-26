package com.lms.app.model.dto.requests;

import com.lms.app.model.dto.commons.BookCategory;

public record CreateBookRequest(
        String title,
        String author,
        String isbn,
        Integer version,
        String description,
        BookCategory category,
        Long blobId
) {
}