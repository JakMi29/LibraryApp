package com.example.BookService.controller;

import com.example.BookService.business.LibraryService;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.infrastructure.database.entity.LibraryBookEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {

    private final LibraryService libraryService;

    @PutMapping("/buy/{id}")
    public ResponseEntity<Void> buyBook(@PathVariable("id") Integer id) {
        libraryService.lendBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "allBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<LibraryBookEntity>> allBooks(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(libraryService.findAll(pageNumber, pageSize));
    }
    @GetMapping(value = "availableBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<LibraryBookEntity>> availableBooks(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(libraryService.findAll(pageNumber, pageSize));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Void> AddBook(
            @RequestBody AddBookRequest request
            ) {
        libraryService.addBook(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
