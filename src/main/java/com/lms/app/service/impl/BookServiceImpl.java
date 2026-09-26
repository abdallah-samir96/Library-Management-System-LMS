package com.lms.app.service.impl;

import com.lms.app.exception.BlobAlreadyAssignedException;
import com.lms.app.exception.ResourceNotFoundException;
import com.lms.app.model.dto.requests.CreateBookRequest;
import com.lms.app.model.entities.Blob;
import com.lms.app.model.entities.Book;
import com.lms.app.repository.BlobRepository;
import com.lms.app.repository.BookRepository;
import com.lms.app.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BlobRepository blobRepository;

    public BookServiceImpl(BookRepository bookRepository, BlobRepository blobRepository) {
        this.bookRepository = bookRepository;
        this.blobRepository = blobRepository;
    }
    /**
     * This method should created book
     * the blob should be existed & should not be assigned to existing book
     * */
    @Override
    public void create(CreateBookRequest request) {
        Blob blob = blobRepository.findById(request.blobId()).orElseThrow(() -> new ResourceNotFoundException("Blob not found: " + request.blobId()));
        if (bookRepository.existsByBlobId(request.blobId())) {
            throw new BlobAlreadyAssignedException("Blob is already assigned to another book: " + request.blobId());
        }
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setVersion(request.version() != null ? request.version() : 1);
        book.setDescription(request.description());
        book.setCategory(request.category());
        book.setBlob(blob);
        bookRepository.save(book);
    }
}
