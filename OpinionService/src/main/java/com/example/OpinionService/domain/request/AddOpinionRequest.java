package com.example.OpinionService.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOpinionRequest {
    private Integer bookId;
    private Integer rating;
    private String comment;
}
