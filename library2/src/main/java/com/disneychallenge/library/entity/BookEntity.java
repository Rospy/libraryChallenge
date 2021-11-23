package com.disneychallenge.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String author;
    private Integer printYear;
    private String borrowed;
    private Integer availableCopies;
    private Integer totalCopies;

    public BookEntity() {}

    public BookEntity(Integer id, String title, String author, Integer printYear, String borrowed,
                      Integer availableCopies, Integer totalCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.printYear = printYear;
        this.borrowed = borrowed;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
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

    public String getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }
}
