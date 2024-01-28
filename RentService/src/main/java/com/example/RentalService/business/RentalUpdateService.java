package com.example.RentalService.business;

import com.example.RentalService.business.dao.RentalDAO;
import com.example.RentalService.external.client.BookService;
import com.example.RentalService.external.client.Kafka;
import com.example.RentalService.external.request.EmailMessage;
import com.example.RentalService.external.response.LibraryBookEntity;
import com.example.RentalService.infrastructure.database.entity.RentalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalUpdateService {
    private final BigDecimal DAY_FEE = BigDecimal.valueOf(0.50);
    private final int FREE_RENTAL_PERIOD = 14;
    private final String CONTENT = "I kindly inform you that the free rental period has expired for book: %s. The fee for each subsequent day will be 0.50. Your current fee is %s";
    private final String SUBJECT = "Rental period has expired";
    private final RentalDAO rentalDAO;
    private final Kafka kafka;
    private final BookService bookService;

    @Transactional
    @Scheduled(cron = "0 27 14 * * ?")
    void updateRental() {
        List<RentalEntity> rentalsToUpdate = rentalDAO.findAllToUpdate();

        List<RentalEntity> rentalsToSave = rentalsToUpdate.stream().map((this::update)).toList();

        rentalDAO.saveAll(rentalsToSave);

        var list = rentalsToSave.stream().filter(t -> t.getRentalPeriod() > 14).toList();
        kafka.sendEmail(sendNotification(list));

    }

    private RentalEntity update(RentalEntity entity) {
        int rentalPeriod = entity.getRentalPeriod() + 1;
        entity.setRentalPeriod(rentalPeriod);
        entity.setFee(
                rentalPeriod > 14 ? BigDecimal.valueOf(rentalPeriod - FREE_RENTAL_PERIOD).multiply(DAY_FEE) : BigDecimal.ZERO
        );
        return entity;
    }

    private List<EmailMessage> sendNotification(List<RentalEntity> entities) {
        return entities.stream().map(this::getEmailMessage).collect(Collectors.toList());
    }

    private EmailMessage getEmailMessage(RentalEntity entities) {
        LibraryBookEntity bookEntity = bookService.getBook(entities.getBookId()).getBody();
        return EmailMessage.builder()
                .content(String.format(CONTENT, bookEntity.getName(), entities.getFee().toString()))
                .recipient(entities.getEmail())
                .subject(SUBJECT)
                .build();
    }

}
