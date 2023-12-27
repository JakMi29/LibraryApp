package com.example.BookService.controller;

import com.example.BookService.business.ShopService;
import com.example.BookService.domain.request.AddBookRequest;
import com.example.BookService.infrastructure.database.entity.ShopBookEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {

    private final ShopService ShopService;

    @PutMapping("/buy/{id}")
    public ResponseEntity<Void> buyBook(@PathVariable("id") Integer id) {
        ShopService.lendBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "allBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ShopBookEntity>> allBooks(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(ShopService.findAll(pageNumber, pageSize));
    }

    @GetMapping(value = "availableBooks/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ShopBookEntity>> availableBooks(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(ShopService.findAll(pageNumber, pageSize));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Void> AddBook(
            @RequestBody AddBookRequest request
    ) {
        ShopService.addBook(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
