package com.ingressgroup.BookStore.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class PageableDto {

    private List<BookDto> bookDtos;
    private int lastPage;
    private boolean hasNext;

}
