package com.example.RentalService.business;

import com.example.RentalService.business.dao.OrderDAO;
import com.example.RentalService.external.client.BookService;
import com.example.RentalService.infrastructure.database.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final BookService bookService;
    private final OrderDAO orderDAO;

    public void placeOrder(Integer id) {
        bookService.lendBook(id);
        OrderEntity order = OrderEntity.builder()
                .receivedDate(OffsetDateTime.now())
                .bookId(id)
                .build();
        orderDAO.save(order);
    }

    public void returnOrder(Integer id) {
        bookService.returnBook(id);
    }
}
