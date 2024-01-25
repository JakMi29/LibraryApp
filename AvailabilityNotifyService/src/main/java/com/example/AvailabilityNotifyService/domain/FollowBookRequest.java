package com.example.AvailabilityNotifyService.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowBookRequest {
    private Integer bookId;
    private String email;
}
