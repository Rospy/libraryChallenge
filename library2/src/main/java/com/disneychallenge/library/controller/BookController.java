package com.disneychallenge.library.controller;

import com.disneychallenge.library.model.BookModel;
import com.disneychallenge.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(path = "apiv1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel incomingObject) {
        BookModel model = bookService.addBook(incomingObject);
        if(nonNull(model)) {
            return new ResponseEntity<>(model, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{bookId}")
    public ResponseEntity<String> removeBook(@PathVariable("bookId") Integer bookId) {
        try {
            String message = bookService.removeBook(bookId);
            if(!message.isEmpty()) {
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message = "Book borrowed, cannot delete it.";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<BookModel> updateBook(@RequestBody BookModel incomingObject) {
        try {
            BookModel model = bookService.updateBook(incomingObject);
            if (nonNull(model)) {
                return new ResponseEntity<>(model, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getBooksByProperties(
                                    @RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "author", required = false) String author,
                                    @RequestParam(value = "printYear", required = false) Integer printYear,
                                    @RequestParam(value = "isBorrowed", required = false) String isBorrowed,
                                    @RequestParam(value = "numberOfAvailableCopies", required = false) Integer numberOfAvailableCopies) {
        List<BookModel> model = bookService.getBooksByProperties(title, author, printYear, isBorrowed, numberOfAvailableCopies);
        if(model.size() > 0) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
