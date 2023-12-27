package com.example.RentalService.business;

import com.example.RentalService.business.dao.OrderDAO;
import com.example.RentalService.domain.CustomException;
import com.example.RentalService.external.client.BookService;
import com.example.RentalService.external.response.RentalInfoResponse;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalService {
    private final BigDecimal DAY_FEE = BigDecimal.valueOf(0.50);
    private final long freeRentPeriod = 14;
    private final BookService bookService;
    private final OrderDAO orderDAO;

    public void placeOrder(Integer id, String email) {
        Optional<RentalEntity> opt = orderDAO.findByBookIdAndEmail(id, email);
        if (opt.isPresent())
            throw new CustomException("You have already borrowed this book", "BAD REQUEST", 404);

        bookService.lendBook(id);
        RentalEntity order = RentalEntity.builder()
                .receivedDate(OffsetDateTime.now())
                .bookId(id)
                .email(email)
                .build();
        orderDAO.save(order);
    }

    public void returnOrder(Integer id) {
        bookService.returnBook(id);
    }

    public RentalInfoResponse rentalInfo(Integer bookId, String email) {
        RentalEntity order = orderDAO.findByBookIdAndEmail(bookId, email)
                .orElseThrow(() -> new CustomException("You have already borrowed this book", "BAD REQUEST", 404));
        return buildRentalInfoResponse(order);
    }

    private RentalInfoResponse buildRentalInfoResponse(RentalEntity order) {
        Long loanPeriod = ChronoUnit.DAYS.between(order.getReceivedDate(), OffsetDateTime.now());
        BigDecimal fee =
                loanPeriod < freeRentPeriod ? BigDecimal.ZERO : BigDecimal.valueOf(loanPeriod).multiply(DAY_FEE);

        return RentalInfoResponse.builder()
                .bookId(order.getBookId())
                .email(order.getEmail())
                .receivedDate(order.getReceivedDate())
                .returnDate(null)
                .loanPeriod(loanPeriod)
                .fee(fee)
                .build();
    }
}
