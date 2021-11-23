package com.disneychallenge.library;

import com.disneychallenge.library.entity.BookEntity;
import com.disneychallenge.library.model.BookModel;
import com.disneychallenge.library.repository.BookRepository;
import com.disneychallenge.library.service.BookService;
import org.aspectj.lang.annotation.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@Ignore
public class BookServiceTests {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @Before("")
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private static final int id = 1;
    private static final String title = "Nada";
    private static final String author = "Jane Teller";
    private static final String author2 = "Isabel Allende";
    private static final int printYear = 2015;
    private static final String isBorrowed = "Yes";
    private static final int numberOfAvailableCopies = 3;
    private static final int numberOfTotalCopies = 5;

    @Test
    public void checkForValidResultInGetAllBooksTest() {
        when(bookService.getBooksByProperties(title,author, printYear, isBorrowed, numberOfAvailableCopies))
                .thenReturn(bookModelList());
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity()));

        List<BookModel> model = bookService.getBooksByProperties(title, author, printYear, isBorrowed, numberOfAvailableCopies);

        assertNotNull(model, "Return should not be null");
        assertThat(model.size()).isGreaterThanOrEqualTo(1);
        assertEquals(model.get(0).getTitle(), title);
        assertEquals(model.get(0).getAuthor(), author);
        assertEquals(model.get(0).getPrintYear(), printYear);
        assertEquals(model.get(0).getIsBorrowed(), isBorrowed);
        assertEquals(model.get(0).getNumberOfAvailableCopies(), numberOfAvailableCopies);
    }

    @Test
    public void checkForValidResultInAddBook() {
        BookEntity bookEntity = new BookEntity(id, title, author, printYear, isBorrowed, numberOfAvailableCopies, numberOfTotalCopies);

        when(bookRepository.existsByTitle(title)).thenReturn(false);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        BookModel model = bookService.addBook(new BookModel(bookEntity));

        assertNotNull(model, "Return should not be null");
    }

    @Test
    public void checkForNotValidResultInAddBook() {
        BookEntity bookEntity = new BookEntity(id, title, author, printYear, isBorrowed, numberOfAvailableCopies, numberOfTotalCopies);

        when(bookRepository.existsByTitle(title)).thenReturn(true);

        BookModel model = bookService.addBook(new BookModel(bookEntity));

        assertThat(model).isNull();
    }

    @Test
    public void CheckForValidResultInUpdateBook() {
        BookEntity bookEntity = new BookEntity(id, title, author, printYear, isBorrowed, numberOfAvailableCopies, numberOfTotalCopies);
        BookEntity newBookEntity = new BookEntity(id, title, author2, printYear, isBorrowed, numberOfAvailableCopies, numberOfTotalCopies);

        when(bookRepository.findById(id)).thenReturn(Optional.of(bookEntity));

        BookModel model = bookService.updateBook(new BookModel(newBookEntity));

        assertNotNull(model, "Return should not be null");
        assertThat(model.getAuthor()).isNotEqualTo(author);
    }

    private BookEntity bookEntity() {
        BookEntity entity = new BookEntity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setPrintYear(printYear);
        entity.setBorrowed(isBorrowed);
        entity.setAvailableCopies(numberOfAvailableCopies);
        entity.setTotalCopies(numberOfTotalCopies);
        return entity;
    }

    private List<BookModel> bookModelList() {
        return List.of(new BookModel(bookEntity()));
    }

}
