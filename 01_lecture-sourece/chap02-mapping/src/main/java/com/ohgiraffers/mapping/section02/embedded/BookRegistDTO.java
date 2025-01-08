package com.ohgiraffers.mapping.section02.embedded;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookRegistDTO {

    private String bookTitle;
    private String author;
    private String publisher;
    private LocalDate createdDate;
    private int regularPrice;
    private double discountRate;

}
