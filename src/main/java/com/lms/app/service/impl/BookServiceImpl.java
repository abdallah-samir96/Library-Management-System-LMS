package com.lms.app.service.impl;

import com.lms.app.exception.BlobAlreadyAssignedException;
import com.lms.app.exception.ResourceNotFoundException;
import com.lms.app.model.dto.commons.SortDirection;
import com.lms.app.model.dto.requests.CreateBookRequest;
import com.lms.app.model.dto.requests.ListBookResponse;
import com.lms.app.model.dto.responses.LMSResponse;
import com.lms.app.model.entities.Blob;
import com.lms.app.model.entities.Book;
import com.lms.app.model.mapeprs.BookMapper;
import com.lms.app.repository.BlobRepository;
import com.lms.app.repository.BookRepository;
import com.lms.app.repository.specifications.BookSpecification;
import com.lms.app.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional
    @Override
    public void delete(long bookId) {
        var book = bookRepository
                .findByIdAndDeletedAtIsNull(bookId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book does not exist"));

        var now = LocalDateTime.now();
        book.setDeletedAt(now);
        book.getBlob().setDeletedAt(now);
    }

    @Override
    public LMSResponse<List<ListBookResponse>> getAll(int page, int size, String search, SortDirection direction) {
        var pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction.direction), "createdAt");
        var specificationCriteria = BookSpecification.notDeleted().and(BookSpecification.search(search));
        Page<Book> pageableResponse = bookRepository.findAll(specificationCriteria, pageable);
        var mapper = new BookMapper();
        var data = mapper.toDTOs(pageableResponse.getContent());
        return new  LMSResponse<List<ListBookResponse>>()
                .setData(data)
                .setPage(page)
                .setPageSize(size)
                .setTotalCounts(pageableResponse.getTotalElements())
                .build();
    }
}
