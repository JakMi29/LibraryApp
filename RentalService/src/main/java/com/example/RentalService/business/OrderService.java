package com.example.RentalService.business;

import com.example.RentalService.external.client.BookService;
import com.example.RentalService.external.request.BorrowReturnBookRequest;
import com.example.RentalService.infrastructure.database.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final BookService bookService;
    public void placeOrder(Integer id) {
        bookService.lendBook(id);
    }

    public void returnOrder(Integer id) {
        bookService.returnBook(id);
    }
}
