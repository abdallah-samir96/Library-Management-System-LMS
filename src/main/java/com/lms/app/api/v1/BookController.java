package com.lms.app.api.v1;

import com.lms.app.model.constants.AppConstants;
import com.lms.app.model.dto.commons.SortDirection;
import com.lms.app.model.dto.requests.CreateBookRequest;
import com.lms.app.model.dto.requests.ListBookResponse;
import com.lms.app.model.dto.responses.LMSResponse;
import com.lms.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * search parameter search book with (title, author, ISBN, description, category)
     * */
    @GetMapping
    public ResponseEntity<LMSResponse<List<ListBookResponse>>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        var response = bookService.getAll(page, size, search, SortDirection.getDirection(direction));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
