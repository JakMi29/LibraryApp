package com.example.BookService.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookRequest {
    private String name;
    private String Author;
    private Integer quantity;
    private Integer publicationYear;
}
