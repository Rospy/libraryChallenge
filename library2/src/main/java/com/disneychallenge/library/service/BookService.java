package com.disneychallenge.library.service;

import com.disneychallenge.library.entity.BookEntity;
import com.disneychallenge.library.enumerations.LoanStatus;
import com.disneychallenge.library.model.BookModel;
import com.disneychallenge.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook(BookModel incomingObject) {
        boolean exists = bookRepository.existsByTitle(incomingObject.getTitle());
        if(exists) {
            return null;
        } else {
            BookEntity entity = new BookEntity();
            entity.setTitle(incomingObject.getTitle());
            entity.setAuthor(incomingObject.getAuthor());
            entity.setPrintYear(incomingObject.getPrintYear());
            entity.setBorrowed(LoanStatus.NOT_BORROWED);
            entity.setAvailableCopies(incomingObject.getNumberOfTotalCopies());
            entity.setTotalCopies(incomingObject.getNumberOfTotalCopies());
            bookRepository.save(entity);
            return new BookModel(entity);
        }
    }

    public String removeBook(Integer bookId) throws Exception {
        BookEntity entity = bookRepository.findById(bookId).orElseThrow();
        if(!nonNull(entity)) {
            throw new Exception();
        } else {
            if(entity.getBorrowed().equalsIgnoreCase(LoanStatus.NOT_BORROWED)) {
                bookRepository.deleteById(bookId);
                return "Book already deleted.";
            }
        }
        return "";
    }

    public BookModel updateBook(BookModel incomingObject) {
        BookEntity entity = bookRepository.findById(incomingObject.getId()).get();
        if(!nonNull(entity)) {
            return null;
        } else {
            if(!incomingObject.getTitle().equalsIgnoreCase(entity.getTitle())) {
                entity.setTitle(incomingObject.getTitle());
            }
            if(!incomingObject.getAuthor().equalsIgnoreCase(entity.getAuthor())) {
                entity.setAuthor(incomingObject.getAuthor());
            }
            if(!incomingObject.getPrintYear().equals(entity.getPrintYear())) {
                entity.setPrintYear(incomingObject.getPrintYear());
            }
            if(!incomingObject.getNumberOfTotalCopies().equals(entity.getTotalCopies())) {
                entity.setTotalCopies(incomingObject.getNumberOfTotalCopies());
                entity.setAvailableCopies(incomingObject.getNumberOfTotalCopies());
            }
            bookRepository.save(entity);
            return new BookModel(entity);
        }
    }

    public List<BookModel> getBooksByProperties(String title, String author, Integer printYear, String isBorrowed,
                                                Integer numberOfAvailableCopies) {
        List<BookEntity> list = bookRepository.findAll();
        if(!nonNull(title) && !nonNull(author) && !nonNull(printYear) && !nonNull(isBorrowed)
                && !nonNull(numberOfAvailableCopies)) {
            return list.stream()
                    .map(b -> new BookModel(b))
                    .collect(Collectors.toList());
        } else {
            return list.stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title)
                            || b.getAuthor().equalsIgnoreCase(author)
                            || b.getPrintYear().equals(printYear)
                            || b.getBorrowed().equalsIgnoreCase(isBorrowed)
                            || b.getAvailableCopies().equals(numberOfAvailableCopies)
                    )
                    .map(b -> new BookModel(b))
                    .collect(Collectors.toList());
        }
    }
}
