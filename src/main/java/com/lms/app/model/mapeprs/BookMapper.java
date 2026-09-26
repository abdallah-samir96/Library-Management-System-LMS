package com.lms.app.model.mapeprs;

import com.lms.app.model.dto.requests.ListBookResponse;
import com.lms.app.model.entities.Book;

public class BookMapper implements Mapper<Book, ListBookResponse>{
    @Override
    public Book toEntity(ListBookResponse dto) {
        return null;
    }

    @Override
    public ListBookResponse toDTO(Book entity) {
        var bookResponse = new ListBookResponse();
        bookResponse.setId(entity.getId());
        bookResponse.setTitle(entity.getTitle());
        bookResponse.setAuthor(entity.getAuthor());
        bookResponse.setDescription(entity.getDescription());
        bookResponse.setIsbn(entity.getIsbn());
        bookResponse.setVersion(entity.getVersion());
        bookResponse.setCategory(entity.getCategory().name());
        bookResponse.setFilePath(entity.getBlob().getPath());
        bookResponse.setThumbnailPath(entity.getBlob().getThumbnailPath());
        BaseMapper.mapToDTO(entity, bookResponse);
        return bookResponse;
    }
}
