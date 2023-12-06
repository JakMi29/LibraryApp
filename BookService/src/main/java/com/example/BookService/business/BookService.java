package com.example.BookService.business;

import com.example.BookService.business.dao.BookDAO;
import com.example.BookService.domain.exception.BookServiceCustomException;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.domain.request.BorrowReturnBookRequest;
import com.example.BookService.infrastructure.database.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class BookService {
    private final BookDAO bookDAO;

    public void returnBook(BorrowReturnBookRequest request) {
        BookEntity book
                = bookDAO.findByNameAndPublicationDate(request.getName(),request.getPublicationYear())
                .orElseThrow(() -> new BookServiceCustomException(
                        "Book with given Name not found",
                        "PRODUCT_NOT_FOUND"
                ));



        book.setQuantity(book.getQuantity() + 1);
        bookDAO.save(book);
        log.info("Book Quantity updated Successfully");
    }
    public void borrowBook(BorrowReturnBookRequest request) {
        BookEntity book
                = bookDAO.findByNameAndPublicationDate(request.getName(),request.getPublicationYear())
                .orElseThrow(() -> new BookServiceCustomException(
                        "Book with given Name not found",
                        "PRODUCT_NOT_FOUND"
                ));
        if (book.getQuantity() <= 0) {
            throw new BookServiceCustomException(
                    "Book does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        book.setQuantity(book.getQuantity() - 1);
        bookDAO.save(book);
        log.info("Book Quantity updated Successfully");
    }

    public void addBook(AddBookRequest request) {
        Optional<BookEntity> optOfBook =
                bookDAO.findByNameAndPublicationDate(request.getName(), request.getPublicationYear());

        BookEntity book = optOfBook.map(s -> {
            log.info("Book Quantity updated Successfully");
            s.setQuantity(s.getQuantity() + request.getQuantity());
            return s;
        }).orElseGet(() -> {
            log.info("The new book has been successfully added");
            return BookEntity.builder()
                    .name(request.getName())
                    .author(request.getAuthor())
                    .quantity(request.getQuantity())
                    .publicationDate(request.getPublicationYear())
                    .build();
        });

        bookDAO.save(book);
    }
}
