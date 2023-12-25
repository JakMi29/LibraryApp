package com.example.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/bookServiceFallback")
    public String bookServiceFallback() {
        return "Book Service is down!";
    }
    @PutMapping("/bookServiceFallback")
    public String bookServiceFallbackPut() {
        return "Book Service is down!";
    }
    @GetMapping("/rentalServiceFallback")
    public String rentalServiceFallbackGet() {
        return "Rental Service is down!";
    }
    @PutMapping("/rentalServiceFallback")
    public String rentalServiceFallbackPut() {
        return "Rental Service is down!";
    }
    @PostMapping("/rentalServiceFallback")
    public String rentalServiceFallbackPost() {
        return "Rental Service is down!";
    }
    @PostMapping("/opinionServiceFallback")
    public String opinionServiceFallbackPost() {
        return "Opinion Service is down!";
    }


}
