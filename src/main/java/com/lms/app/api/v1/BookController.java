package com.lms.app.api.v1;

import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.requests.CreateBookRequest;
import com.lms.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppConstants.BOOK_API_V1_PATH)
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateBookRequest request) {
        bookService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
