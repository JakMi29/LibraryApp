package com.example.BookService.controller;

import com.example.BookService.business.BookService;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.domain.request.BorrowReturnBookRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PutMapping("/borrow/{id}")
    public ResponseEntity<Void> lendBook(@PathVariable("id") Integer id) {
        bookService.lendBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable("id") Integer id) {
        bookService.returnBook(id);
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
