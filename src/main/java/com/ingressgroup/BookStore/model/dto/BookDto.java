package com.ingressgroup.BookStore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookDto {

    private String name;

    private String author;

    private String publisher;


}
