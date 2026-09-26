package com.lms.app.repository;

import com.lms.app.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByBlobId(Long blobId);
    Optional<Book> findByIdAndDeletedAtIsNull(Long bookId);
}
