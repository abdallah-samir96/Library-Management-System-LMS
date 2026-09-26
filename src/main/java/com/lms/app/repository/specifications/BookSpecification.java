package com.lms.app.repository.specifications;

import com.lms.app.model.entities.Book;
import org.springframework.data.jpa.domain.Specification;

public final class BookSpecification {
    private BookSpecification() {}

    public static Specification<Book> search(String search) {
        var searchValue = "%" + search.trim().toLowerCase() + "%";
        return (root, query, cb) -> {
            if (search.isBlank()) {return cb.conjunction();}
            return cb.or(
                    cb.like(cb.lower(root.get("title")), searchValue),
                    cb.like(cb.lower(root.get("author")), searchValue),
                    cb.like(cb.lower(root.get("isbn")), searchValue),
                    cb.like(cb.lower(root.get("description")), searchValue),
                    cb.like(cb.lower(root.get("category")), searchValue)
        );
        };

    }

    public static Specification<Book> notDeleted() {
        return (root, query, cb) ->
                cb.isNull(root.get("deletedAt"));
    }
}
