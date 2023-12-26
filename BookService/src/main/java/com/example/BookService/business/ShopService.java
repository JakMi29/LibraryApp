package com.example.BookService.business;

import com.example.BookService.business.dao.BookDAO;
import com.example.BookService.domain.exception.BookServiceCustomException;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class ShopService {
    private final BookDAO bookDAO;
    private final BookPaginationService bookPaginationService;

    public void returnBook(Integer id) {
        LibraryBookEntity book
                = bookDAO.findById(id)
                .orElseThrow(() -> new BookServiceCustomException(
                        "Book with given id not found",
                        "PRODUCT_NOT_FOUND"
                ));



        book.setQuantity(book.getQuantity() + 1);
        bookDAO.save(book);
        log.info("Book Quantity updated Successfully");
    }
    public Integer lendBook(Integer id) {
        LibraryBookEntity book
                = bookDAO.findById(id)
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
        return book.getId();
    }

    public void addBook(AddBookRequest request) {
        Optional<LibraryBookEntity> optOfBook =
                bookDAO.findByNameAndPublicationDate(request.getName(), request.getPublicationYear());

        LibraryBookEntity book = optOfBook.map(s -> {
            log.info("Book Quantity updated Successfully");
            s.setQuantity(s.getQuantity() + request.getQuantity());
            return s;
        }).orElseGet(() -> {
            log.info("The new book has been successfully added");
            return LibraryBookEntity.builder()
                    .name(request.getName())
                    .author(request.getAuthor())
                    .quantity(request.getQuantity())
                    .publicationDate(request.getPublicationYear())
                    .build();
        });

        bookDAO.save(book);
    }

    public List<LibraryBookEntity> findAll(Integer pageNumber, Integer pageSize) {
        return bookPaginationService.paginateAll(pageNumber,pageSize);
    }
}
