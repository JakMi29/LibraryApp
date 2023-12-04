package com.example.BookService.controller;

import com.example.BookService.business.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") Integer productId,
            @RequestParam Integer quantity
    ) {
        bookService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
