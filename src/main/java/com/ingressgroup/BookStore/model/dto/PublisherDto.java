package com.ingressgroup.BookStore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PublisherDto {

    private String name;


    private String email;

    private String password;
}
