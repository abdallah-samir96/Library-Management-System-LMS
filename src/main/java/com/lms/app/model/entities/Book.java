package com.lms.app.model.entities;

import com.lms.app.model.dto.commons.BookCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 1000)
    private String title;

    @Column(name = "author", length = 500)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true, length = 255)
    private String isbn;

    @Column(name = "version", columnDefinition = "integer default 1")
    private Integer version = 1;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "category", length = 255)
    @Enumerated(EnumType.STRING)
    private BookCategory category;



    @OneToOne
    @JoinColumn(name = "blob_id", referencedColumnName = "id")
    private Blob blob;

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

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }
}
