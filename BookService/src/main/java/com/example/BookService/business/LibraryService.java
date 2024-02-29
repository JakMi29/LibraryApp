package com.example.BookService.business;

import com.example.BookService.business.dao.LibraryBookDAO;
import com.example.BookService.domain.exception.BookServiceCustomException;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.external.client.AvailabilityNotifyService;
import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class LibraryService {
    private final LibraryBookDAO bookDAO;
private final AvailabilityNotifyService availabilityNotifyService;
    private final LibraryBookPaginationService bookPaginationService;

    public void returnBook(Integer id) {
        LibraryBookEntity book
                = bookDAO.findById(id)
                .orElseThrow(() -> new BookServiceCustomException(
                        "Book with given id not found",
                        HttpStatus.BAD_REQUEST
                ));
        if(book.getQuantity()==0){
            availabilityNotifyService.sendNotifications(book.getName(),book.getId());
        }

        book.setQuantity(book.getQuantity() + 1);
        bookDAO.save(book);
        log.info("Book Quantity updated Successfully");
    }

    public void lendBook(Integer id) {
        LibraryBookEntity book
                = bookDAO.findById(id)
                .orElseThrow(() -> new BookServiceCustomException(
                        "Book with given Name not found",
                        HttpStatus.BAD_REQUEST
                ));
        if (book.getQuantity() <= 0) {
            throw new BookServiceCustomException(
                    "Book does not have sufficient Quantity",
                    HttpStatus.BAD_REQUEST
            );
        }

        book.setQuantity(book.getQuantity() - 1);
        bookDAO.save(book);
        log.info("Book Quantity updated Successfully");
    }

    public void addBook(AddBookRequest request) {
        Optional<LibraryBookEntity> optOfBook =
                bookDAO.findByNameAndPublicationDate(request.getName(), request.getPublicationYear());

        LibraryBookEntity book = optOfBook.map(s -> {
            log.info("Book Quantity updated Successfully");
            s.setQuantity(s.getQuantity() + request.getQuantity());
            return s;
        }).orElseGet(() -> {
            log.info("The new book added successfully");
            return LibraryBookEntity.builder()
                    .name(request.getName())
                    .author(request.getAuthor())
                    .quantity(request.getQuantity())
                    .category(request.getCategory())
                    .publicationDate(request.getPublicationYear())
                    .build();
        });

        bookDAO.save(book);
    }

    public List<LibraryBookEntity> findAll(Integer pageNumber, Integer pageSize) {
        return bookPaginationService.paginateAll(pageNumber, pageSize);
    }

    public List<LibraryBookEntity> findAllByCategory(Integer pageNumber, Integer pageSize, String category) {
        return bookPaginationService.paginateAllByCategory(pageNumber, pageSize, category);
    }

    public List<LibraryBookEntity> findAvailable(Integer pageNumber, Integer pageSize) {
        return bookPaginationService.paginateAvailable(pageNumber, pageSize);
    }

    public List<LibraryBookEntity> findAvailableByCategory(Integer pageNumber, Integer pageSize, String category) {
        return bookPaginationService.paginateAvailableByCategory(pageNumber, pageSize, category);
    }

    public LibraryBookEntity findBook(Integer id) {
        return bookDAO.findById(id).orElseThrow(
                () -> new BookServiceCustomException(
                        "Can not find book with this id",
                        HttpStatus.BAD_REQUEST));
    }
}
