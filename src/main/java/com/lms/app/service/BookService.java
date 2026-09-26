package com.lms.app.service;

import com.lms.app.model.dto.requests.CreateBookRequest;

public interface BookService {
    void create(CreateBookRequest request);
    void delete(long bookId);
}
