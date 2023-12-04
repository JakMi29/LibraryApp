package com.example.BookService.business;

import com.example.BookService.business.dao.BookDAO;
import com.example.BookService.domain.exception.BookServiceCustomException;
import com.example.BookService.infrastructure.database.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
@Log4j2
@AllArgsConstructor
public class BookService {
    private final BookDAO bookDAO;

    public void reduceQuantity(Integer bookId, Integer quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,bookId);

        BookEntity book
                = bookDAO.findById(bookId)
                .orElseThrow(() -> new BookServiceCustomException(
                        "Book with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if(book.getQuantity() < quantity) {
            throw new BookServiceCustomException(
                    "Book does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        book.setQuantity(book.getQuantity() - quantity);
        bookDAO.save(book);
        log.info("Book Quantity updated Successfully");
    }

}
