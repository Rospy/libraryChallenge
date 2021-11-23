package com.disneychallenge.library.service;

import com.disneychallenge.library.entity.BookEntity;
import com.disneychallenge.library.entity.LoanEntity;
import com.disneychallenge.library.enumerations.LoanStatus;
import com.disneychallenge.library.model.LoanModel;
import com.disneychallenge.library.repository.BookRepository;
import com.disneychallenge.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public LoanModel registerLoan(LoanModel incomingObject) {
        LoanEntity entity = loanRepository.findByEmail(incomingObject.getEmail());
        if(!nonNull(entity)) {
            LoanEntity newEntity = new LoanEntity();
            newEntity.setFirstName(incomingObject.getFirstName());
            newEntity.setLastName(incomingObject.getLastName());
            newEntity.setEmail(incomingObject.getEmail());
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(2);
            newEntity.setStartDate(startDate);
            newEntity.setEndDate(endDate);
            newEntity.setLoanActive(LoanStatus.BORROWED);
            if(checkIfBooksAreValid(incomingObject.getBookId(), newEntity)) {
                return new LoanModel(newEntity);
            }
        } else {
            if(entity.getLoanActive().equalsIgnoreCase(LoanStatus.BORROWED)) {
                return null;
            }
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(2);
            entity.setStartDate(startDate);
            entity.setEndDate(endDate);
            entity.setLoanActive(LoanStatus.BORROWED);
            if(checkIfBooksAreValid(incomingObject.getBookId(), entity)) {
                return new LoanModel(entity);
            }
        }
        return null;
    }

    private boolean checkIfBooksAreValid(int[] ids, LoanEntity entity) {
        boolean var = false;
        for (int id : ids) {
            BookEntity book = bookRepository.findById(id).get();
            String books;
            String replaced;
            if (book.getAvailableCopies() > 0) {
                int updatedCopies = book.getAvailableCopies() - 1;
                book.setAvailableCopies(updatedCopies);
                book.setBorrowed(LoanStatus.BORROWED);
                bookRepository.save(book);
                if(nonNull(entity.getBookId())) {
                    if(entity.getBookId().equalsIgnoreCase("[0]")) {
                        replaced = "[" + id + "]";
                        entity.setBookId(replaced);
                    } else {
                        replaced = entity.getBookId().replaceAll("\\[", "").replaceAll("]", "");
                        books = "[" + replaced.trim() + "," + id + "]";
                        entity.setBookId(books);
                    }
                } else {
                    books = "[" + id + "]";
                    entity.setBookId(books);
                }
                loanRepository.save(entity);
                var = true;
            }
        }
        return var;
    }

    public LoanModel updateLoan(LoanModel incomingObject) {
        LoanEntity entity = loanRepository.findByEmail(incomingObject.getEmail());
        if(!nonNull(entity)) {
            return null;
        } else {
            if(!incomingObject.getFirstName().equalsIgnoreCase(entity.getFirstName())) {
                entity.setFirstName(incomingObject.getFirstName());
            }
            if(!incomingObject.getLastName().equalsIgnoreCase(entity.getLastName())) {
                entity.setLastName(incomingObject.getLastName());
            }
            if(!incomingObject.getEmail().equalsIgnoreCase(entity.getEmail())) {
                entity.setEmail(incomingObject.getEmail());
            }
            if(!incomingObject.getEndDate().isEqual(entity.getEndDate())) {
                entity.setEndDate(incomingObject.getEndDate());
            }
            if(updateCopiesAvailable(entity.getBookId())) {
                entity.setLoanActive(LoanStatus.NOT_BORROWED);
                entity.setBookId(Arrays.toString(incomingObject.getBookId()));
                loanRepository.save(entity);
                return new LoanModel(entity);
            }
        }
        return null;
    }

    private boolean updateCopiesAvailable(String ids) {
        String[] booksIds = ids.replaceAll("\\[", "").replaceAll("]","").split(",");
        int[] bookId = new int[booksIds.length];
        for(int i = 0; i < booksIds.length; i++) {
            try {
                bookId[i] = Integer.parseInt(booksIds[i].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        for (int i : bookId) {
            BookEntity bookEntity = bookRepository.findById(i).get();
            int updatedCopies = bookEntity.getAvailableCopies() + 1;
            if (updatedCopies == bookEntity.getTotalCopies()) {
                bookEntity.setBorrowed(LoanStatus.NOT_BORROWED);
            }
            bookEntity.setAvailableCopies(updatedCopies);
            bookRepository.save(bookEntity);
        }
        return true;
    }
}
