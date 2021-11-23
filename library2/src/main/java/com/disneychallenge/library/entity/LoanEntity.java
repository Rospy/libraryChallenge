package com.disneychallenge.library.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private String loanActive;
    private String bookId;

    public LoanEntity() {}

    public LoanEntity(Integer id, String firstName, String lastName, String email, LocalDate startDate, LocalDate endDate, String loanActive, String bookId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanActive = loanActive;
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLoanActive() {
        return loanActive;
    }

    public void setLoanActive(String loanActive) {
        this.loanActive = loanActive;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
