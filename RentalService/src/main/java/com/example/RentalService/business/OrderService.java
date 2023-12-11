package com.example.RentalService.business;

import com.example.RentalService.external.client.BookService;
import com.example.RentalService.external.request.BorrowReturnBookRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final BookService bookService;
    public void placeOrder(BorrowReturnBookRequest request) {
        bookService.lendBook(request);
    }

    public void returnOrder(BorrowReturnBookRequest request) {
        bookService.returnBook(request);
    }
}
