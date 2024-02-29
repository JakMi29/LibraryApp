package com.example.AvailabilityNotifyService.business;

import com.example.AvailabilityNotifyService.business.dao.FollowersDAO;
import com.example.AvailabilityNotifyService.domain.FollowBookRequest;
import com.example.AvailabilityNotifyService.external.client.Kafka;
import com.example.AvailabilityNotifyService.external.request.EmailMessage;
import com.example.AvailabilityNotifyService.infrastructure.database.entity.FollowersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowersDAO dao;
    private final Kafka kafka;
    private final String CONTENT = "I kindly inform you that Book: %s is now available. Hurry up.";
    private final String SUBJECT = "Book: %s is now available.";

    @Transactional
    public void follow(FollowBookRequest request) {
        FollowersEntity entity = FollowersEntity.builder()
                .bookId(request.getBookId())
                .email(request.getEmail())
                .build();
        dao.save(entity);
    }

    @Transactional
    public void unFollow(FollowBookRequest request) {
        FollowersEntity entity = dao.findByBookIdAndEmail(request.getBookId(), request.getEmail()).orElseThrow();
        dao.remove(entity);
    }

        public void sendNotifications(String bookName, Integer bookId) {
        List<FollowersEntity> followers = dao.findAllByBookId(bookId);
        List<EmailMessage> messages = followers.stream().
                map(
                        t -> {
                            return EmailMessage.builder()
                                    .content(String.format(CONTENT, bookName))
                                    .recipient(t.getEmail())
                                    .subject(String.format(SUBJECT,bookName))
                                    .build();
                        })
                .collect(Collectors.toList());

        kafka.sendEmail(messages);
    }
}
