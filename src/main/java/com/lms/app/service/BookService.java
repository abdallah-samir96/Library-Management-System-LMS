package com.lms.app.service;

import com.lms.app.model.dto.commons.SortDirection;
import com.lms.app.model.dto.requests.CreateBookRequest;
import com.lms.app.model.dto.requests.ListBookResponse;
import com.lms.app.model.dto.requests.UpdateBookRequest;
import com.lms.app.model.dto.responses.LMSResponse;

import java.util.List;

public interface BookService {
    void create(CreateBookRequest request);
    void delete(long bookId);
    LMSResponse<List<ListBookResponse>> getAll(int page, int size, String search, SortDirection direction);
    ListBookResponse getBookDetails(long id);
    void update(UpdateBookRequest request);
}
