package com.lms.app.api.v1;

import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.requests.CreateBookRequest;
import com.lms.app.service.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // This API should be called through the ADMIN Only
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
