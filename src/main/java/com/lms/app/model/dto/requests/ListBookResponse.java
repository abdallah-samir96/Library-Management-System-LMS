package com.lms.app.model.dto.requests;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBookResponse extends BaseEntityResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer version;
    private String description;
    private String category;
    private String filePath;
    private String thumbnailPath;
    private Long blobId;

    public ListBookResponse() {
    }

    public ListBookResponse(Long id, String title, String author, String isbn, Integer version, String description, String category, String filePath, String thumbnailPath) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.version = version;
        this.description = description;
        this.category = category;
        this.filePath = filePath;
        this.thumbnailPath = thumbnailPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public Long getBlobId() {
        return blobId;
    }

    public void setBlobId(Long blobId) {
        this.blobId = blobId;
    }
}