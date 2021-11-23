package com.disneychallenge.library.model;

import com.disneychallenge.library.entity.BookEntity;
import org.springframework.lang.NonNull;

public class BookModel {

    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private Integer printYear;
    private String isBorrowed;
    private Integer numberOfAvailableCopies;
    @NonNull
    private Integer numberOfTotalCopies;

    public BookModel() {}

    public BookModel(BookEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.printYear = entity.getPrintYear();
        this.isBorrowed = entity.getBorrowed();
        this.numberOfAvailableCopies = entity.getAvailableCopies();
        this.numberOfTotalCopies = entity.getTotalCopies();
    }

    public BookModel(String title, String author, int printYear, int numberOfTotalCopies) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPrintYear() {
        return printYear;
    }

    public void setPrintYear(Integer printYear) {
        this.printYear = printYear;
    }

    public String getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(String isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public Integer getNumberOfAvailableCopies() {
        return numberOfAvailableCopies;
    }

    public void setNumberOfAvailableCopies(Integer numberOfAvailableCopies) {
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    public Integer getNumberOfTotalCopies() {
        return numberOfTotalCopies;
    }

    public void setNumberOfTotalCopies(Integer numberOfTotalCopies) {
        this.numberOfTotalCopies = numberOfTotalCopies;
    }
}
