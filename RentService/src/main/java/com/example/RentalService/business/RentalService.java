package com.example.RentalService.business;

import com.example.RentalService.business.dao.RentalDAO;
import com.example.RentalService.domain.CustomException;
import com.example.RentalService.domain.FinishedRentalsResponse;
import com.example.RentalService.domain.ReturnBookPaymentRequest;
import com.example.RentalService.external.client.BookService;
import com.example.RentalService.external.client.PaymentService;
import com.example.RentalService.external.request.PaymentRequest;
import com.example.RentalService.external.response.LibraryBookEntity;
import com.example.RentalService.external.response.PaymentInfoResponse;
import com.example.RentalService.external.response.RentalInfoResponse;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalService {
    private final BookService bookService;
    private final PaymentService paymentService;
    private final RentalDAO rentalDAO;

    private final RentalPaginationService rentalPaginationService;

    @Transactional
    public void lentBook(Integer id, String email) {
        Optional<RentalEntity> opt = rentalDAO.findActiveByBookIdAndEmail(id, email);
        if (opt.isPresent())
            throw new CustomException("You have already borrowed this book", "BAD REQUEST", 400);

        bookService.lendBook(id);
        RentalEntity order = RentalEntity.builder()
                .receivedDate(OffsetDateTime.now())
                .fee(BigDecimal.ZERO)
                .rentalPeriod(0)
                .bookId(id)
                .email(email)
                .build();
        rentalDAO.save(order);
    }

    @Transactional
    public void returnBook(Integer id, String email) {
        RentalEntity rental = rentalDAO.findActiveByBookIdAndEmail(id, email)
                .orElseThrow(() -> new CustomException("You didn't borrow this book", "Bad request", 400));

        if (rental.getFee().compareTo(BigDecimal.ZERO) != 0)
            throw new CustomException(
                    "You must pay for the book, select the option return the book with payment",
                    "Bad request",
                    400
            );
        bookService.returnBook(id);
        rental.setReturnDate(OffsetDateTime.now());
        rentalDAO.save(rental);
    }

    @Transactional
    public void returnBookWithPayment(Integer id, ReturnBookPaymentRequest request) {
        RentalEntity rental = rentalDAO.findActiveByBookIdAndEmail(id, request.getEmail())
                .orElseThrow(() -> new CustomException("You didn't borrow this book", "Bad request", 400));

        PaymentRequest paymentRequest = buildPaymentRequest(id, request, rental);
        paymentService.doPayment(paymentRequest);

        bookService.returnBook(id);

        rental.setReturnDate(OffsetDateTime.now());
        rentalDAO.save(rental);
    }

    private PaymentRequest buildPaymentRequest(Integer id, ReturnBookPaymentRequest request, RentalEntity rental) {
        return PaymentRequest.builder()
                .transactionType("RENTAL")
                .paymentMode(request.getPaymentMode())
                .referenceId(id)
                .amount(rental.getFee())
                .email(request.getEmail())
                .build();
    }

    @Transactional
    public RentalInfoResponse rentalInfo(Integer bookId, String email) {
        RentalEntity order = rentalDAO.findActiveByBookIdAndEmail(bookId, email)
                .orElseThrow(() -> new CustomException("You didn't borrow this book", "BAD REQUEST", 400));
        return buildRentalInfoResponse(order);
    }

    private RentalInfoResponse buildRentalInfoResponse(RentalEntity rental) {
        return RentalInfoResponse.builder()
                .bookId(rental.getBookId())
                .email(rental.getEmail())
                .receivedDate(rental.getReceivedDate())
                .returnDate(rental.getReturnDate())
                .loanPeriod(rental.getRentalPeriod())
                .fee(rental.getFee())
                .build();
    }

    public List<RentalEntity> getActiveRentals(String email, Integer pageNumber, Integer pageSize) {
        return rentalPaginationService.getActiveRentals(email, pageNumber, pageSize);
    }

    public List<FinishedRentalsResponse> getFinishedRentals(String email, Integer pageNumber, Integer pageSize) {
        List<RentalEntity> rentals = rentalPaginationService.getFinishedRentals(email, pageNumber, pageSize);
        return rentals.stream().map(this::buildFinishRentalsResponse).toList();
    }

    private FinishedRentalsResponse buildFinishRentalsResponse(RentalEntity rental) {
        LibraryBookEntity book = bookService.getBook(rental.getBookId()).getBody();
        FinishedRentalsResponse response = FinishedRentalsResponse.builder()
                .email(rental.getEmail())
                .fee(rental.getFee())
                .receivedDate(rental.getReceivedDate())
                .rentalPeriod(rental.getRentalPeriod())
                .returnDate(rental.getReturnDate())
                .bookName(book.getName())
                .build();
        if (rental.getFee().compareTo(BigDecimal.ZERO) != 0) {
            PaymentInfoResponse paymentInfoResponse = paymentService
                    .getPaymentInfo(rental.getRentalId(), "RENTAL").getBody();

            response.setPaymentMode(paymentInfoResponse.getPaymentMode());
        }
        return response;
    }
}
