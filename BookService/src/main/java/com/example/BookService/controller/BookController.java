package com.example.BookService.controller;

import com.example.BookService.business.BookService;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.domain.request.BorrowReturnBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PutMapping("/borrow")
    public ResponseEntity<Void> borrowBook(
            @RequestBody BorrowReturnBookRequest request
            ) {
        bookService.borrowBook(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(
            @RequestBody BorrowReturnBookRequest request
    ) {
        bookService.returnBook(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addBook")
    public ResponseEntity<Void> AddBook(
            @RequestBody AddBookRequest request
            ) {
        bookService.addBook(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}