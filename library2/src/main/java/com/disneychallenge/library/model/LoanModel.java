package com.disneychallenge.library.model;

import com.disneychallenge.library.entity.LoanEntity;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class LoanModel {

    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private String isLoanActive;
    @NonNull
    private int[] bookId;

    public LoanModel() {}

    public LoanModel(LoanEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.isLoanActive = entity.getLoanActive();
        this.bookId = getStringValues(entity.getBookId());
    }

    private int[] getStringValues(String books) {
        String[] newBooks = books.replaceAll("\\[", "").replaceAll("]","").split(",");
        bookId = new int[newBooks.length];
        for(int i = 0; i < newBooks.length; i++) {
            try {
                bookId[i] = Integer.parseInt(newBooks[i].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return bookId;
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

    public String getIsLoanActive() {
        return isLoanActive;
    }

    public void setIsLoanActive(String isLoanActive) {
        this.isLoanActive = isLoanActive;
    }

    public int[] getBookId() {
        return bookId;
    }

    public void setBookId(int[] bookId) {
        this.bookId = bookId;
    }
}
