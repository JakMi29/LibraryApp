package com.example.RentalService.business;

import com.example.RentalService.business.dao.RentalDAO;
import com.example.RentalService.domain.FinishedRentalsResponse;
import com.example.RentalService.domain.RentalServiceCustomException;
import com.example.RentalService.domain.ReturnBookPaymentRequest;
import com.example.RentalService.external.client.BookService;
import com.example.RentalService.external.client.PaymentService;
import com.example.RentalService.external.request.PaymentRequest;
import com.example.RentalService.external.response.LibraryBookEntity;
import com.example.RentalService.external.response.PaymentInfoResponse;
import com.example.RentalService.external.response.RentalInfoResponse;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
            throw new RentalServiceCustomException(
                    "You have already borrowed this book",
                    HttpStatus.BAD_REQUEST
            );

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
                .orElseThrow(() -> new RentalServiceCustomException(
                        "You didn't borrow this book",
                        HttpStatus.BAD_REQUEST));

        if (rental.getFee().compareTo(BigDecimal.ZERO) != 0)
            throw new RentalServiceCustomException(
                    "You must pay for the book, select the option return the book with payment",
                    HttpStatus.BAD_REQUEST
            );
        bookService.returnBook(id);
        rental.setReturnDate(OffsetDateTime.now());
        rentalDAO.save(rental);
    }

    @Transactional
    public void returnBookWithPayment(Integer id, ReturnBookPaymentRequest request) {
        RentalEntity rental = rentalDAO.findActiveByBookIdAndEmail(id, request.getEmail())
                .orElseThrow(() -> new RentalServiceCustomException(
                        "You didn't borrow this book",
                        HttpStatus.BAD_REQUEST));

        PaymentRequest paymentRequest = buildPaymentRequest(id, request, rental);
        paymentService.doPayment(paymentRequest);

        bookService.returnBook(id);

        rental.setReturnDate(OffsetDateTime.now());
        rentalDAO.save(rental);
    }

    @Transactional
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
                .orElseThrow(() -> new RentalServiceCustomException(
                        "You didn't borrow this book",
                        HttpStatus.BAD_REQUEST));
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

    @Transactional
    public List<RentalEntity> getActiveRentals(String email, Integer pageNumber, Integer pageSize) {
        return rentalPaginationService.getActiveRentals(email, pageNumber, pageSize);
    }

    @Transactional
    public List<FinishedRentalsResponse> getFinishedRentals(String email, Integer pageNumber, Integer pageSize) {
        List<RentalEntity> rentals = rentalPaginationService.getFinishedRentals(email, pageNumber, pageSize);
        return rentals.stream().map(this::buildFinishRentalsResponse).toList();
    }

    @Transactional
    private FinishedRentalsResponse buildFinishRentalsResponse(RentalEntity rental) {
        LibraryBookEntity book = bookService.getBook(rental.getBookId()).getBody();
        FinishedRentalsResponse response = FinishedRentalsResponse.builder()
                .fee(rental.getFee())
                .email(rental.getEmail())
                .bookName(book.getName())
                .returnDate(rental.getReturnDate())
                .receivedDate(rental.getReceivedDate())
                .rentalPeriod(rental.getRentalPeriod())
                .build();
        if (rental.getFee().compareTo(BigDecimal.ZERO) != 0) {
            PaymentInfoResponse paymentInfoResponse = paymentService
                    .getPaymentInfo(rental.getRentalId()).getBody();

            response.setPaymentMode(paymentInfoResponse.getPaymentMode());
        }
        return response;
    }
}
