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
@RequestMapping("/library")
@AllArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryBookEntity> getBook(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(libraryService.findBook(id));
    }

    @GetMapping("allBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<LibraryBookEntity>> allBooks(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(libraryService.findAll(pageNumber, pageSize));
    }

    @GetMapping("allBooksByCategory/{pageNumber}/{pageSize}")
    public ResponseEntity<List<LibraryBookEntity>> allBooksByCategory(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @RequestParam String category
    ) {
        System.out.println(category);
        return ResponseEntity.ok(libraryService.findAllByCategory(pageNumber, pageSize, category));
    }

    @GetMapping("availableBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<LibraryBookEntity>> availableBooks(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(libraryService.findAvailable(pageNumber, pageSize));
    }

    @GetMapping("availableBooksByCategory/{pageNumber}/{pageSize}")
    public ResponseEntity<List<LibraryBookEntity>> availableBooksByCategory(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @RequestParam String category
    ) {
        return ResponseEntity.ok(libraryService.findAvailableByCategory(pageNumber, pageSize, category));
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Void> lendBook(
            @PathVariable("id") Integer id
    ) {
        libraryService.lendBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(
            @PathVariable("id") Integer id
    ) {
        libraryService.returnBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Void> AddBook(
            @RequestBody AddBookRequest request
    ) {
        libraryService.addBook(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
