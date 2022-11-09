package com.example.bookshop.entities;

import com.example.bookshop.enums.ageRestriction;
import com.example.bookshop.enums.editionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "edition_type", nullable = false)
    private editionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "age_restriction", nullable = false)
    private ageRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public Book(String title, editionType editionType, BigDecimal price, int copies,
                LocalDate releaseDate, ageRestriction ageRestriction, Author author,
                Set<Category> categories) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public com.example.bookshop.enums.editionType getEditionType() {
        return editionType;
    }

    public void setEditionType(com.example.bookshop.enums.editionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public com.example.bookshop.enums.ageRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(com.example.bookshop.enums.ageRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
