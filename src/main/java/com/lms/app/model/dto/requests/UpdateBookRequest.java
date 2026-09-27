package com.lms.app.model.dto.requests;

import com.lms.app.model.dto.commons.BookCategory;

public record UpdateBookRequest(
        long id,
        String title,
        String author,
        String isbn,
        Integer version,
        String description,
        BookCategory category
) {
}